import {
    ADD_DIAGNOSE,
    ADD_MEDICINE,
    PUT_DIAGNOSE_DATA,
    PUT_IS_FETCH_DIAGNOSE,
    PUT_MEDICINE_DATA,
    PUT_IS_FETCH_MEDICINE,
    FETCH_DIAGNOSE_DATA,
    FETCH_MEDICINE_DATA,
    FETCH_DIAGNOSES_ALL,
    FETCH_MEDICINES_ALL
} from './constants';

export const fetchDiagnosesAll = payload => ({
    type: FETCH_DIAGNOSES_ALL,
    payload
});

export const fetchMedicinesAll = payload => ({
    type: FETCH_MEDICINES_ALL,
    payload
});

export const fetchMedicineData = payload => ({
    type: FETCH_MEDICINE_DATA,
    payload
});

export const fetchDiagnoseData = payload => ({
    type: FETCH_DIAGNOSE_DATA,
    payload
});

export const putIsFetchDiagnose = payload => ({
    type: PUT_IS_FETCH_DIAGNOSE,
    payload
});

export const putIsFetchMedicine = payload => ({
    type: PUT_IS_FETCH_MEDICINE,
    payload
});

export const putMedicineData = payload => ({
    type: PUT_MEDICINE_DATA,
    payload
});

export const putDiagnoseData = payload => ({
    type: PUT_DIAGNOSE_DATA,
    payload
});

export const addMedicine = payload => ({
    type: ADD_MEDICINE,
    payload
});

export const addDiagnose = payload => ({
    type: ADD_DIAGNOSE,
    payload
});