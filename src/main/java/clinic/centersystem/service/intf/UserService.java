package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByUsername(String email);

    List<User> findAll();

    User save();

    boolean existsByEmail(String email);
}
