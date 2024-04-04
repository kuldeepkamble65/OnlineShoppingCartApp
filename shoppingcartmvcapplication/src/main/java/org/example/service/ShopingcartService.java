package org.example.service;

import org.example.db.DbQueries;
import org.example.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.db.DbQueries.GET_PRODUCT_BY_CATEGORY;


public interface ShopingcartService {
    List<Product> getProductByCategory(String category);

    List<Product> getAllProducts();



}
