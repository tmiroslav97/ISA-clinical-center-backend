export function putMedicalRecord(state, payload) {
    return {
        ...state,
        medicalRecord: payload
    };
}

export function putIsFetchMedicalRecord(state, payload) {
    return {
        ...state,
        isFetchMedicalRecord: payload
    };
}