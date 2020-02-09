package clinic.centersystem;

import clinic.centersystem.controller.RegistrationRequirementControllerUnitTest;
import clinic.centersystem.repository.RegistrationRequirementRepositoryUnitTest;
import clinic.centersystem.service.RegistrationRequirementServiceUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RegistrationRequirementControllerUnitTest.class,
        RegistrationRequirementServiceUnitTest.class,
        RegistrationRequirementRepositoryUnitTest.class})
public class TestSuiteUnit {


}
