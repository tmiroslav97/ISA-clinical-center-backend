const reducer = 'prescriptionReducer';

export const prescriptionsDataSelector = state => state[reducer].prescriptions;
export const isFetchPrescriptionsSelector = state => state[reducer].isFetchPrescriptions;