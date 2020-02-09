import { take, put, call } from 'redux-saga/effects';

import {
    ADD_DOCTOR,
    FETCH_DOCTORS_DATA,
    FETCH_DOCTORS_DATA_ON_CLINIC,
    SEARCH_DOCTOR,
    SEARCH_DOCTORS,
    FETCH_DOCTORS_BY_CLINICID,
    DELETE_DOCTOR
} from './constants';

import DoctorService from '../../services/DoctorService';

import {
    putIsFetchDoctors,
    putDoctorsData,
    putPageCount
} from './actions';

import {
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';

export function* fetchDoctorsData() {
    const { payload } = yield take(FETCH_DOCTORS_DATA);
    yield put(putIsFetchDoctors(false));
    const { doctors } = yield call(DoctorService.fetchDoctorsByClinicId,payload);
    yield put(putDoctorsData(doctors));
    yield put(putIsFetchDoctors(true));
}

export function* fetchDoctorsByClinicId() {
    //eslint-disable-next-line
    const { payload } = yield take(FETCH_DOCTORS_BY_CLINICID);
    yield put(putIsFetchDoctors(false));
    const { doctors } = yield call(DoctorService.fetchDoctorsByClinicId, payload);
    yield put(putDoctorsData(doctors));
    yield put(putIsFetchDoctors(true));
}

export function* addDoctor() {
    const { payload } = yield take(ADD_DOCTOR);
    const {response} = yield call(DoctorService.addDoctor, payload);
    if(response==='Successfully added doctor on clinic'){
        yield put(putSuccessMsg(response));
        yield put(putSuccessMsg(null));
        yield put(putIsFetchDoctors(false));
        const { doctors } = yield call(DoctorService.fetchDoctorsByClinicId, {clinicId:payload.clinicId});
        yield put(putDoctorsData(doctors));
        yield put(putIsFetchDoctors(true));
    } else {
        yield put(putErrorMsg(response));
        yield put(putErrorMsg(null));
    }
}

export function* deleteDoctor() {
    const { payload } = yield take(DELETE_DOCTOR);
    const { response } = yield call(DoctorService.deleteDoctor, payload);
    if(response === 'Successfully deleted doctor'){
        yield put(putSuccessMsg(response));
        yield put(putSuccessMsg(null));
        yield put(putIsFetchDoctors(false));
        const { doctors } = yield call(DoctorService.fetchDoctorsByClinicId, {clinicId:payload.clinicId});
        yield put(putDoctorsData(doctors));
        yield put(putIsFetchDoctors(true));
    }else {
        yield put(putErrorMsg(response));
        yield put(putErrorMsg(null));
    }
}


export function* searchDoctor() {
    const { payload } = yield take(SEARCH_DOCTOR);
    yield put(putIsFetchDoctors(false));
    const { data } = yield call(DoctorService.searchDoctor, payload);
    yield put(putDoctorsData(data.doctors));
    yield put(putPageCount(data.putPageCount));
    yield put(putIsFetchDoctors(true));
}

export function* searchDoctors() {
    const { payload } = yield take(SEARCH_DOCTORS);
    yield put(putIsFetchDoctors(false));
    const { data } = yield call(DoctorService.searchDoctors,payload);
    yield put(putDoctorsData(data));
    yield put(putIsFetchDoctors(true));
}
