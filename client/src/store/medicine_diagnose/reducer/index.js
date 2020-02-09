import {
    PUT_DIAGNOSE_DATA,
    PUT_MEDICINE_DATA,
    PUT_IS_FETCH_DIAGNOSE,
    PUT_IS_FETCH_MEDICINE,
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    medicines: [],
    isFetchMedicines: false,
    diagnoses: [],
    isFetchDiagnoses: false
};

const medicineDiagnoseReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_DIAGNOSE_DATA]: computationFunctions.putDiagnoseData,
    [PUT_IS_FETCH_DIAGNOSE]: computationFunctions.putIsFetchDiagnoses,
    [PUT_MEDICINE_DATA]: computationFunctions.putMedicineData,
    [PUT_IS_FETCH_MEDICINE]: computationFunctions.putIsFetchMedicines
};

export default medicineDiagnoseReducer;