import {
    PUT_PRESCRIPTIONS_DATA,
    PUT_IS_FETCH_PRESCRIPTIONS
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    prescriptions: [],
    isFetchPrescriptions: false
};

const prescriptionReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_PRESCRIPTIONS_DATA]: computationFunctions.putPrescriptionsData,
    [PUT_IS_FETCH_PRESCRIPTIONS]: computationFunctions.putIsFetchPrescriptions
};

export default prescriptionReducer;