package clinic.centersystem;


import clinic.centersystem.controller.RegistrationRequirementControllerIntegrationTest;
import clinic.centersystem.repository.RegistrationRequirementRepositoryIntegrationTest;
import clinic.centersystem.service.RegistrationRequirementServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegistrationRequirementControllerIntegrationTest.class,
        RegistrationRequirementServiceIntegrationTest.class,
        RegistrationRequirementRepositoryIntegrationTest.class})
public class TestSuiteIntegration {
}
