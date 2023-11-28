package com.neoris.challenge.util;

import com.neoris.challenge.dto.PhoneDTO;
import com.neoris.challenge.dto.UserDTO;
import com.neoris.challenge.entity.Phone;
import com.neoris.challenge.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoMapper {

    public UserDTO convertToUserDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhones(convertToPhoneDtoList(user.getPhones()));
        userDto.setCreated(user.getCreated());
        userDto.setModified(user.getModified());
        userDto.setLastLogin(user.getLastLogin());
        userDto.setToken(user.getToken());
        userDto.setActive(user.isActive());
        return userDto;
    }

    public User convertToUserEntity(UserDTO userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhones(convertToPhoneEntityList(userDto.getPhones()));
        user.setCreated(userDto.getCreated());
        user.setModified(userDto.getModified());
        user.setLastLogin(userDto.getLastLogin());
        user.setToken(userDto.getToken());
        user.setActive(userDto.isActive());
        return user;
    }

    private List<PhoneDTO> convertToPhoneDtoList(List<Phone> phones) {
        if (phones == null) {
            return null;
        }
        return phones.stream()
                .map(this::convertToPhoneDto)
                .collect(Collectors.toList());
    }

    private PhoneDTO convertToPhoneDto(Phone phone) {
        PhoneDTO phoneDto = new PhoneDTO();
        phoneDto.setId(phone.getId());
        phoneDto.setNumber(phone.getNumber());
        phoneDto.setCitycode(phone.getCitycode());
        phoneDto.setCountrycode(phone.getCountrycode());
        return phoneDto;
    }

    public List<Phone> convertToPhoneEntityList(List<PhoneDTO> phoneDtos) {
        if (phoneDtos == null) {
            return null;
        }
        return phoneDtos.stream()
                .map(this::convertToPhoneEntity)
                .collect(Collectors.toList());
    }

    private Phone convertToPhoneEntity(PhoneDTO phoneDto) {
        Phone phone = new Phone();
        phone.setId(phoneDto.getId());
        phone.setNumber(phoneDto.getNumber());
        phone.setCitycode(phoneDto.getCitycode());
        phone.setCountrycode(phoneDto.getCountrycode());
        return phone;
    }
}

