package com.kraytsman.fcm.services;

import com.kraytsman.fcm.entities.Token;
import com.kraytsman.fcm.entities.User;
import com.kraytsman.fcm.exceptions.NotFoundException;
import com.kraytsman.fcm.repositories.TokenRepository;
import com.kraytsman.fcm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User addToken(String userId, String token) {
        User user = get(userId);
        Token savedToken = tokenRepository.save(new Token(token));
        user.getTokens().add(savedToken);
        return userRepository.save(user);
    }

    @Override
    public User get(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format("User with id %s was not found", id)));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> get(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    public User delete(String userId) {
        User user = this.get(userId);
        userRepository.deleteById(userId);
        return user;
    }
}
