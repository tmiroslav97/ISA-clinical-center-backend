package clinic.centersystem.controller;

import clinic.centersystem.dto.request.UserEditReqDTO;
import clinic.centersystem.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping(method = POST, value = "/edit-user-profile")
    public ResponseEntity<String> editUserProfile(@RequestBody UserEditReqDTO userEditReqDTO){
        return  new ResponseEntity<>(userService.editUserProfile(userEditReqDTO), HttpStatus.OK);
    }
}
