package com.best.seller.models.views;

import lombok.Data;

/**
 * @author Malindu De Alwis
 */
@Data
public class PriceRequest {
    private String key;
    private int cartonCount;
    private int unitCount;
}
