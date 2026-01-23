package com.project.blog.application.controller;

import com.project.blog.application.payloads.ResponseApi;
import com.project.blog.application.payloads.UserDTO;
import com.project.blog.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
  public  ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
        UserDTO createUserdto= this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserdto, HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserDTO> updateUser (@Valid @RequestBody UserDTO userDTO, @PathVariable Integer user_id){
        UserDTO dto= userService.updateUser(userDTO,user_id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<ResponseApi> deleteUser(@PathVariable Integer user_id){
        userService.deleteUserById(user_id);
        return new ResponseEntity<>(new ResponseApi("user deleted", true), HttpStatus.OK);

    }
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer user_id){
        UserDTO user= userService.getById(user_id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> users= userService.getAllUser();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

}
