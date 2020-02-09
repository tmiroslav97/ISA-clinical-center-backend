import { take, put, call } from 'redux-saga/effects';
import { history } from '../../index';

import {
    REG_CLINIC,
    FETCH_CLINICS_DATA,
    REG_CLINIC_ADMIN,
    FETCH_CLINIC_PAGINATION_DATA,
    SEARCH_CLINIC
} from './constants';

import ClinicService from '../../services/ClinicService';

import {
    putClinicsData,
    putIsFetchClinicsData,
    putPageCount
} from './actions';

import {
    putPageCnt,
    putSelPageCnt,
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';


export function* regClinicAdmin() {
    const { payload } = yield take(REG_CLINIC_ADMIN);
    const { data } = yield call(ClinicService.regClinicAdmin, payload);
    if (data === 'Clinic admin successfully registered') {
        yield put(putSuccessMsg(data));
        yield put(putSuccessMsg(null));
    } else {
        yield put(putErrorMsg(data));
        yield put(putErrorMsg(null));
    }
}

export function* fetchClinicsData() {
    const { payload } = yield take(FETCH_CLINICS_DATA);
    yield put(putIsFetchClinicsData(false));
    const { clinics } = yield call(ClinicService.fetchClinicsData, payload);
    yield put(putClinicsData(clinics));
    yield put(putIsFetchClinicsData(true));
}

export function* fetchClinicPaginationData() {
    const { payload } = yield take(FETCH_CLINIC_PAGINATION_DATA);
    yield put(putIsFetchClinicsData(false));
    const { clinicPag } = yield call(ClinicService.fetchClinicPaginationData, payload);
    yield put(putSelPageCnt(payload.pageCnt));
    yield put(putPageCnt(clinicPag.pageCnt));
    yield put(putClinicsData(clinicPag.clinics));
    yield put(putIsFetchClinicsData(true));
}

export function* regClinic() {
    const { payload } = yield take(REG_CLINIC);
    const { data } = yield call(ClinicService.regClinic, payload);
    if (data === 'Clinic successfuly added') {
        yield put(putSuccessMsg(data));
        yield put(putSuccessMsg(null));
        yield put(putIsFetchClinicsData(false));
        const { clinicPag } = yield call(ClinicService.fetchClinicPaginationData, { pageCnt: 0 });
        yield put(putSelPageCnt(0));
        yield put(putPageCnt(clinicPag.pageCnt));
        yield put(putClinicsData(clinicPag.clinics))
        yield put(putIsFetchClinicsData(true));
    } else {
        yield put(putErrorMsg(data));
        yield put(putErrorMsg(null));
    }
}

export function* searchClinic() {
    const { payload } = yield take(SEARCH_CLINIC);
    yield put(putIsFetchClinicsData(false));
    const { data } = yield call(ClinicService.searchClinic, payload);
    yield put(putClinicsData(data.clinics));
    yield put(putPageCount(data.pageCount));
    yield put(putIsFetchClinicsData(true)); 
}
