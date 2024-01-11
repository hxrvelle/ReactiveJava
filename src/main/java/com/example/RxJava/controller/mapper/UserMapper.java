package com.example.RxJava.controller.mapper;

import com.example.RxJava.controller.dto.UserIncomingDto;
import com.example.RxJava.controller.dto.UserOutgoingDto;
import com.example.RxJava.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserOutgoingDto userToUserOutgoingDto(User user);
    User userIncomingDtoToUser(UserIncomingDto userIncomingDto);
}
