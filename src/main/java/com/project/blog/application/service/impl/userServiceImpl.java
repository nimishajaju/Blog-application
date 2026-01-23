package com.project.blog.application.service.impl;

import com.project.blog.application.entity.User;
import com.project.blog.application.exceptions.ResourceNotFoundException;
import com.project.blog.application.payloads.UserDTO;
import com.project.blog.application.repository.UserRepository;
import com.project.blog.application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class userServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.dtotoUser(userDTO);
        User savedUser = this.userRepository.save(user);

        return this.userToDto(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if(user==null){
            throw new ResourceNotFoundException("User", "Id", user_id);
        }
//        User user1 = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        User savedUser= userRepository.save(user);

        return this.userToDto(savedUser) ;
    }

    @Override
    public UserDTO getById(Integer user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if(user==null){
            throw new ResourceNotFoundException("User", "Id", user_id);
        }


        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUser() {

        List<User> users= userRepository.findAll();
        List<UserDTO> userDTOS= users.stream()
                .map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    public void  deleteUserById(Integer user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if(user==null){
            throw new ResourceNotFoundException("User", "Id", user_id);
        }
        userRepository.delete(user);

    }

    private User dtotoUser(UserDTO userDTO){
        User user= modelMapper.map(userDTO, User.class);


//        User user= new User();
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());
        return user;
    }

    private UserDTO userToDto (User user){
        UserDTO userDTO= modelMapper.map(user,UserDTO.class);

//        UserDTO userDTO= new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail((user.getEmail()));
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}
