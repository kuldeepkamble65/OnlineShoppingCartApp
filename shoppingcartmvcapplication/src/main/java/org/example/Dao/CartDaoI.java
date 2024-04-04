package org.example.Dao;

import org.example.model.Product;

import java.util.List;
import java.util.Map;

public interface CartDaoI {
    List<Product> getProductsByUserId(int userId);

    void deleteProductsFromCart(int userId, List<Integer> productIds);

    void updateProductQuantity(int userId, Map<String, Integer> updatedQuantitiesMap);
}
