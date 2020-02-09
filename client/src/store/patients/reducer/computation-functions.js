export function putPatientsData(state, payload) {
    return {
        ...state,
        patients: payload
    };
}

export function putIsFetchPatients(state, payload) {
    return {
        ...state,
        isFetchPatients: payload
    };
}

export function putPatient(state, payload) {
    return {
        ...state,
        patient: payload
    };
}