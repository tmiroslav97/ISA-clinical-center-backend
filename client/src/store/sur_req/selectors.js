const reducer = 'surReqReducer';

export const surReqDataSelector = state => state[reducer].surgeryReq;
export const isFetchSurReqDataSelector = state => state[reducer].isFetchSurgeryReq;
export const surReqPageCountSelector = state => state[reducer].pageCount;
export const pickSurReqSelector = state => state[reducer].pickSurReq;
export const pickedSurReqSelector = state => state[reducer].pickedSurReq;
export const pickTermSelector = state => state[reducer].pickTerm;
export const pickedTermSelector = state => state[reducer].pickedTerm;
export const pickedRoomSelector = state => state[reducer].pickedRoom;