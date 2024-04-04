package org.example.service;

import org.example.Enum.Category;
import org.example.db.DbQueries;
import org.example.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.db.DbQueries.GET_PRODUCT_BY_CATEGORY;

@Service
public class ShopingcartServiceImpl implements ShopingcartService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getProductByCategory(String category) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Product> query = session.createQuery(DbQueries.GET_PRODUCT_BY_CATEGORY, Product.class);
            query.setParameter("category", Category.valueOf(category.toUpperCase()));
            List<Product> products = query.getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}