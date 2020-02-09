import { take, put, call } from 'redux-saga/effects';

import {
    ADD_APPOINTEMNT_REQUIREMENT
} from './constants';

import AppointmentRequirementService from '../../services/AppointmentRequirementService';

import {
} from './actions';

import {
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';

export function* addAppointmentRequirement() {
    const { payload } = yield take(ADD_APPOINTEMNT_REQUIREMENT);
    const { data } = yield call(AppointmentRequirementService.addAppointmentRequirement, payload);
    if (data === 'Appointment requirement submited succesfully') {
        yield put(putSuccessMsg(data));
        yield put(putSuccessMsg(null));
    } else {
        yield put(putErrorMsg(data));
        yield put(putErrorMsg(null));
    }

}