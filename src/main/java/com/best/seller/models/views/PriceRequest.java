package com.best.seller.models.views;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Malindu De Alwis
 */
@Data
public class PriceRequest {
    @NotBlank(message="Key is empty.")
    private String key;
    private int cartonCount;
    private int unitCount;
}
