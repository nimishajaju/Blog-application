package com.project.blog.application.service;

import com.project.blog.application.payloads.UserDTO;
import com.project.blog.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, Integer user_id);

    UserDTO getById(Integer user_id);

    List<UserDTO> getAllUser();

    void deleteUserById(Integer user_id);

}
