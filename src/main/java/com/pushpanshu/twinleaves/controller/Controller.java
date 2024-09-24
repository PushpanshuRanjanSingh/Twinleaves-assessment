package com.pushpanshu.twinleaves.controller;

import com.pushpanshu.twinleaves.dto.ProductDTO;
import com.pushpanshu.twinleaves.model.Batch;
import com.pushpanshu.twinleaves.model.Gtin;
import com.pushpanshu.twinleaves.model.Product;
import com.pushpanshu.twinleaves.service.MainService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private final MainService service;

    public Controller(MainService service) {
        this.service = service;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO createProductDTO) {
        Product createdProduct = service.createProduct(createProductDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @GetMapping("/products/gtin/{gtin}")
    public ResponseEntity<Gtin> getProductByGtin(@PathVariable String gtin) {
        Gtin data = service.getProductByGtin(gtin);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/gtins/positive-quantity")
    public ResponseEntity<List<Gtin>> getGtinsWithPositiveQuantity() {
        List<Gtin> gtins = service.getGtinsWithPositiveQuantity();
        return ResponseEntity.ok(gtins);
    }

    @GetMapping("/batches/latest-non-positive")
    public ResponseEntity<Batch> getLatestNonPositiveBatches() {
        Batch batches = service.getLatestNonPositiveBatches();
        return ResponseEntity.ok(batches);
    }

}
