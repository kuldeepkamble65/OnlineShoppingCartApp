package org.example.Handler;

import org.example.DTO.LoginDTO;
import org.example.DTO.UserDto;

public interface UserHandlerI {

    UserDto login(LoginDTO loginDTO);

  //  UserDto displayUser(LoginDTO loginDTO);
}
