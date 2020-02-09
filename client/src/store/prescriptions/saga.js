import { take, put, call } from 'redux-saga/effects';

import {
    FETCH_PRESCRIPTIONS,
    REWRITE_PRESCRIPTION
} from './constants';

import NurseService from '../../services/NurseService';

import {
    putIsFetchPrescriptions,
    putPrescriptionsData
} from './actions';

import {
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';


export function* rewritePrescription() {
    const { payload } = yield take(REWRITE_PRESCRIPTION);
    const { data } = yield call(NurseService.reweritePrescription, { nurseId: payload.nurseId, prescriptionId: payload.prescriptionId });
    if (data === 'Successfully rewrited prescription') {
        yield put(putSuccessMsg(data));
        yield put(putSuccessMsg(null));
    } else {
        yield put(putErrorMsg(data));
        yield put(putErrorMsg(null));
    }
    put(putIsFetchPrescriptions(false));
    const { prescriptions } = yield call(NurseService.fetchPrescriptions, { clinicId: payload.clinicId });
    yield put(putPrescriptionsData(prescriptions));
    put(putIsFetchPrescriptions(true));

}

export function* fetchPrescriptions() {
    const { payload } = yield take(FETCH_PRESCRIPTIONS);
    put(putIsFetchPrescriptions(false));
    const { prescriptions } = yield call(NurseService.fetchPrescriptions, payload);
    yield put(putPrescriptionsData(prescriptions));
    put(putIsFetchPrescriptions(true));
}

