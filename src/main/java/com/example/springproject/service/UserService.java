package com.example.springproject.service;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.User;
import com.example.springproject.exception.UserNotFoundException;
import com.example.springproject.model.request.UserRequestDTO;
import com.example.springproject.model.response.UserResponseDTO;
import com.example.springproject.repositories.CartRepository;
import com.example.springproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private CartService cartService;

    public UserResponseDTO addUser(UserRequestDTO userRequestDto) {
        User user = userRequestDto.build();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCart(cartService.createNewCart().setUser(user));
        userRepository.save(user);
        return new UserResponseDTO(user);
    }

    public UserResponseDTO findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        User newUser = user.orElse(null);
        if (newUser == null) {
            throw new UserNotFoundException(String.format("User %s not found", id));
        } else {
            return new UserResponseDTO(newUser);
        }
    }


    public List<UserResponseDTO> getAllUsers() {
        List<UserResponseDTO> userDTO = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDTO.add(new UserResponseDTO(user));
        }
        return userDTO;
    }

    public void deleteUserByLogin(String login) {
        User user = userRepository.findByLoginContainingIgnoreCase(login);
        if (user == null) {
            throw new UserNotFoundException(String.format("User login:%s not found", login));
        } else {
            userRepository.delete(user);
        }
    }
}
