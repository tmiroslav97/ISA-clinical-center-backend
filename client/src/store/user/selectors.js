const reducer = 'userReducer';

//user selectors
export const userDataSelector = state => state[reducer].data;
export const userTokenSelector = state => state[reducer].token;
export const isFetchUserDataSelector = state => state[reducer].isFetchUserData;
export const userIdSelector = state => state[reducer].id;
