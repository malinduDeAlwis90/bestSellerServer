package com.best.seller;

import com.best.seller.models.pojos.Product;
import com.best.seller.models.views.ProductResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDataPopulator {

    private Product product = populateProduct();
    private List<ProductResponse> productList = populateProductList();


    private Product populateProduct(){
        Product product = new Product();

        product.setId(1L);
        product.setKey("PEN_EARS");
        product.setName("Penguin-ears");
        product.setPrice(175D);
        product.setCartonSize(20);

        return product;
    }

    private List<ProductResponse> populateProductList() {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setKey(product.getKey());
        productResponse.setCartonSize(product.getCartonSize());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());

        List<ProductResponse> productList = new ArrayList<>();
        productList.add(productResponse);
        return productList;
    }
}
