package clinic.centersystem.service;

import clinic.centersystem.common.TimeProvider;
import clinic.centersystem.model.User;
import clinic.centersystem.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
        } else {
            System.out.println();
            return user;
        }
    }

    public User changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        String email = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '" + email + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPassword));
        } else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return null;
        }

        LOGGER.debug("Changing password for user '" + email + "'");

        User user = (User) loadUserByUsername(email);

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setFirstLog(false);
        user.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        user = userRepository.save(user);
        return user;
    }
}
