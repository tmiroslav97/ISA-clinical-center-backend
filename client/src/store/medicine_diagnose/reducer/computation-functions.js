export function putDiagnoseData(state, payload) {
    return {
        ...state,
        diagnoses: payload
    };
}

export function putIsFetchDiagnoses(state, payload) {
    return {
        ...state,
        isFetchDiagnoses: payload
    };
}

export function putMedicineData(state, payload) {
    return {
        ...state,
        medicines: payload
    };
}

export function putIsFetchMedicines(state, payload) {
    return {
        ...state,
        isFetchMedicines: payload
    };
}