package com.best.seller.models.pojos;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Product.
 *
 * @author Malindu De Alwis
 */
@Data
@Entity
@EqualsAndHashCode
public class Product implements Serializable {

    /**
     * Instantiates a new Product.
     */
    public Product(){}

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String key;

    private String name;

    private Double price;

    private Integer cartonSize;
}