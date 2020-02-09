export function putClinicsData(state, payload) {
    return {
        ...state,
        clinics: payload
    };
}

export function putIsFetchClinicsData(state, payload) {
    return {
        ...state,
        isFetchClinics: payload
    };
}

export function putPageCount(state, payload) {
    return {
        ...state,
        pageCount: payload
    };
}