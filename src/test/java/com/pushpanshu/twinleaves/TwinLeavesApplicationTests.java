package com.pushpanshu.twinleaves;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.pushpanshu.twinleaves.dto.ProductDTO;
import com.pushpanshu.twinleaves.model.Batch;
import com.pushpanshu.twinleaves.model.Gtin;
import com.pushpanshu.twinleaves.model.Product;
import com.pushpanshu.twinleaves.repository.BatchRepository;
import com.pushpanshu.twinleaves.repository.GtinRepository;
import com.pushpanshu.twinleaves.repository.ProductRepository;
import com.pushpanshu.twinleaves.service.implementation.MainServiceImpl;

@SpringBootTest
class TwinLeavesApplicationTests {

    // Successfully creates a product with valid ProductDTO
    @Test
    public void test_create_product_with_valid_productdto() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        BatchRepository batchRepository = mock(BatchRepository.class);
        GtinRepository gtinRepository = mock(GtinRepository.class);
        MainServiceImpl mainService = new MainServiceImpl(productRepository, batchRepository, gtinRepository);
    
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("Test Product");
        Batch batch = new Batch();
        batch.setMrp(100);
        batch.setSp(90);
        batch.setPurchasePrice(80);
        batch.setAvailableQuantity(10);
        productDTO.setBatches(List.of(batch));
        productDTO.setGtins(List.of("1234567890123"));
    
        Product savedProduct = new Product();
        savedProduct.setProductName("Test Product");
    
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
    
        // Act
        Product result = mainService.createProduct(productDTO);
    
        // Assert
        assertNotNull(result);
        assertEquals("Test Product", result.getProductName());
        verify(productRepository, times(1)).save(any(Product.class));
        verify(batchRepository, times(1)).save(any(Batch.class));
        verify(gtinRepository, times(1)).save(any(Gtin.class));
    }
    

}
