package clinic.centersystem.converter;

import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.LoginUserResponse;
import clinic.centersystem.model.User;

public class UserConverter {

    public static LoginUserResponse toCreateUserLoginResponse(User user, String jwt) {
        return LoginUserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .token(jwt)
                .role(user.getRole().name())
                .isFirstLog(user.isFirstLog())
                .build();
    }
}
