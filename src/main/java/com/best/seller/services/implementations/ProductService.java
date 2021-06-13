package com.best.seller.services.implementations;

import com.best.seller.models.pojos.Product;
import com.best.seller.models.views.CheckoutInfoResponse;
import com.best.seller.models.views.PriceRequest;
import com.best.seller.models.views.ProductResponse;
import com.best.seller.repositories.ProductRepository;
import com.best.seller.services.blueprints.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * The type Product service.
 *
 * @author Malindu De Alwis
 */
@Slf4j
@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Value("${app.laborMultiplier}")
    private Double laborMultiplier;

    @Value("${app.discountMultiplier}")
    private Double discountMultiplier;

    @Value("${app.discountCartonLimit}")
    private Integer discountCartonLimit;

    /**
     * Instantiates a new Product service.
     *  @param productRepository the product repository
     */
    public ProductService(
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Double> getPriceList(String key) throws EntityNotFoundException {
        log.info("Getting price list. Retrieving product");
        Product product = productRepository.findByKey(key);
        if (product != null) {
            log.info("Product retrieved successfully. Calculating price list");
            List<Double> responseList = new ArrayList<>();
            Integer cartonSize = product.getCartonSize();
            Double price = product.getPrice();

//            laborMultiplier;
//            discountMultiplier;
//            discountCartonLimit;

            for (int i = 1; i <= 50; i++) {
                responseList.add(calculatePrice(price, cartonSize, i));
            }
            log.info("Price list successfully calculated.");
            return responseList;
        } else {
            throw new EntityNotFoundException("Product not found.");
        }
    }

    @Override
    public Map<String, ProductResponse> getProductList() {
        log.info("Getting product list.");
        Map<String, ProductResponse> responseMap = new HashMap<>();
        List<ProductResponse> productList = productRepository.getProductList();
        for(ProductResponse item: productList) {
            responseMap.put(item.getKey(), item);
        }
        return responseMap;
    }

    @Override
    public Double getPrice(String key, Integer cartonCount, Integer unitCount) {
        log.info("Getting price. Retrieving product");
        Product product = productRepository.findByKey(key);
        if (product != null) {
            log.info("Product retrieved successfully. Calculating price");
            Integer cartonSize = product.getCartonSize();
            return calculatePrice(product.getPrice(), cartonSize, cartonCount * cartonSize + unitCount);
        } else {
            throw new EntityNotFoundException("Product not found.");
        }
    }

    @Override
    public CheckoutInfoResponse getCheckoutInfo(Map<String, PriceRequest> request) {
        log.info("Getting checkout info.");
        CheckoutInfoResponse response = new CheckoutInfoResponse();
        Map<String, Double> prices = response.getPrices();
        Double total = 0D;

        for (PriceRequest priceRequest : request.values()) {
            Double price = getPrice(priceRequest.getKey(), priceRequest.getCartonCount(), priceRequest.getUnitCount());
            prices.put(priceRequest.getKey(), price);
            total = total + price;
        }
        response.setTotal(total);

        log.info("Checkout info retrieved successfully.");
        return response;
    }

    private Double calculatePrice(Double cartonPrice, Integer cartonSize, int unitCount) {// todo caching
        int cartonCount = unitCount / cartonSize;
        Integer remainUnitCount = unitCount % cartonSize;

        double price = cartonPrice * cartonCount;
        if (cartonCount >= discountCartonLimit) {
            price = price * discountMultiplier;
        }
        price = price + (cartonPrice * remainUnitCount * laborMultiplier / cartonSize);
        return price; // todo decimal point limitting
    }
}
