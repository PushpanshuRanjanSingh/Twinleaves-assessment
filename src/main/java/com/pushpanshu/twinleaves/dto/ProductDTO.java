package com.pushpanshu.twinleaves.dto;

import com.pushpanshu.twinleaves.model.Batch;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    @NotNull
    private String productName;

    @NotEmpty
    private List<Batch> batches;

    @NotEmpty
    private List<String> gtins;

}
