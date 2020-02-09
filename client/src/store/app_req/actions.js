import {
    ADD_APPOINTEMNT_REQUIREMENT
} from './constants';

export const addAppointmentRequirement = payload => ({
    type: ADD_APPOINTEMNT_REQUIREMENT,
    payload
});