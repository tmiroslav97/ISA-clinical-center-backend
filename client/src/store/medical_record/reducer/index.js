import {
    PUT_IS_FETCH_MEDICAL_RECORD,
    PUT_MEDICAL_RECORD
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    medicalRecord: null,
    isFetchMedicalRecord: false
};

const medicalRecordReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_MEDICAL_RECORD]: computationFunctions.putMedicalRecord,
    [PUT_IS_FETCH_MEDICAL_RECORD]: computationFunctions.putIsFetchMedicalRecord
};

export default medicalRecordReducer;