import { take, put, call } from 'redux-saga/effects';
import { history } from '../../index';

import {
    ADD_MEDICAL_REPORT,
    FETCH_DOCTORS_MEDICAL_REPORTS,
    EDIT_MEDICAL_REPORT
} from './constants';

import MedicalReportService from '../../services/MedicalReportService';

import {
    putIsFetchMedicalReport,
    putDoctorsMedicalReports
} from './actions';

import {
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';

export function* editMedicalReport() {
    const { payload } = yield take(EDIT_MEDICAL_REPORT);
    const { msg } = yield call(MedicalReportService.editMedicalReport, payload);
    if (msg === 'Successfuly edited') {
        yield put(putSuccessMsg(msg));
        yield put(putSuccessMsg(null));
    } else {
        yield put(putErrorMsg(msg));
        yield put(putErrorMsg(null));
    }
    yield put(putIsFetchMedicalReport(false));
    const { data } = yield call(MedicalReportService.fetchDoctorsMedicalReports, { doctorId: payload.doctorId });
    yield put(putDoctorsMedicalReports(data));
    yield put(putIsFetchMedicalReport(true));
}

export function* fetchDoctorsMedicalReports() {
    const { payload } = yield take(FETCH_DOCTORS_MEDICAL_REPORTS);
    yield put(putIsFetchMedicalReport(false));
    const { data } = yield call(MedicalReportService.fetchDoctorsMedicalReports, payload);
    yield put(putDoctorsMedicalReports(data));
    yield put(putIsFetchMedicalReport(true));
}

export function* addMedicalReport() {
    const { payload } = yield take(ADD_MEDICAL_REPORT);
    const { data } = yield call(MedicalReportService.addMedicalReport, payload);
    if (data === 'Appointment finished') {
        yield put(putSuccessMsg(data));
        yield put(putSuccessMsg(null));
    } else {
        yield put(putErrorMsg(data));
        yield put(putErrorMsg(null));
    }
    history.push('/doc');
}