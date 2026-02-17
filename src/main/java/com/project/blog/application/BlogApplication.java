package com.project.blog.application;

import com.project.blog.application.entity.Role;
import com.project.blog.application.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(passwordEncoder.encode("nimi@123"));
//	}

	@Override
	public void run(String... args) throws Exception {

		String dbHash = "$2a$10$58GCjXya/KTlTHmEOkUM/uF4kR5s8u9yNqTRDfswJ7iLZyZmvWlUK"; // copy from DB

		System.out.println(passwordEncoder.matches("nimi@123", dbHash));

		Role role = new Role();
		role.setRoleId(501);
		role.setName("ROLE_ADMIN");

		Role role1= new Role();
		role1.setRoleId(502);
		role1.setName("ROLE_NORMAL");

		List<Role> roles= List.of(role,role1);
		roleRepo.saveAll(roles);
	}



}
