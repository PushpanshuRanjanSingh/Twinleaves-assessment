package com.pushpanshu.twinleaves.dto;

import com.pushpanshu.twinleaves.model.Batch;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private String productName;
    private List<Batch> batches;
    private List<String> gtins;

}
