package com.pushpanshu.twinleaves.service.implementation;

import com.pushpanshu.twinleaves.dto.ProductDTO;
import com.pushpanshu.twinleaves.model.Batch;
import com.pushpanshu.twinleaves.model.Gtin;
import com.pushpanshu.twinleaves.model.Product;
import com.pushpanshu.twinleaves.repository.BatchRepository;
import com.pushpanshu.twinleaves.repository.GtinRepository;
import com.pushpanshu.twinleaves.repository.ProductRepository;
import com.pushpanshu.twinleaves.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {
    private final ProductRepository productRepository;
    private final BatchRepository batchRepository;
    private final GtinRepository gtinRepository;

    public MainServiceImpl(ProductRepository productRepository, BatchRepository batchRepository, GtinRepository gtinRepository) {
        this.productRepository = productRepository;
        this.batchRepository = batchRepository;
        this.gtinRepository = gtinRepository;
    }

    @Override
    @Transactional
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product = productRepository.save(product);

        for (Batch b: productDTO.getBatches()){
            Batch batch = new Batch();
            batch.setProduct(product);
            batch.setMrp(b.getMrp());
            batch.setSp(b.getSp());
            batch.setPurchasePrice(b.getPurchasePrice());
            batch.setAvailableQuantity(b.getAvailableQuantity());
            batch.setInwardedOn(b.getInwardedOn());
            batchRepository.save(batch);
        }

        for (String gtinString : productDTO.getGtins()) {
            Gtin gtin = new Gtin();
            gtin.setProduct(product);
            gtin.setGtin(gtinString);
            gtinRepository.save(gtin);
        }
        return product;

    }

    @Override
    public Gtin getProductByGtin(String gtin) {
        return gtinRepository.findGtinByGtin(gtin)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"GTIN not found"));
    }

    @Override
    public List<Gtin> getGtinsWithPositiveQuantity() {
        List<Gtin> gtins = gtinRepository.findGtinsWithPositiveQuantity();
        return gtins;
    }

    @Override
    public List<Batch> getLatestNonPositiveBatches() {
        List<Batch> batches = batchRepository.findLatestNonPositiveBatches();
        return batches;
    }
}
