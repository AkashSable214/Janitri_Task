package com.patientInfo.service;

import com.patientInfo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	User createUser(User user);

	List<User> getAllUsers();

	Optional<User> getUserById(Long id);

	Optional<User> updateUser(Long id, User userDetails);

	boolean deleteUser(Long id);
}
