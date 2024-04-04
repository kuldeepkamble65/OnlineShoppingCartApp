package org.example.db;

import org.example.model.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbQueries {

    //TODO - fetch category wise data
    public static String GET_PRODUCT_BY_CATEGORY = "from Product p where p.category = :category";


    public static final String GET_ALL_PRODUCTS = "from Product";

   // public static final String GET_PRODUCT_BY_ID = "form Cart where product.id =:id";

    public static final  String FIND_PRODUCT_BY_NAME = "FROM Product WHERE name = :name";

    public static final String FIND_BY_ID ="from Cart where Product.id=:id";

    public static final String GET_BY_MAIL = "FROM User WHERE email = :email";

    public static final String GET_PRODUCTS_BY_USER_ID = "FROM UserCartMapping WHERE user.userId = :userId";

    public static final String DELETE_PRODUCT_FROM_CART = "DELETE FROM UserCartMapping WHERE user.userId = :userId AND product.id IN (:productIds)";

    public static final String UPDATE_PRODUCT_QUANTITY="UPDATE UserCartMapping SET quantity = :quantity WHERE user.userId = :userId AND product.id = :productId";

    public static final String SEARCH_PRODUCTS_IN_CATEGORY ="FROM Product p WHERE p.category = :category AND p.name LIKE :productName";
}
