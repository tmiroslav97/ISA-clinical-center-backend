import {
    PUT_ABS_HOL_REQUESTS,
    PUT_IS_FETCH_ABS_HOL_REQUESTS
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    absholrequests: [],
    isFetchAbsHolRequests: false
};

const absenceHolidayReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_ABS_HOL_REQUESTS]: computationFunctions.putAbsHolRequests,
    [PUT_IS_FETCH_ABS_HOL_REQUESTS]: computationFunctions.putIsFetchAbsHolRequests
};

export default absenceHolidayReducer;