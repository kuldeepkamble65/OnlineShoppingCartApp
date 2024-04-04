package org.example.controller;

import org.example.Dao.CartDao;
import org.example.Dao.ProductDao;
import org.example.Dao.UserDaoImpl;
import org.example.Handler.CartHandler;
import org.example.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class CartContoller {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartContoller.class);

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private CartHandler cartHandler;

    @Autowired
    private ProductDao productDao;

    @GetMapping("/showCart")
    public String showCart() {
        return "mycart";
    }



    @PostMapping(value = "/adduserToCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestHeader(value = "id") int id) {
        try {
            String response = cartHandler.addCartToUser(id);
            LOGGER.info("User added to cart: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error adding cart to user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding cart to user: " + e.getMessage());
        }
    }


    @PostMapping(value = "/addtocart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addingProductToCart(@RequestHeader(value = "userId") int userId,
                                                      @RequestHeader(value = "productId") int productId,
                                                      @RequestHeader(value = "quantity") int quantity) {
        try {
            String response = cartHandler.addProductToCart(userId, productId, quantity);
            LOGGER.info("Product added to cart: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error adding product to cart: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding product to cart: " + e.getMessage());
        }
    }

    @GetMapping(value = "/cart/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> fetchProductsInCart(@RequestHeader("userId") int userId) {
        try {
            List<Product> products = cartHandler.getProductsInCart(userId);
            LOGGER.info("Fetched products in cart for userId: {}", userId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Error fetching products in cart: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @PostMapping("/updateordelete")
    public ResponseEntity<String> updateCart(@RequestHeader("userId") int userId, @RequestBody Map<String, Object> mapdata) {
        try {
            if (mapdata.containsKey("updatedData")) {
                Map<String, Integer> updatedQuantitiesMap = (Map<String, Integer>) mapdata.get("updatedData");

                if (!updatedQuantitiesMap.isEmpty()) {
                    cartHandler.updateProductQuantities(userId, updatedQuantitiesMap);
                    LOGGER.info("Updated product quantities: {}", updatedQuantitiesMap);
                }
            }
            if (mapdata.containsKey("deletedData")) {
                List<Integer> deletedProductIds = (List<Integer>) mapdata.get("deletedData");
                if (!deletedProductIds.isEmpty()) {
                    cartHandler.deleteProductsFromCart(userId, deletedProductIds);
                    LOGGER.info("Deleted products from cart: {}", deletedProductIds);
                }
            }
            return ResponseEntity.ok("Update product successfully");
        } catch (Exception e) {
            LOGGER.error("Error updating/deleting products from cart: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating/deleting products from cart: " + e.getMessage());
        }
    }
}