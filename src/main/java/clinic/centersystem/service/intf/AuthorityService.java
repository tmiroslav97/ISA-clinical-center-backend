package clinic.centersystem.service.intf;

import clinic.centersystem.model.Authority;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AuthorityService {
    List<Authority> findById(Long id);
    List<Authority> findByName(String name);
}
