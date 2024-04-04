package org.example.Handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.Dao.CartDao;
import org.example.Dao.ProductDao;
import org.example.Dao.UserDaoImpl;
import org.example.model.UserCartMapping;
import org.example.model.Product;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartHandler {
    private static final Logger LOGGER = LogManager.getLogger(CartHandler.class);

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Autowired
    private ProductDao productDao;

    public String addCartToUser(int id) {
        try {
            LOGGER.info("start::CartHandler::addCartToUser::");

            User user = userDaoImpl.findById(id);
            if (user == null) {
                throw new IllegalArgumentException("User not found with ID: " + id);
            }

            UserCartMapping userCartMapping = new UserCartMapping();
            userCartMapping.setUser(user);

            userDaoImpl.updateUser(user);

            LOGGER.info("Cart added successfully!");
            return "Cart added successfully!";
        } catch (Exception e) {
            LOGGER.error("Error adding cart to user", e);
            throw new RuntimeException("Error adding cart to user", e);
        }
    }

    public String addProductToCart(int userId, int productId, int quantity) {
        try {
            LOGGER.info("start::CartHandler::addProductToCart::");

            User user = userDaoImpl.findById(userId);
            Product product = productDao.findById(productId);

            if (user == null || product == null) {
                LOGGER.error("User or Product not found.");
                return "User or Product not found.";
            }

            List<UserCartMapping> cartMappings = user.getCartMappings();

            UserCartMapping existingMapping = null;
            for (UserCartMapping mapping : cartMappings) {
                if (mapping.getProduct().getId() == productId) {
                    existingMapping = mapping;
                    break;
                }
            }

            if (existingMapping != null) {
                existingMapping.setQuantity(existingMapping.getQuantity() + quantity);
            } else {
                UserCartMapping cartItem = new UserCartMapping();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartMappings.add(cartItem);
            }

            userDaoImpl.updateUser(user);

            LOGGER.info("Product added to cart successfully!");
            return "Product added to cart successfully!";
        } catch (Exception e) {
            LOGGER.error("Error adding product to cart", e);
            throw new RuntimeException("Error adding product to cart", e);
        }
    }

    public List<Product> getProductsInCart(int userId) {
        try {
            LOGGER.info("start::CartHandler::getProductsInCart::");

            LOGGER.info("Fetching products in cart");
            return cartDao.getProductsByUserId(userId);
        } catch (Exception e) {
            LOGGER.error("Error fetching products in cart", e);
            throw new RuntimeException("Error fetching products in cart", e);
        }
    }

    public void deleteProductsFromCart(int userId, List<Integer> productIds) {
        try {
            cartDao.deleteProductsFromCart(userId, productIds);
        } catch (Exception e) {
            LOGGER.error("Error deleting products from cart", e);
            throw new RuntimeException("Error deleting products from cart", e);
        }
    }

    public void updateProductQuantities(int userId, Map<String, Integer> updatedQuantitiesMap) {
        try {
            cartDao.updateProductQuantity(userId, updatedQuantitiesMap);
        } catch (Exception e) {
            LOGGER.error("Error updating product quantities", e);
            throw new RuntimeException("Error updating product quantities", e);
        }
    }
}

