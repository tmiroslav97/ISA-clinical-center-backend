import {
    PUT_IS_FETCH_MEDICAL_RECORD,
    FETCH_MEDICAL_RECORD_BY_APP,
    PUT_MEDICAL_RECORD,
    FETCH_MEDICAL_RECORD,
    EDIT_MEDICAL_RECORD
} from './constants';

export const editMedicalRecord = payload => ({
    type: EDIT_MEDICAL_RECORD,
    payload
});

export const fetchMedicalRecordByApp = payload => ({
    type: FETCH_MEDICAL_RECORD_BY_APP,
    payload
});

export const fetchMedicalRecord = payload => ({
    type: FETCH_MEDICAL_RECORD,
    payload
});

export const putMedicalRecord = payload => ({
    type: PUT_MEDICAL_RECORD,
    payload
});

export const putIsFetchMedicalRecord = payload => ({
    type: PUT_IS_FETCH_MEDICAL_RECORD,
    payload
});
