package com.pushpanshu.twinleaves.repository;

import com.pushpanshu.twinleaves.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BatchRepository extends JpaRepository<Batch,Long> {
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity < 1 ORDER BY b.inwardedOn DESC LIMIT 1")
    Batch findLatestNonPositiveBatch();
}
