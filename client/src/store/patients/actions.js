import {
    FETCH_PATIENTS_DATA,
    FETCH_PATIENTS_DATA_BY_CLINIC_ID,
    PUT_PATIENTS_DATA,
    PUT_IS_FETCH_PATIENTS,
    FETCH_PATIENT_BY_APP,
    PUT_PATIENT,
    FETCH_PATIENTS_PAGINATION
} from './constants';

export const fetchPatientsPagination = payload => ({
    type: FETCH_PATIENTS_PAGINATION,
    payload
});

export const fetchPatientByApp = payload => ({
    type: FETCH_PATIENT_BY_APP,
    payload
});

export const putPatient = payload => ({
    type: PUT_PATIENT,
    payload
});

export const fetchPatientsDataByClinicId = payload => ({
    type: FETCH_PATIENTS_DATA_BY_CLINIC_ID,
    payload
});

export const fetchPatientsData = payload => ({
    type: FETCH_PATIENTS_DATA,
    payload
});

export const putPatientsData = payload => ({
    type: PUT_PATIENTS_DATA,
    payload
});

export const putIsFetchPatients = payload => ({
    type: PUT_IS_FETCH_PATIENTS,
    payload
});
