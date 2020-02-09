export function putAbsHolRequests(state, payload) {
    return {
        ...state,
        absholrequests: payload
    };
}

export function putIsFetchAbsHolRequests(state, payload) {
    return {
        ...state,
        isFetchAbsHolRequests: payload
    };
}