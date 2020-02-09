const reducer = 'medicalRecordReducer';

export const medicalRecordSelector = state => state[reducer].medicalRecord;
export const isFetchMedicalRecordSelector = state => state[reducer].isFetchMedicalRecord;