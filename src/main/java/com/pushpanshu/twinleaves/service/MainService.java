package com.pushpanshu.twinleaves.service;

import com.pushpanshu.twinleaves.dto.ProductDTO;
import com.pushpanshu.twinleaves.model.Batch;
import com.pushpanshu.twinleaves.model.Gtin;
import com.pushpanshu.twinleaves.model.Product;

import java.util.List;

public interface MainService {
    Product createProduct(ProductDTO product);
    Gtin getProductByGtin(String gtin);
    List<Gtin> getGtinsWithPositiveQuantity();
    List<Batch> getLatestNonPositiveBatches();

}
