import { take, put, call, select } from 'redux-saga/effects';
import { history } from '../../index';
import { userDataSelector } from '../../store/user/selectors';

import {
    LOGIN,
    REGISTRATION,
    CHANGE_PASSWORD,
    SIGN_OUT,
    FETCH_CCADMIN_DATA,
    REG_CC_ADMIN,
    FETCH_NURSE_DATA,
    FETCH_CADMIN_DATA,
    FETCH_PATIENT_DATA,
    FETCH_DOCTOR_DATA,
    EDIT_USER_INFORMATION
} from './constants';

import authService from '../../services/AuthSecurity';
import CCAdminService from '../../services/CCAdminService';
import NurseService from '../../services/NurseService';
import CAdminService from '../../services/CAdminService';
import PatientService from '../../services/PatientService';
import DoctorService from '../../services/DoctorService';


import {
    putUserData,
    putUserToken,
    putIsFetchUserData,
    putUserId
} from './actions';

import {
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';

//patient sagas
export function* fetchPatientData() {
    const { payload } = yield take(FETCH_PATIENT_DATA);
    yield put(putIsFetchUserData(false));
    const { data } = yield call(PatientService.fetchPatientData, payload);
    yield put(putUserData(data));
    yield put(putIsFetchUserData(true));

}

//cadmin sagas
export function* fetchCAdminData() {
    const { payload } = yield take(FETCH_CADMIN_DATA);
    yield put(putIsFetchUserData(false));
    const { data } = yield call(CAdminService.fetchCAdminData, payload);
    yield put(putUserData(data));
    yield put(putIsFetchUserData(true));
}
//doctor sagas
export function* fetchDoctorData() {
    const { payload } = yield take(FETCH_DOCTOR_DATA);
    yield put(putIsFetchUserData(false));
    const { data } = yield call(DoctorService.fetchDoctorData, payload);
    yield put(putUserData(data));
    yield put(putIsFetchUserData(true));
}

//nurse sagas
export function* fetchNurseData() {
    const { payload } = yield take(FETCH_NURSE_DATA);
    yield put(putIsFetchUserData(false));
    const { data } = yield call(NurseService.fetchNurseData, payload);
    yield put(putUserData(data));
    yield put(putIsFetchUserData(true));
}

//clinic center admin sagas
export function* fetchCCAdminData() {
    const { payload } = yield take(FETCH_CCADMIN_DATA);
    yield put(putIsFetchUserData(false));
    const { data } = yield call(CCAdminService.fetchCCAdminData, payload);
    yield put(putUserData(data));
    yield put(putIsFetchUserData(true));
}


export function* regCCAdmin() {
    const { payload } = yield take(REG_CC_ADMIN);
    const { data } = yield call(CCAdminService.regCCAdmin, payload);
    if (data === 'Successfully added new clinic center administrator') {
        yield put(putSuccessMsg(data));
        yield put(putSuccessMsg(null));
    } else {
        yield put(putErrorMsg(data));
        yield put(putErrorMsg(null));
    }
}

//user sagas
export function* signOut() {
    const { payload } = yield take(SIGN_OUT);
    localStorage.clear();
    yield put(putUserData(payload));
    yield put(putUserId(null));
    yield put(putUserToken(null));
    window.location.href = '/';
}

export function* registration() {
    const { payload } = yield take(REGISTRATION);
    yield call(authService.registration, payload);
    alert('Successfuly submited!');
}

export function* login() {
    const { payload } = yield take(LOGIN);
    const { data } = yield call(authService.login, payload);
    yield put(putUserData(data));
    yield put(putUserId(data.id));
    yield put(putUserToken(data.token));
    if (data.roles.includes('ROLE_PATIENT')) {
        history.push('/pat');
    } else if (data.roles.includes('ROLE_CCADMIN')) {
        if (data.firstLogin) {
            history.push('/change-pass');
        } else {
            history.push('/ccadmin');
        }

    } else if (data.roles.includes('ROLE_DOCTOR')) {
        history.push('/doc');
    } else if (data.roles.includes('ROLE_NURSE')) {
        history.push('/nurse-page');
    } else if (data.roles.includes('ROLE_ADMINC')) {
        if (data.firstLogin) {
            history.push('/change-pass');
        } else {
            history.push('/adminc');
        }
    } else {
        alert('Nije odobren pristup sistemu!');
        history.push('/');
    }
}

export function* changePassword() {
    const { payload } = yield take(CHANGE_PASSWORD);
    const { data } = yield call(authService.changePassword, payload);
    if (data !== 'Service unavailable') {
        yield put(putSuccessMsg('Successfuly changed password'));
        yield put(putSuccessMsg(null));
        yield put(putUserData(data));
        yield put(putUserToken(data.token));

        if (data.roles.includes('ROLE_PATIENT')) {
            history.push('/pat');
        } else if (data.roles.includes('ROLE_CCADMIN')) {
            history.push('/ccadmin');
        } else if (data.roles.includes('ROLE_DOCTOR')) {
            history.push('/doc');
        } else if (data.roles.includes('ROLE_NURSE')) {
            history.push('/nurse-page');
        } else if (data.roles.includes('ROLE_ADMINC')) {
            history.push('/adminc');
        } else {
            alert('Nije odobren pristup sistemu!');
        }
    } else {
        yield put(putErrorMsg(data));
        yield put(putErrorMsg(null));
    }
}

export function* editUserInformation() {
    const { payload } = yield take(EDIT_USER_INFORMATION);
    const { response } = yield call(CAdminService.editUserInformation, payload);
    if (response === 'Successfully edited users profile') {
        yield put(putSuccessMsg(response));
        yield put(putSuccessMsg(null));
        const userData = yield select(userDataSelector);
        if (userData.roles.includes("ROLE_DOCTOR")) {
            yield put(putIsFetchUserData(false));
            const { data } = yield call(DoctorService.fetchDoctorData, { id: payload.id });
            yield put(putUserData(data));
            yield put(putIsFetchUserData(true));
        } else if (userData.roles.includes("ROLE_NURSE")) {
            yield put(putIsFetchUserData(false));
            const { data } = yield call(NurseService.fetchNurseData, { id: payload.id });
            yield put(putUserData(data));
            yield put(putIsFetchUserData(true));
        } else if (userData.roles.includes("ROLE_ADMINC")) {
            yield put(putIsFetchUserData(false));
            const { data } = yield call(CAdminService.fetchCAdminData, { id: payload.id });
            yield put(putUserData(data));
            yield put(putIsFetchUserData(true));
        } else if (userData.roles.includes("ROLE_PATIENT")) {
            yield put(putIsFetchUserData(false));
            const { data } = yield call(PatientService.fetchPatientData, { patientId: payload.id });
            yield put(putUserData(data));
            yield put(putIsFetchUserData(true));
        } else {
            yield put(putErrorMsg('Unknown user role'));
            yield put(putErrorMsg(null));
        }

    } else {
        yield put(putErrorMsg(response));
        yield put(putErrorMsg(null));
    }
}

