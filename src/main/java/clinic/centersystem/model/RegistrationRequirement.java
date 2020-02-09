package clinic.centersystem.model;


import clinic.centersystem.common.db.DbColumnConstants;
import clinic.centersystem.common.db.DbTableConstants;
import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbTableConstants.REGISTRATIONREQUIREMENT)
public class RegistrationRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbColumnConstants.ID, unique = true, nullable = false)
    private Long id;

    @Column(name = DbColumnConstants.FIRSTNAME, nullable = false)
    private String firstName;

    @Column(name = DbColumnConstants.LASTNAME, nullable = false)
    private String lastName;

    @Column(name = DbColumnConstants.EMAIL, nullable = false)
    private String email;

    @Column(name = DbColumnConstants.PASSWORD, nullable = false)
    private String password;

    @Column(name = DbColumnConstants.PASSWORD2, nullable = false)
    private String password2;

    @Column(name = DbColumnConstants.ADDRESS, nullable = false)
    private String address;

    @Column(name = DbColumnConstants.COUNTRY, nullable = false)
    private String country;

    @Column(name = DbColumnConstants.CITY, nullable = false)
    private String city;

    @Column(name = DbColumnConstants.PHONENUM, nullable = false)
    private String phoneNum;

    @Column(name = DbColumnConstants.UNOIP, nullable = false)
    private String unoip;

    @Version
    private Long version;

}