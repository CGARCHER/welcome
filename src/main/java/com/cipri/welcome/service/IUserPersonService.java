package com.cipri.welcome.service;

import com.cipri.welcome.dto.RequestUpdateApplDTO;
import com.cipri.welcome.dto.UserDTO;

import java.util.List;

public interface IUserPersonService {
    List<UserDTO> getAll();
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(Integer id);
    boolean deleteUser(Integer id);
    boolean deleteUserByName(String name);
    List<UserDTO> getUserByName(String name);
    List<UserDTO> getUserByNameContaining(String name);
    UserDTO updateApplByName(RequestUpdateApplDTO requestUpdateApplDTO);

}
