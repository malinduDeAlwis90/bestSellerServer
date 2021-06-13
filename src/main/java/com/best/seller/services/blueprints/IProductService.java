package com.best.seller.services.blueprints;

import com.best.seller.models.pojos.Product;
import com.best.seller.models.views.CheckoutInfoResponse;
import com.best.seller.models.views.PriceRequest;
import com.best.seller.models.views.ProductResponse;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Product service.
 *
 * @author Malindu De Alwis
 */
public interface IProductService {
    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Product> findById(Long id);

    /**
     * Get Price list
     * @param key the product key
     * @return the price list
     * @throws EntityNotFoundException the entity not found exception
     */
    List<Double> getPriceList(String key) throws EntityNotFoundException;

    /**
     * Get the list of products
     *
     * @return the list of products
     */
    Map<String, ProductResponse> getProductList();

    /**
     * Get the price
     * @param key the product key
     * @param cartonCount the carton count
     * @param unitCount the unit count
     * @return the price
     */
    Double getPrice(String key, Integer cartonCount, Integer unitCount);

    /**
     * Get checkout info (prices)
     * @param request the cart item map
     * @return the price info
     */
    CheckoutInfoResponse getCheckoutInfo(Map<String, PriceRequest> request);
}
