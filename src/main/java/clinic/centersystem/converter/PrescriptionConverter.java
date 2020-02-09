package clinic.centersystem.converter;

import clinic.centersystem.dto.response.PrescriptionResponse;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.model.MedicalReport;
import clinic.centersystem.model.Medicine;
import clinic.centersystem.model.Prescription;

public class PrescriptionConverter {

    public static Prescription toCreatePrescriptionFromMedicine(Medicine medicine, Clinic clinic, MedicalReport medicalReport){
        return  Prescription.builder()
                .isValidate(false)
                .medicine(medicine)
                .medicalReport(medicalReport)
                .clinic(clinic)
                .build();
    }

    public static PrescriptionResponse toCreatePrescriptionResponseFromPrescription(Prescription prescription) {
        return PrescriptionResponse.builder()
                .id(prescription.getId())
                .medicineName(prescription.getMedicine().getName())
                .medicineCode(prescription.getMedicine().getCode())
                .build();
    }
}
