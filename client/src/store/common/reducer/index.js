import {
    PUT_ERROR_MSG,
    PUT_SUCCESS_MSG,
    PUT_WARN_MSG,
    PUT_INFO_MSG,
    PUT_PAGE_CNT,
    PUT_SEL_PAGE_CNT
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    error: null,
    success: null,
    warn: null,
    info: null,
    pageCnt: 0,
    selPageCnt: 0
};

const commonReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_ERROR_MSG]: computationFunctions.putError,
    [PUT_SUCCESS_MSG]: computationFunctions.putSuccess,
    [PUT_WARN_MSG]: computationFunctions.putWarn,
    [PUT_INFO_MSG]: computationFunctions.putInfo,
    [PUT_SEL_PAGE_CNT]: computationFunctions.putSelPageCnt,
    [PUT_PAGE_CNT]: computationFunctions.putPageCnt
};

export default commonReducer;