package clinic.centersystem.service;

import clinic.centersystem.dto.request.UserEditReqDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.exception.UserNotFoundException;
import clinic.centersystem.model.User;
import clinic.centersystem.repository.UserRepository;
import clinic.centersystem.service.intf.AuthorityService;
import clinic.centersystem.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User save() {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public String editUserProfile(UserEditReqDTO userEditReqDTO){
        User user = this.findById(userEditReqDTO.getId());
        user.setFirstName(userEditReqDTO.getFirstName());
        user.setLastName(userEditReqDTO.getLastName());
        userRepository.save(user);
        return "Successfully edited users profile";
    }
}
