export function putDoctorsData(state, payload) {
    return {
        ...state,
        doctors: payload
    };
}

export function putIsFetchDoctors(state, payload) {
    return {
        ...state,
        isFetchDoctors: payload
    };
}

export function putPageCount(state, payload) {
    return {
        ...state,
        pageCount: payload
    };
}