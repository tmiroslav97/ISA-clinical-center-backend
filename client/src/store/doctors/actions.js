import {
    FETCH_DOCTORS_DATA,
    PUT_DOCTORS_DATA,
    ADD_DOCTOR,
    DELETE_DOCTOR,
    PUT_IS_FETCH_DOCTORS_DATA,
    SEARCH_DOCTOR,
    SEARCH_DOCTORS,
    PUT_PAGE_COUNT,
    FETCH_DOCTORS_BY_CLINICID
} from './constants';

export const putPageCount = payload => ({
    type: PUT_PAGE_COUNT,
    payload
});


export const putIsFetchDoctors = payload => ({
    type: PUT_IS_FETCH_DOCTORS_DATA,
    payload
});

export const putDoctorsData = payload => ({
    type: PUT_DOCTORS_DATA,
    payload
});

export const fetchDoctorsByClinicId = payload => ({
    type: FETCH_DOCTORS_BY_CLINICID,
    payload
});

export const fetchDoctorsData = payload => ({
    type: FETCH_DOCTORS_DATA,
    payload
});

export const addDoctor = payload => ({
    type: ADD_DOCTOR,
    payload
});

export const searchDoctor = payload => ({
    type: SEARCH_DOCTOR,
    payload
});

export const searchDoctors = payload => ({
    type: SEARCH_DOCTORS,
    payload
});

export const deleteDoctor = payload => ({
    type: DELETE_DOCTOR,
    payload
});

