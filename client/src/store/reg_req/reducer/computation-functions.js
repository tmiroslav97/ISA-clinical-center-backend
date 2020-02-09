export function putRegReqsData(state, payload) {
    return {
        ...state,
        reqs: payload
    };
}

export function putIsFetchRegReqs(state, payload) {
    return {
        ...state,
        isFetchRegReqs: payload
    };
}

export function putRegReqsCnt(state, payload) {
    return {
        ...state,
        regReqsPageCnt: payload
    };
}