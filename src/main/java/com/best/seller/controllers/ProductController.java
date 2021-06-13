package com.best.seller.controllers;

import com.best.seller.models.views.CheckoutInfoResponse;
import com.best.seller.models.views.PriceRequest;
import com.best.seller.models.views.ProductResponse;
import com.best.seller.services.implementations.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * The type Product controller.
 *
 * @author Malindu De Alwis
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductService productService; // todo hateos

    /**
     * Instantiates a new Product controller.
     *
     * @param productService              the product service
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Return the price list.
     *
     * @return the response entity
     */
    @GetMapping("/{key}/price-list")
    public ResponseEntity<List<Double>> getPriceList(@PathVariable String key) {
        log.info("Incoming price list request. Key: " + key);
        List<Double> priceList = productService.getPriceList(key);
//        EntityModel<BankProductResponse> entityModel = bankResponseModelAssembler.toModel(savedProduct);
        log.info("Price list successfully received. Returning");

        return ResponseEntity.ok(priceList); // todo
    }

    /**
     * Return the price for carton/unit count.
     *
     * @return the response entity
     */
    @PostMapping("/price") // todo
    public ResponseEntity<Double> getPrice(@RequestBody PriceRequest request) { // todo request validation
        String key = request.getKey();
        Integer cartonCount = request.getCartonCount();
        Integer unitCount = request.getUnitCount();

        log.info("Incoming price request. Key: {}, cartonCount: {}, unitCount: {}", key, cartonCount, unitCount);
        Double price = productService.getPrice(key, cartonCount, unitCount);
//        EntityModel<BankProductResponse> entityModel = bankResponseModelAssembler.toModel(savedProduct);
        log.info("Price successfully received. Returning");

        return ResponseEntity.ok(price); // todo
    }

    /**
     * Return the price information for a cart
     *
     * @return the response entity
     */
    @PostMapping("/checkout-info")
    public ResponseEntity<CheckoutInfoResponse> getCheckoutInfo(@RequestBody Map<String, PriceRequest> request) {

        log.info("Incoming checkout info request.");
        CheckoutInfoResponse response = productService.getCheckoutInfo(request);
        log.info("Checkout info successfully received. Returning.");

        return ResponseEntity.ok(response);
    }

    /**
     * Return the price list.
     *
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<Map<String, ProductResponse>> getProductList() {
        log.info("Incoming product list request.");
        Map<String, ProductResponse> priceList = productService.getProductList();
        log.info("Product list successfully received. Returning");
        return ResponseEntity.ok(priceList);
    }
}
