package com.neoris.challenge.controller;

import com.neoris.challenge.validatior.EmailValidator;
import com.neoris.challenge.validatior.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.neoris.challenge.dto.UserDTO;
import com.neoris.challenge.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Api(value = "User Management System", description = "Operations pertaining to user management in the application")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @ApiOperation(value = "View a list of available users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @ApiOperation(value = "Get a user by Id")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @ApiOperation(value = "Add a user")
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO, BindingResult result) {
        emailValidator.validate(userDTO, result);
        passwordValidator.validate(userDTO, result);

        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update a user")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO, BindingResult result) {
        emailValidator.validate(userDTO, result);
        passwordValidator.validate(userDTO, result);

        return ResponseEntity.ok(userService.updateUser(id, userDTO));
    }

    @ApiOperation(value = "Delete a user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
