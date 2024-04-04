package org.example.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.Dao.ProductDao;
import org.example.Exception.ProductNotFoundException;
import org.example.Handler.ProductHandler;
import org.example.model.Product;
import org.example.service.ShopingcartService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ShoppingcartController {

    private static final Logger LOGGER = LogManager.getLogger(ShoppingcartController.class);

   @Autowired
   private ProductHandler productHandler;

   @Autowired
   private ShopingcartService shopingcartService;

    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/addproduct")
    public String addproduct(){
        return "addproduct";
    }

    @PostMapping(value = "/addproduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productHandler.addOrUpdateProduct(product);
        return ResponseEntity.ok("Product Added successfully");
    }


    @GetMapping("/dashboard")
    public String getproducts(){
        LOGGER.info("Accessed dashboard endpoint");
        return "dashboard";
    }


    @GetMapping(value = "/dashboard/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> fetchProductsDetails(@RequestHeader(name = "category",required = true) String category) {
        LOGGER.debug("Fetching products for category");
        List<Product> products = new ArrayList<>();
        if (category != null) {
            products = shopingcartService.getProductByCategory(category);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/searchProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchProduct(
            @RequestHeader(name = "category", required = true) String category,
            @RequestHeader(name = "productName", required = true) String productName) {
        try {
            if (category != null && productName != null ) {
                List<Product> products = productHandler.searchProductsInCategory(category, productName);
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            LOGGER.error("Error occurred while searching for products.", e);
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            request.getSession(false).invalidate();
            return ResponseEntity.ok("Successfully logout");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error during logout");
        }
    }

    @GetMapping("/removeproduct")
    public String removeProducts(){
        return "removeproduct";
    }


    @GetMapping("/removeproduct/{productName}")
    public ResponseEntity<String> removeProductByName(@PathVariable String productName) {
        productHandler.removeProduct(productName);
        return ResponseEntity.ok("Product removed successfully");
    }


}
