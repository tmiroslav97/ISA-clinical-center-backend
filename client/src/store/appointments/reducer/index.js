import {
    PUT_APPOINTMENT_TYPES,
    PUT_IS_FETCH_APPOINTMENT_TYPES,
    PUT_APPOINTMENT,
    PUT_IS_FETCH_APPOINTMENT
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    appointmentTypes: [],
    isFetchAppointmentTypes: false,
    appointment: null,
    isFetchAppointment: false
};

const appointmentReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_APPOINTMENT_TYPES]: computationFunctions.putAppointmentTypes,
    [PUT_IS_FETCH_APPOINTMENT_TYPES]: computationFunctions.putIsFetchAppointmentTypes,
    [PUT_APPOINTMENT]: computationFunctions.putAppointment,
    [PUT_IS_FETCH_APPOINTMENT]: computationFunctions.putIsFetchAppointment
};

export default appointmentReducer;