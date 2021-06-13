package com.best.seller.models.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Malindu De Alwis
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {
    private String key;
    private String name;
    private Double price;
    private Integer cartonSize;
}
