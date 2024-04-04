package org.example.Handler;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.DTO.UserDto;
import org.example.Dao.UserDaoImpl;
import org.example.Exception.UserNotFoundException;
import org.example.Exception.ValidationException;
import org.example.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserHandler {

    private static final Logger LOGGER = LogManager.getLogger(UserHandler.class);

    @Autowired
    private UserDaoImpl userDaoImpl;


    public void validateUser(User user) throws ValidationException {
        if (StringUtils.isBlank(user.getFirstname()) || StringUtils.isBlank(user.getLastname()) || StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getMobileNo()) || StringUtils.isBlank(user.getPassword())) {
            throw new ValidationException("Invalid input. Please provide values for all user properties.");
        }
    }
    public String addUser(User user) {
        try {
            LOGGER.info("start::UserHandler::addUser::");

            userDaoImpl.addUser(user);

            LOGGER.info("User registered successfully!");
            return "User Register Successfully !";
        } catch (Exception e) {
            LOGGER.error("Error registering user", e);
            throw new RuntimeException("Error registering user", e);
        }
    }

    public UserDto displayUser(int userId){
        try{
            User user = userDaoImpl.findById(userId);

            if(user!= null){
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(user,userDto);
                return userDto;
            }else {
                throw new UserNotFoundException("User not found!");
            }
        }catch (Exception e){
            throw new RuntimeException("Error displaying user ", e);
        }
    }
}
