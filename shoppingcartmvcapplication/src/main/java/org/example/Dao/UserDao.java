package org.example.Dao;

import org.example.model.User;

public interface UserDao {

    User getByEmail(String email);

}
