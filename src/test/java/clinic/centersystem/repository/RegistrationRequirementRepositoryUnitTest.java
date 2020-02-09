package clinic.centersystem.repository;

import clinic.centersystem.model.RegistrationRequirement;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class RegistrationRequirementRepositoryUnitTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RegistrationRequirementRepository registrationRequirementRepository;

    private static final Long VALID_ID = 1L;
    private static final Long NOT_VALID_ID = 15L;
    private static final String ADDRESS_VALID = "Safarikova 31";
    private static final String CITY_VALID = "Bijeljina";
    private static final String COUNTRY_VALID = "Bosna";
    private static final String EMAIL_VALID = "miroslavtomic@outlook.com";
    private static final String FIRST_NAME_VALID = "Miki";
    private static final String LAST_NAME_VALID = "Peric";
    private static final String PASSWORD_VALID = "123";
    private static final String PASSWORD2_VALID = "123";
    private static final String PHONE_NUME_VALID = "065987654";
    private static final String UNOIP_VALID = "1234543";
    private static final Long VERSION_VALID = 0L;

    @After
    public void cleanUp() {
        registrationRequirementRepository.deleteAll();
    }

    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingRegistrationRequirementById() {
        Optional<RegistrationRequirement> foundRegistrationRequirement = registrationRequirementRepository.findById(NOT_VALID_ID);

        assertFalse("Registration requirement is not found.", foundRegistrationRequirement.isPresent());
    }

    @Transactional
    @Test
    public void shouldReturnRegistrationRequirementWhenFindingExistingRegistrationRequirementById() {
        RegistrationRequirement registrationRequirement = new RegistrationRequirement(VALID_ID, FIRST_NAME_VALID, LAST_NAME_VALID,
                EMAIL_VALID, PASSWORD_VALID, PASSWORD2_VALID, ADDRESS_VALID, COUNTRY_VALID,
                CITY_VALID, PHONE_NUME_VALID, UNOIP_VALID, VERSION_VALID);
        try {
            testEntityManager.persist(registrationRequirement);
        } catch (Exception e) {
            System.out.println("For some reason exception from detached to persistent object is thrown");
        }
        testEntityManager.flush();

        Optional<RegistrationRequirement> foundRegistrationRequirement = registrationRequirementRepository.findById(VALID_ID);

        assertTrue("Flight is present.", foundRegistrationRequirement.isPresent());
        assertCorrectRegistrationRequirementIsReturned(foundRegistrationRequirement.get());
    }

    private void assertCorrectRegistrationRequirementIsReturned(RegistrationRequirement registrationRequirement) {
        assertEquals("Registration requirement contains correct id.", registrationRequirement.getId(), VALID_ID);
        assertEquals("Registration requirement contains correct first name.", registrationRequirement.getFirstName(), FIRST_NAME_VALID);
        assertEquals("Registration requirement contains correct last name.", registrationRequirement.getLastName(), LAST_NAME_VALID);
        assertEquals("Registration requirement contains correct email.", registrationRequirement.getEmail(), EMAIL_VALID);
        assertEquals("Registration requirement contains correct password.", registrationRequirement.getPassword(), PASSWORD_VALID);
        assertEquals("Registration requirement contains correct password2.", registrationRequirement.getPassword2(), PASSWORD2_VALID);
        assertEquals("Registration requirement contains correct address.", registrationRequirement.getAddress(), ADDRESS_VALID);
        assertEquals("Registration requirement contains correct country.", registrationRequirement.getCountry(), COUNTRY_VALID);
        assertEquals("Registration requirement contains correct city.",registrationRequirement.getCity(), CITY_VALID);
        assertEquals("Registration requirement contains correct phonenum.", registrationRequirement.getPhoneNum(), PHONE_NUME_VALID);
        assertEquals("Registration requirement contains correct unoip.",registrationRequirement.getUnoip(), UNOIP_VALID);
        assertEquals("Registration requirement contains correct version.", registrationRequirement.getVersion(), VERSION_VALID);
    }
}
