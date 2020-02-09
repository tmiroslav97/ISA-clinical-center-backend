package clinic.centersystem.converter;

import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.LoginUserResponse;
import clinic.centersystem.model.User;

import java.util.stream.Collectors;

public class UserConverter {

    public static LoginUserResponse toCreateUserLoginResponse(User user, String jwt) {
        return LoginUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(jwt)
                .roles(user.getAuthorities().stream().map(authority -> authority.getName()).collect(Collectors.toList()))
                .firstLogin(user.isFirstLog())
                .build();
    }
}
