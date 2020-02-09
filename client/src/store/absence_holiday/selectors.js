const reducer = 'absenceHolidayReducer';

export const absHolRequestDataSelector = state => state[reducer].absholrequests;
export const isFetchAbsHolRequestsSelector = state => state[reducer].isFetchAbsHolRequests;
