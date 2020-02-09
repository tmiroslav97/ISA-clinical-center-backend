import {
    FETCH_SUR_REQ_DATA,
    PUT_IS_FETCH_SUR_REQ_DATA,
    PUT_SUR_REQ_DATA,
    PUT_SUR_REQ_PAGE_COUNT,
    PUT_PICK_SUR_REQ,
    PUT_PICKED_SUR_REQ,
    FETCH_PICK_SUR_ROOM,
    PUT_PICK_TERM,
    PUT_PICKED_TERM,
    FETCH_PICK_DOC,
    PUT_PICKED_ROOM,
    FETCH_FINISH_RESERVATION
} from './constants';

export const fetchFinishReservation = payload => ({
    type: FETCH_FINISH_RESERVATION,
    payload
});

export const putPickedRoom = payload => ({
    type: PUT_PICKED_ROOM,
    payload
});

export const putPickTerm = payload => ({
    type: PUT_PICK_TERM,
    payload
});

export const putPickedTerm = payload => ({
    type: PUT_PICKED_TERM,
    payload
});

export const fetchPickDoc = payload => ({
    type: FETCH_PICK_DOC,
    payload
});

export const fetchPickSurRoom = payload => ({
    type: FETCH_PICK_SUR_ROOM,
    payload
});

export const putPickedSurReq = payload => ({
    type: PUT_PICKED_SUR_REQ,
    payload
});

export const putPickSurReq = payload => ({
    type: PUT_PICK_SUR_REQ,
    payload
});

export const putSurReqPageCount = payload => ({
    type: PUT_SUR_REQ_PAGE_COUNT,
    payload
});

export const fetchSurReqData = payload => ({
    type: FETCH_SUR_REQ_DATA,
    payload
});

export const putIsFetchSurReqData = payload => ({
    type: PUT_IS_FETCH_SUR_REQ_DATA,
    payload
});

export const putSurReqData = payload => ({
    type: PUT_SUR_REQ_DATA,
    payload
});