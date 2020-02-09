const reducer = 'appointmentReducer';

export const appointmentTypeSelector = state => state[reducer].appointmentTypes;
export const isFetchAppointmentTypeSelector = state => state[reducer].isFetchAppointmentTypes;
export const appIdSelector = state => state[reducer].id;
export const appointmentSelector = state => state[reducer].appointment;
export const isFetchAppointmentSelector = state => state[reducer].isFetchAppointment;
