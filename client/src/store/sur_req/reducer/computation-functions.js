export function putSurReqData(state, payload) {
    return {
        ...state,
        surgeryReq: payload
    };
}

export function putIsFetchSurReqData(state, payload) {
    return {
        ...state,
        isFetchSurgeryReq: payload
    };
}

export function putSurReqPageCount(state, payload) {
    return {
        ...state,
        pageCount: payload
    };
}

export function putPickSurReq(state, payload) {
    return {
        ...state,
        pickSurReq: payload
    };
}

export function putPickedSurReq(state, payload) {
    return {
        ...state,
        pickedSurReq: payload
    };
}

export function putPickTerm(state, payload) {
    return {
        ...state,
        pickTerm: payload
    };
}

export function putPickedTerm(state, payload) {
    return {
        ...state,
        pickedTerm: payload
    };
}

export function putPickedRoom(state, payload) {
    return {
        ...state,
        pickedRoom: payload
    };
}