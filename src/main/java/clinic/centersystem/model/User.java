package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import clinic.centersystem.model.enumeration.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = DbTableConstants.USERS)
@Inheritance(strategy = JOINED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbColumnConstants.ID, unique = true, nullable = false)
    private Long id;

    @Column(name = DbColumnConstants.EMAIL, unique = true, nullable = false)
    private String email;

    @Column(name = DbColumnConstants.PASSWORD, nullable = false)
    private String password;

    @Column(name = DbColumnConstants.FIRSTNAME, nullable = false)
    private String firstName;

    @Column(name = DbColumnConstants.LASTNAME, nullable = false)
    private String lastName;

    @Column(name = DbColumnConstants.ENABLED, nullable = false)
    private boolean enabled;

    @Column(name = DbColumnConstants.FIRSTLOG, nullable = false)
    private boolean isFirstLog;

    @Column(name = DbColumnConstants.LASTPASSWORDRESETDATE)
    private Timestamp lastPasswordResetDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = DbColumnConstants.USERAUTHORITY,
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    @Version
    private Long version = 0L;

    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}