package com.neoris.challenge.service;

import com.neoris.challenge.dto.UserDTO;
import com.neoris.challenge.entity.User;
import com.neoris.challenge.exception.UserException;
import com.neoris.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.neoris.challenge.util.EntityDtoMapper;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    public UserDTO createUser(UserDTO userDto) throws UserException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserException("Email already registered");
        }

        User user = entityDtoMapper.convertToUserEntity(userDto);
        user.setId(UUID.randomUUID());
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setLastLogin(new Date());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setToken(UUID.randomUUID().toString());

        User savedUser = userRepository.save(user);
        return entityDtoMapper.convertToUserDto(savedUser);
    }

    public UserDTO getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        return entityDtoMapper.convertToUserDto(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(entityDtoMapper::convertToUserDto)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(UUID userId, UserDTO userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with ID " + userId + " not found"));

        if (!user.getEmail().equals(userDto.getEmail()) && userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new UserException("Email " + userDto.getEmail() + " already in use");
        }

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if (userDto.getPhones() != null && !userDto.getPhones().isEmpty()) {
            user.setPhones(entityDtoMapper.convertToPhoneEntityList(userDto.getPhones()));
        }

        user.setModified(new Date());

        User updatedUser = userRepository.save(user);
        return entityDtoMapper.convertToUserDto(updatedUser);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
