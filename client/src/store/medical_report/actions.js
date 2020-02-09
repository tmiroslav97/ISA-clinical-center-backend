import {
    ADD_MEDICAL_REPORT,
    PUT_DOCTORS_MEDICAL_REPORTS,
    PUT_IS_FETCH_MEDICAL_REPORT,
    EDIT_MEDICAL_REPORT,
    FETCH_DOCTORS_MEDICAL_REPORTS
} from './constants';

export const fetchDoctorsMedicalReports = payload => ({
    type: FETCH_DOCTORS_MEDICAL_REPORTS,
    payload
});

export const editMedicalReport = payload => ({
    type: EDIT_MEDICAL_REPORT,
    payload
});

export const putIsFetchMedicalReport = payload => ({
    type: PUT_IS_FETCH_MEDICAL_REPORT,
    payload
});

export const putDoctorsMedicalReports = payload => ({
    type: PUT_DOCTORS_MEDICAL_REPORTS,
    payload
});

export const addMedicalReport = payload => ({
    type: ADD_MEDICAL_REPORT,
    payload
});