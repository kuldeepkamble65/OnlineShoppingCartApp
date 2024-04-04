package org.example.Handler;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.Dao.ProductDao;
import org.example.Exception.ProductNotFoundException;
import org.example.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductHandler {
    private static final Logger LOGGER = LogManager.getLogger(ProductHandler.class);

    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        try {
            LOGGER.info("start::ProductHandler::getAllProducts::");

            LOGGER.info("Fetching all products");
            return productDao.getAllProducts();
        } catch (Exception e) {
            LOGGER.error("Error fetching all products", e);
            throw new RuntimeException("Error fetching all products", e);
        }
    }

    public void addOrUpdateProduct(Product product) {
        try {
            LOGGER.info("start::ProductHandler::addOrUpdateProduct::");

            Product existingProduct = productDao.findProductByName(product.getName());
            if (existingProduct != null) {
                existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                productDao.updateProductByName(existingProduct);
            } else {
                productDao.saveProduct(product);
            }

            LOGGER.info("Product added or updated successfully!");
        } catch (Exception e) {
            LOGGER.error("Error adding or updating product", e);
            throw new RuntimeException("Error adding or updating product", e);
        }
    }

    public void removeProduct(String productName) {
        try {
            LOGGER.info("start::ProductHandler::removeProduct::");

            Product product = productDao.findProductByName(productName);
            if (product != null) {
                productDao.removeProduct(product);
            } else {
                throw new RuntimeException("Product not found with name: " + productName);
            }

            LOGGER.info("Product removed successfully!");
        } catch (Exception e) {
            LOGGER.error("Error removing product", e);
            throw new RuntimeException("Error removing product", e);
        }
    }

    public Product getProductById(int id) {
        try {
            LOGGER.info("start::ProductHandler::getProductById::");

            LOGGER.info("Fetching product by ID");
            return productDao.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error fetching product by ID", e);
            throw new RuntimeException("Error fetching product by ID", e);
        }
    }

    public List<Product> searchProductsInCategory(String category, String productName)   {
      try{
          return productDao.searchProductsInCategory(category, productName);
      }catch (ProductNotFoundException e){
          throw new ProductNotFoundException("Product not found");
      }catch (RuntimeException e){
          throw new RuntimeException("internal server error");
      }

    }


}
