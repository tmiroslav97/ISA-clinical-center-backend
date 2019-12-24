package clinic.centersystem.converter;

import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.PatientResponse;
import clinic.centersystem.model.Authority;
import clinic.centersystem.model.Patient;
import clinic.centersystem.model.RegistrationRequirement;
import clinic.centersystem.model.enumeration.RoleEnum;

import java.util.ArrayList;

public class PatientConverter {

    public static Patient toCreatePatientFromRequest(RegistrationRequirement registrationRequirement) {

        return Patient.patientBuilder()
                .email(registrationRequirement.getEmail())
                .password(registrationRequirement.getPassword())
                .firstName(registrationRequirement.getFirstName())
                .lastName(registrationRequirement.getLastName())
                .address(registrationRequirement.getAddress())
                .city(registrationRequirement.getCity())
                .country(registrationRequirement.getCountry())
                .isActivated(false)
                .enabled(true)
                .isFirstLog(true)
                .phoneNum(registrationRequirement.getPhoneNum())
                .role(RoleEnum.ROLE_PATIENT)
                .unoip(registrationRequirement.getUnoip())
                .build();
    }

    public static PatientResponse toCreatePatientResponseFromPatient(Patient patient) {
        return PatientResponse.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .email(patient.getEmail())
                .country(patient.getCountry())
                .city(patient.getCity())
                .address(patient.getAddress())
                .phoneNum(patient.getPhoneNum())
                .unoip(patient.getUnoip())
                .build();
    }
}

