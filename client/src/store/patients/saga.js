import { take, put, call } from 'redux-saga/effects';
import { history } from '../../index';

import {
    FETCH_PATIENTS_DATA,
    FETCH_PATIENTS_DATA_BY_CLINIC_ID,
    FETCH_PATIENT_BY_APP,
    FETCH_PATIENTS_PAGINATION
} from './constants';

import PatientService from '../../services/PatientService';

import {
    putPatientsData,
    putIsFetchPatients,
    putPatient
} from './actions';

import {
    putPageCnt,
    putSelPageCnt,
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';


export function* fetchPatientsDataByClinicId() {
    const { payload } = yield take(FETCH_PATIENTS_DATA_BY_CLINIC_ID);
    yield put(putIsFetchPatients(false));
    const { patients } = yield call(PatientService.fetchPatientsByClinicId, payload);
    yield put(putPatientsData(patients));
    yield put(putIsFetchPatients(true));
}

export function* fetchPatinentsPagination() {
    const { payload } = yield take(FETCH_PATIENTS_PAGINATION);
    yield put(putIsFetchPatients(false));
    const { patients } = yield call(PatientService.fetchPatinentsPagination, payload);
    yield put(putSelPageCnt(payload.pageCnt));
    yield put(putPageCnt(patients.patientPageCnt));
    yield put(putPatientsData(patients.patients));
    yield put(putIsFetchPatients(true));
}

export function* fetchPatientByApp() {
    const { payload } = yield take(FETCH_PATIENT_BY_APP);
    yield put(putIsFetchPatients(false));
    const { data } = yield call(PatientService.fetchPatientByApp, payload);
    yield put(putPatient(data));
    yield put(putIsFetchPatients(true));
}