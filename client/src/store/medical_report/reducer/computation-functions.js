export function putMedicalReport(state, payload) {
    return {
        ...state,
        medicalReport: payload
    };
}

export function putIsFetchMedicalReport(state, payload) {
    return {
        ...state,
        isFetchMedicalReport: payload
    };
}

export function putMedicalReports(state, payload) {
    return {
        ...state,
        medicalReports: payload
    };
}