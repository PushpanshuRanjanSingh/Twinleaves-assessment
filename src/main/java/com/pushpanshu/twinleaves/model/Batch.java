package com.pushpanshu.twinleaves.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "Batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    @JsonBackReference
    private Product product;

    @Column(nullable = false)
    private Integer mrp;

    @Column(nullable = false)
    private Integer sp;

    @Column(nullable = false)
    private Integer purchasePrice;

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private Date inwardedOn=new Date();
}
