package com.best.seller;
import com.best.seller.models.pojos.Product;
import com.best.seller.models.views.CheckoutInfoResponse;
import com.best.seller.models.views.PriceRequest;
import com.best.seller.models.views.ProductResponse;
import com.best.seller.repositories.ProductRepository;
import com.best.seller.services.implementations.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * The type Product Service application tests.
 */

@TestPropertySource(locations="classpath:application.properties")
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    ProductDataPopulator productDataPopulator = new ProductDataPopulator();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(productService, "laborMultiplier", 1.3D);
        ReflectionTestUtils.setField(productService, "discountMultiplier", 0.9D);
        ReflectionTestUtils.setField(productService, "discountCartonLimit", 3);

    }


    @Test
    public void getPriceList() {
        Product product = productDataPopulator.getProduct();
        String key = product.getKey();

        when( productRepository.findByKey(key)).thenReturn(product);

        List<Double> response = productService.getPriceList(key);

        assertEquals(11.38, response.get(0), 0.01);
        assertEquals(45.5, response.get(3), 0.01);
        assertEquals(56.88, response.get(4), 0.01);
        assertEquals(452.38, response.get(48), 0.01);
        assertEquals(463.75, response.get(49), 0.01);
    }

    @Test
    public void getProductList() {
        Product product = productDataPopulator.getProduct();
        when(productRepository.getProductList()).thenReturn(productDataPopulator.getProductList());

        Map<String, ProductResponse> response = productService.getProductList();

        ProductResponse responseItem = response.get(product.getKey());

        assertEquals(product.getKey(), responseItem.getKey());
        assertEquals(product.getName(), responseItem.getName());
        assertEquals(product.getPrice(), responseItem.getPrice());
        assertEquals(product.getCartonSize(), responseItem.getCartonSize());
    }

    @Test
    public void getPrice() {
        Product product = productDataPopulator.getProduct();
        String key = product.getKey();
        Integer cartonCount = 2;
        Integer unitCount = 3;

        when(productRepository.findByKey(key)).thenReturn(product);

        Double response = productService.getPrice(key, cartonCount, unitCount);

        assertEquals(384.13, response, 0.01);
    }

    @Test
    public void getCheckoutInfo() {
        Product product = productDataPopulator.getProduct();
        String key = product.getKey();
        int cartonCount = 3;
        int unitCount = 4;

        when(productRepository.findByKey(key)).thenReturn(product);

        PriceRequest item = new PriceRequest();
        item.setKey(key);
        item.setCartonCount(cartonCount);
        item.setUnitCount(unitCount);
        Map<String, PriceRequest> request = new HashMap<>();
        request.put(key, item);

        CheckoutInfoResponse response = productService.getCheckoutInfo(request);

        assertEquals(518, response.getPrices().get(key), 0.01);
    }
}
