import {
    FETCH_ABS_HOL_REQUESTS,
    PUT_IS_FETCH_ABS_HOL_REQUESTS,
    PUT_ABS_HOL_REQUESTS,
    ABS_HOL_REQUEST
} from './constants';

export const fetchAbsHolRequests = payload => ({
    type: FETCH_ABS_HOL_REQUESTS,
    payload
});

export const absHolRequest = payload => ({
    type: ABS_HOL_REQUEST,
    payload
});

export const putAbsHolRequest = payload => ({
    type: PUT_ABS_HOL_REQUESTS,
    payload
});

export const putIsFetchAbsHolRequests = payload => ({
    type: PUT_IS_FETCH_ABS_HOL_REQUESTS,
    payload
});