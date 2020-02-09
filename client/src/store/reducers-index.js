import { combineReducers } from 'redux';
import userReducer from './user/reducer';
import regReqsReducer from './reg_req/reducer';
import patientsReducer from './patients/reducer';
import calendarReducer from './calendar/reducer';
import absenceHolidayReducer from './absence_holiday/reducer';
import prescriptionReducer from './prescriptions/reducer';
import medicineDiagnoseReducer from './medicine_diagnose/reducer';
import clinicReducer from './clinics/reducer';
import appointmentReducer from './appointments/reducer';
import doctorsReducer from './doctors/reducer';
import roomsReducer from './rooms/reducer';
import surReqReducer from './sur_req/reducer';
import commonReducer from './common/reducer';
import medicalRecordReducer from './medical_record/reducer';
import medicalReportReducer from './medical_report/reducer';
import appReqReducer from './app_req/reducer';

const rootReducer = combineReducers({
    userReducer,
    regReqsReducer,
    patientsReducer,
    calendarReducer,
    absenceHolidayReducer,
    prescriptionReducer,
    medicineDiagnoseReducer,
    clinicReducer,
    appointmentReducer,
    doctorsReducer,
    roomsReducer,
    surReqReducer,
    commonReducer,
    medicalRecordReducer,
    medicalReportReducer,
    appReqReducer
});


export default (state, action) => {
    return rootReducer(state, action);
};