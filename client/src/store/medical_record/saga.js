import { take, put, call } from 'redux-saga/effects';

import {
    FETCH_MEDICAL_RECORD,
    FETCH_MEDICAL_RECORD_BY_APP,
    EDIT_MEDICAL_RECORD
} from './constants';

import MedicalRecordService from '../../services/MedicalRecordService';

import {
    putIsFetchMedicalRecord,
    putMedicalRecord
} from './actions';

import {
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';

export function* editMedicalRecord() {
    const { payload } = yield take(EDIT_MEDICAL_RECORD);
    const { edit } = yield call(MedicalRecordService.editMedicalRecord, payload);
    if (edit === 'Successfuly edited') {
        yield put(putSuccessMsg(edit));
        yield put(putSuccessMsg(null));
    } else {
        yield put(putErrorMsg(edit));
        yield put(putErrorMsg(null));
    }
    yield put(putIsFetchMedicalRecord(false));
    const { data } = yield call(MedicalRecordService.fetchMedicalRecord, payload);
    yield put(putMedicalRecord(data));
    yield put(putIsFetchMedicalRecord(true));
}

export function* fetchMedicalRecord() {
    const { payload } = yield take(FETCH_MEDICAL_RECORD);
    yield put(putIsFetchMedicalRecord(false));
    const { data } = yield call(MedicalRecordService.fetchMedicalRecord, payload);
    yield put(putMedicalRecord(data));
    yield put(putIsFetchMedicalRecord(true));
}

export function* fetchMedicalRecordByApp() {
    const { payload } = yield take(FETCH_MEDICAL_RECORD_BY_APP);
    yield put(putIsFetchMedicalRecord(false));
    const { data } = yield call(MedicalRecordService.fetchMedicalRecordByApp, payload);
    yield put(putMedicalRecord(data));
    yield put(putIsFetchMedicalRecord(true));
}