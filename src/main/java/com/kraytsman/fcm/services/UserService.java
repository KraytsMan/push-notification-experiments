package com.kraytsman.fcm.services;

import com.kraytsman.fcm.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User save(User user);

    User addToken(String id, String token);

    User get(String id);

    List<User> getAll();

    Page<User> get(Pageable pageable);

    User delete(String user);
}
