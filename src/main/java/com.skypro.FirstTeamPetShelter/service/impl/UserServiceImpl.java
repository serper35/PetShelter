package com.skypro.FirstTeamPetShelter.service.impl;

import com.skypro.FirstTeamPetShelter.exception.UserNotFoundException;
import com.skypro.FirstTeamPetShelter.model.User;
import com.skypro.FirstTeamPetShelter.repository.UserRepository;
import com.skypro.FirstTeamPetShelter.service.UserService;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User addUser(User user) {
        logger.info("Method addUser was invoked");

        User savedUser = userRepository.save(user);

        logger.info("User added: {}", savedUser);
        return savedUser;
    }

    @Override
    public User getUser(long id) {
        logger.info("Method getUser was invoked");
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<User> getAllUser() {
        logger.info("Method getAllUser was invoked");

        Collection<User> users = userRepository.findAll();
        logger.info("All users received");
        return users;
    }

    @Override
    public User editUser(User user) {
        logger.info("Method editUser was invoked");

        User updatedUser = userRepository.save(user);
        logger.info("User edited: {}", updatedUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(long id) {
        logger.info("Method deleteUser was invoked");

        userRepository.deleteById(id);
        logger.info("User deleted");
    }
}
