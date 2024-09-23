package com.pushpanshu.twinleaves.repository;

import com.pushpanshu.twinleaves.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch,Long> {
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity <= 0 AND b.inwardedOn = (SELECT MAX(b2.inwardedOn) FROM Batch b2 WHERE b2.availableQuantity <= 0)")
    List<Batch> findLatestNonPositiveBatches();
}
