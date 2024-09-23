package com.pushpanshu.twinleaves.repository;

import com.pushpanshu.twinleaves.model.Gtin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GtinRepository extends JpaRepository<Gtin, Long> {
    @Query("SELECT DISTINCT g FROM Gtin g JOIN g.product p JOIN p.batches b WHERE b.availableQuantity > 0")
    List<Gtin> findGtinsWithPositiveQuantity();

    Optional<Gtin> findGtinByGtin(String gtin);
}