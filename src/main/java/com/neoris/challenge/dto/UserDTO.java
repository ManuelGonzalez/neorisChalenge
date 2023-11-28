package com.neoris.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private List<PhoneDTO> phones;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private String password;
    private boolean isActive;
}
