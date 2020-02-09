import {
    PUT_PATIENTS_DATA,
    PUT_IS_FETCH_PATIENTS,
    PUT_PATIENT
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    patients:[],
    isFetchPatients:false,
    patient: null
};

const patientsReducer = (state = initialState, { type, payload })=>{
    if(actionHandler.hasOwnProperty(type)){
        return actionHandler[type](state,payload);
    }
    return state;
};

const actionHandler = {
    [PUT_PATIENTS_DATA]: computationFunctions.putPatientsData,
    [PUT_IS_FETCH_PATIENTS]: computationFunctions.putIsFetchPatients,
    [PUT_PATIENT]: computationFunctions.putPatient
  };

export default patientsReducer;