package com.pushpanshu.twinleaves.repository;

import com.pushpanshu.twinleaves.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> { }
