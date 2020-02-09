import { take, put, call } from 'redux-saga/effects';

import {
    FETCH_APPOINTMENT_TYPE,
    ADD_APPOINTMENT_TYPE,
    DELETE_APPOINTMENT_TYPE,
    EDIT_APPOINTMENT_TYPE,
    SEARCH_APPOINTMENT_TYPE,
    FETCH_APPOINTMENT
} from './constants';

import AppointmentTypeService from '../../services/AppointmentTypeService';

import {
    putAppointmentTypes,
    putIsFetchAppointmentTypes,
    putAppointment,
    putIsFetchAppointment
} from './actions';

export function* fetchAppointment() {
    const { payload } = yield take(FETCH_APPOINTMENT);
    yield put(putIsFetchAppointment(false));
    const { data } = yield call(AppointmentTypeService.fetchAppointment, payload);
    yield put(putAppointment(data));
    yield put(putIsFetchAppointment(true));
}

export function* fetchAppointmentType() {
    const { payload } = yield take(FETCH_APPOINTMENT_TYPE);
    yield put(putIsFetchAppointmentTypes(false));
    const { appointmentTypes } = yield call(AppointmentTypeService.fetchAppointmentType, payload);
    yield put(putAppointmentTypes(appointmentTypes));
    yield put(putIsFetchAppointmentTypes(true));
}
export function* addAppointmentType() {
    const { payload } = yield take(ADD_APPOINTMENT_TYPE);
    const { data } = yield call(AppointmentTypeService.addAppointmentType, payload);
    yield put(putIsFetchAppointmentTypes(false));
    const { appointmentTypes } = yield call(AppointmentTypeService.fetchAppointmentType, {clinicId:payload.clinicId});
    yield put(putAppointmentTypes(appointmentTypes));
    yield put(putIsFetchAppointmentTypes(true));
}

export function* deleteAppointmentType() {
    const { payload } = yield take(DELETE_APPOINTMENT_TYPE);
    const { data } = yield call(AppointmentTypeService.deleteAppointmentType, {id:payload.id});
    yield put(putIsFetchAppointmentTypes(false));
    const { appointmentTypes } = yield call(AppointmentTypeService.fetchAppointmentType, {clinicId:payload.clinicId});
    yield put(putAppointmentTypes(appointmentTypes));
    yield put(putIsFetchAppointmentTypes(true));
}

export function* editAppointmentType() {
    const { payload } = yield take(EDIT_APPOINTMENT_TYPE);
    yield call(AppointmentTypeService.editAppointmentType, payload);
    yield put(putIsFetchAppointmentTypes(false));
    const { appointmentTypes } = yield call(AppointmentTypeService.fetchAppointmentType, {clinicId:payload.clinicId});
    yield put(putAppointmentTypes(appointmentTypes));
    yield put(putIsFetchAppointmentTypes(true));
}

export function* searchAppointmentType() {
        const { payload } = yield take(SEARCH_APPOINTMENT_TYPE);
        yield put(putIsFetchAppointmentTypes(false));
        const { data } = yield call(AppointmentTypeService.searchAppointmentType,payload);
        yield put(putAppointmentTypes(data));
        yield put(putIsFetchAppointmentTypes(true));
}

