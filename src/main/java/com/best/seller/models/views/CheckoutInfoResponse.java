package com.best.seller.models.views;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Malindu De Alwis
 */
@Data
public class CheckoutInfoResponse {
    private Map<String, Double> prices = new HashMap<>();
    private Double total;
}
