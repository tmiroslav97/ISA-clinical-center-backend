import {
    PUT_PRESCRIPTIONS_DATA,
    FETCH_PRESCRIPTIONS,
    PUT_IS_FETCH_PRESCRIPTIONS,
    REWRITE_PRESCRIPTION
} from './constants';

export const fetchPrescriptions = payload => ({
    type: FETCH_PRESCRIPTIONS,
    payload
});

export const putPrescriptionsData = payload => ({
    type: PUT_PRESCRIPTIONS_DATA,
    payload
});

export const rewritePrescription = payload => ({
    type: REWRITE_PRESCRIPTION,
    payload
});

export const putIsFetchPrescriptions = payload => ({
    type: PUT_IS_FETCH_PRESCRIPTIONS,
    payload
});
