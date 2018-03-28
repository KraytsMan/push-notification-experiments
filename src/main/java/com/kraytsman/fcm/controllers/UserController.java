package com.kraytsman.fcm.controllers;

import com.kraytsman.fcm.entities.User;
import com.kraytsman.fcm.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping(value = "{userId}/tokens/{token}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    User addToken(@PathVariable String userId, @PathVariable String token) {
        return userService.addToken(userId, token);
    }

    @GetMapping(value = "{userId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    User get(@PathVariable String userId) {
        return userService.get(userId);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    List<User> getAll() {
        return userService.getAll();
    }

}
