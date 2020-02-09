import {
    PUT_DOCTORS_MEDICAL_REPORTS,
    PUT_IS_FETCH_MEDICAL_REPORT
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    medicalReport: null,
    isFetchMedicalReport: false,
    medicalReports: []
};

const medicalReportReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_IS_FETCH_MEDICAL_REPORT]: computationFunctions.putIsFetchMedicalReport,
    [PUT_DOCTORS_MEDICAL_REPORTS]: computationFunctions.putMedicalReports
};

export default medicalReportReducer;