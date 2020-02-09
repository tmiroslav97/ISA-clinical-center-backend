const reducer = 'appReqReducer';

export const appReqsDataSelector = state => state[reducer].appointmentReqs;
export const isFetchAppReqsSelector = state => state[reducer].isFetchAppointmentReqs;