import {
    FETCH_REG_REQS_DATA,
    PUT_REG_REQS_DATA,
    PUT_IS_FETCH_REG_REQS,
    APPROVE_REG_REQ,
    REJECT_REG_REQ,
    PUT_REG_REQS_CNT
} from './constants';

export const putRegReqsCnt = payload => ({
    type: PUT_REG_REQS_CNT,
    payload
});

export const fetchRegReqsData = payload => ({
    type: FETCH_REG_REQS_DATA,
    payload
});

export const putRegReqsData = payload => ({
    type: PUT_REG_REQS_DATA,
    payload
});

export const putIsFetchRegReqs = payload => ({
    type: PUT_IS_FETCH_REG_REQS,
    payload
});

export const approveRegReq = payload => ({
    type: APPROVE_REG_REQ,
    payload
});

export const rejectRegReq = payload => ({
    type: REJECT_REG_REQ,
    payload
});