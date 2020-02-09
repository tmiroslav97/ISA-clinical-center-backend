const reducer = 'clinicReducer';

export const clinicsDataSelector = state => state[reducer].clinics;
export const isFetchClinicsDataSelector = state => state[reducer].isFetchClinics;
export const pageCountSelector = state => state[reducer].pageCount;