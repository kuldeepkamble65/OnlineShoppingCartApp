package org.example.Dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.db.DbQueries;
import org.example.model.UserCartMapping;
import org.example.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CartDao implements CartDaoI {


    private static final Logger LOGGER = LogManager.getLogger(CartDao.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private DbQueries dbQueries;

    public void addProductToCart(UserCartMapping userCartMapping) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(userCartMapping);
        session.getTransaction().commit();
        session.close();
    }

    public void updateCart(UserCartMapping userCartMapping) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(userCartMapping);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException("Error updating cart", e);
        }
    }

    @Override
    public List<Product> getProductsByUserId(int userId) {
        try {
            LOGGER.info("start::CartDao::getProductsByUserId::");

            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();

                Query<UserCartMapping> query = session.createQuery(DbQueries.GET_PRODUCTS_BY_USER_ID, UserCartMapping.class);
                query.setParameter("userId", userId);

                List<UserCartMapping> cartMappings = query.getResultList();

                session.getTransaction().commit();

                List<Product> productsInCart = new ArrayList<>();
                for (UserCartMapping cartMapping : cartMappings) {
                    Product product = cartMapping.getProduct();
                    product.setQuantity(cartMapping.getQuantity());
                    productsInCart.add(product);
                }

                return productsInCart;
            }
        } catch (Exception e) {
            LOGGER.error("Error fetching products in cart", e);
            throw new RuntimeException("Error fetching products in cart", e);
        }
    }

    @Override
    public void deleteProductsFromCart(int userId, List<Integer> productIds) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<?> deleteQuery = session.createQuery(DbQueries.DELETE_PRODUCT_FROM_CART);
            deleteQuery.setParameter("userId", userId);
            deleteQuery.setParameter("productIds", productIds);
            deleteQuery.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Error deleting products from cart", e);
            throw new RuntimeException("Error deleting products from cart", e);
        }
    }



    public void updateProductQuantity(int userId, Map<String, Integer> updatedQuantitiesMap) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            for (Map.Entry<String, Integer> entry : updatedQuantitiesMap.entrySet()) {
                String productIdString = entry.getKey();
                Integer currentQuantity = entry.getValue();

                if (currentQuantity != null) {
                    try {
                        Integer productId = Integer.parseInt(productIdString);
                        Query<UserCartMapping> updateQuery = session.createQuery(DbQueries.UPDATE_PRODUCT_QUANTITY);
                        updateQuery.setParameter("userId", userId);
                        updateQuery.setParameter("productId", productId);
                        updateQuery.setParameter("quantity", currentQuantity);
                        updateQuery.executeUpdate();
                    } catch (NumberFormatException e) {
                        LOGGER.error("Error converting productId to Integer", e);
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            LOGGER.error("Error updating product quantities", e);
            throw new RuntimeException("Error updating product quantities", e);
        }
    }
}






