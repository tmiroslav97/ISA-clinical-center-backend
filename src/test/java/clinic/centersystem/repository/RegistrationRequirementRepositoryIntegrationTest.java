package clinic.centersystem.repository;

import clinic.centersystem.model.RegistrationRequirement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@DataJpaTest
public class RegistrationRequirementRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RegistrationRequirementRepository registrationRequirementRepository;

    private static final Long VALID_ID = 1L;
    private static final Long NOT_VALID_ID = 15L;

    @Test
    public void shouldReturnEmptyOptionalWhenFindingNonExistingRegistrationRequirementById() {
        Optional<RegistrationRequirement> foundRegistrationRequirement = registrationRequirementRepository.findById(NOT_VALID_ID);

        assertFalse("Registration requirement is not found.", foundRegistrationRequirement.isPresent());
    }

    @Test
    public void shouldReturnMinusOneSizeWhenDeletingRegistrationRequirementById() {
        RegistrationRequirement regReq = testEntityManager.find(RegistrationRequirement.class, VALID_ID);
        assertNotNull("Registration requirement should not be null", regReq);

        registrationRequirementRepository.deleteById(VALID_ID);
        RegistrationRequirement regReq2 = testEntityManager.find(RegistrationRequirement.class, VALID_ID);

        assertNull("Registration requirement should be null", regReq2);
    }

}
