package com.skypro.FirstTeamPetShelter.service.Impl;

import com.skypro.FirstTeamPetShelter.exception.UserNotFoundException;
import com.skypro.FirstTeamPetShelter.model.UserApp;
import com.skypro.FirstTeamPetShelter.repository.UserRepository;
import com.skypro.FirstTeamPetShelter.service.UserService;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserApp addUser(UserApp userApp) {
        logger.info("Method addUser was invoked");

        UserApp savedUserApp = userRepository.save(userApp);

        logger.info("UserApp added: {}", savedUserApp);
        return savedUserApp;
    }

    @Override
    public UserApp getUser(long id) {
        logger.info("Method getUser was invoked");

        UserApp user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }

        logger.info("UserApp found");
        return user;
    }

    @Override
    public Collection<UserApp> getAllUser() {
        logger.info("Method getAllUser was invoked");

        Collection<UserApp> userApps = userRepository.findAll();
        logger.info("All userApps received");
        return userApps;
    }

    @Override
    public UserApp editUser(UserApp userApp) {
        logger.info("Method editUser was invoked");

        UserApp user = userRepository.findById(userApp.getId()).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }

        UserApp updatedUserApp = userRepository.save(userApp);
        logger.info("UserApp edited: {}", updatedUserApp);
        return updatedUserApp;
    }

    @Override
    public void deleteUser(long id) {
        logger.info("Method deleteUser was invoked");

        UserApp user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }

        userRepository.deleteById(id);
        logger.info("UserApp deleted");
    }

    @Override
    public UserApp getUserByTelegramId(long telegramId) {
        logger.info("Method getUserByTelegramId was invoked");

        return userRepository.findByUserTelegramId(telegramId);
    }
}
