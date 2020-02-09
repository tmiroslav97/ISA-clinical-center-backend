export function putPrescriptionsData(state, payload) {
    return {
        ...state,
        prescriptions: payload
    };
}

export function putIsFetchPrescriptions(state, payload) {
    return {
        ...state,
        isFetchPrescriptions: payload
    };
}