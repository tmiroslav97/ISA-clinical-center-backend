import {
    LOGIN,
    REGISTRATION,
    PUT_USER_DATA,
    PUT_USER_TOKEN,
    CHANGE_PASSWORD,
    SIGN_OUT,
    FETCH_CCADMIN_DATA,
    REG_CC_ADMIN,
    FETCH_NURSE_DATA,
    FETCH_CADMIN_DATA,
    FETCH_PATIENT_DATA,
    FETCH_DOCTOR_DATA,
    PUT_IS_FETCH_USER_DATA,
    PUT_USER_ID,
    EDIT_USER_INFORMATION
} from './constants';

//patient actions
export const fetchPatientData = payload => ({
    type: FETCH_PATIENT_DATA,
    payload
});

//cadmin actions
export const fetchCAdminData = payload => ({
    type: FETCH_CADMIN_DATA,
    payload
});

//doctor actions
export const fetchDoctorData = payload => ({
    type: FETCH_DOCTOR_DATA,
    payload
});

//nurse actions
export const fetchNurseData = payload => ({
    type: FETCH_NURSE_DATA,
    payload
});

//clinic center admin actions
export const fetchCCAdminData = payload => ({
    type: FETCH_CCADMIN_DATA,
    payload
});

export const regCCAdmin = payload => ({
    type: REG_CC_ADMIN,
    payload
});

//user actions
export const putUserId = payload => ({
    type: PUT_USER_ID,
    payload
});

export const putIsFetchUserData = payload => ({
    type: PUT_IS_FETCH_USER_DATA,
    payload
});

export const signOut = payload => ({
    type: SIGN_OUT,
    payload
});

export const loginUser = payload => ({
    type: LOGIN,
    payload
});

export const registerUser = payload => ({
    type: REGISTRATION,
    payload
});

export const putUserData = payload => ({
    type: PUT_USER_DATA,
    payload
});

export const putUserToken = payload => ({
    type: PUT_USER_TOKEN,
    payload
});

export const changePassword = payload => ({
    type: CHANGE_PASSWORD,
    payload
});

export const editUserInformation = payload => ({
    type: EDIT_USER_INFORMATION,
    payload
});
