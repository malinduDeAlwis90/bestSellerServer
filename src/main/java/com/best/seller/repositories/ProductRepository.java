package com.best.seller.repositories;

import com.best.seller.models.pojos.Product;
import com.best.seller.models.views.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * The interface Product repository.
 *
 * @author Malindu De Alwis
 */
public interface ProductRepository extends Repository<Product, Long>, JpaRepository<Product, Long> {
    /**
     * Find the product by key.
     *
     * @param key the key
     * @return the product
     */
    Product findByKey(String key);

    /**
     * Get the product list
     *
     * @return the product list
     */
    @Query(value="SELECT new com.best.seller.models.views.ProductResponse(" +
            "p.key, p.name, p.price, p.cartonSize" +
            ") FROM Product p")
    List<ProductResponse> getProductList();
}
