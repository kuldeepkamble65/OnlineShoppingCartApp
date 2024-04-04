package org.example.Dao;

import org.example.Enum.Category;
import org.example.Exception.ProductNotFoundException;
import org.example.db.DbQueries;
import org.example.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DbQueries dbQueries;

    public List<Product> getAllProducts() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery(DbQueries.GET_ALL_PRODUCTS, Product.class).getResultList();
            session.getTransaction().commit();
            return products;
        }
    }
    public Product findProductByName(String productName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(DbQueries.FIND_PRODUCT_BY_NAME, Product.class)
                    .setParameter("name", productName)
                    .uniqueResult();
        }
    }

    public void saveProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public void updateProductByName(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        }
    }

    public void updateProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        }
    }

    public void removeProduct(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
        }
    }


    public Product findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching user by ID", e);
        }
    }

    public List<Product> searchProductsInCategory(String category, String productName) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Product> query = session.createQuery(DbQueries.SEARCH_PRODUCTS_IN_CATEGORY, Product.class);
            query.setParameter("category", Category.valueOf(category.toUpperCase()));
            query.setParameter("productName", productName);
            List<Product> products = query.getResultList();
            session.getTransaction().commit();

            if (products.isEmpty()) {
                throw new ProductNotFoundException("Product not found");
            }
            return products;
        } catch (ProductNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (RuntimeException e) {
            throw new RuntimeException("Internal server error", e);
        }
    }



}
