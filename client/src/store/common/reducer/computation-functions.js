export function putError(state, payload) {
    return {
        ...state,
        error: payload
    };
}

export function putSuccess(state, payload) {
    return {
        ...state,
        success: payload
    };
}


export function putWarn(state, payload) {
    return {
        ...state,
        warn: payload
    };
}

export function putInfo(state, payload) {
    return {
        ...state,
        info: payload
    };
}

export function putPageCnt(state, payload) {
    return {
        ...state,
        pageCnt: payload
    };
}

export function putSelPageCnt(state, payload) {
    return {
        ...state,
        selPageCnt: payload
    };
}