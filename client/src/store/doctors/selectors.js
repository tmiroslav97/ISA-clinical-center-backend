const reducer = 'doctorsReducer';

export const doctorsDataSelector = state => state[reducer].doctors;
export const isFetchDoctorsSelector = state => state[reducer].isFetchDoctors;
export const pageCountSelector = state => state[reducer].pageCount;