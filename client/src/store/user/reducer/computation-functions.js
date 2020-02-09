export function putUserData(state, payload) {
    return {
        ...state,
        data: payload
    };
}
  
export function putUserToken(state, payload) {
    return {
        ...state,
        token: payload
    };
}

export function putUserId(state, payload) {
    return {
        ...state,
        id: payload
    };
}

export function putIsFetchUserData(state, payload) {
    return {
        ...state,
        isFetchUserData: payload
    };
}
