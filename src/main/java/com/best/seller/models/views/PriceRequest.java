package com.best.seller.models.views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Malindu De Alwis
 */
@Data
public class PriceRequest {
    private String key;
    private int cartonCount;
    private int unitCount;
}
