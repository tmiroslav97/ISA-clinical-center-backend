const reducer = 'medicineDiagnoseReducer';

export const diagnosesSelector = state => state[reducer].diagnoses;
export const isFetchDiagnosesSelector = state => state[reducer].isFetchDiagnoses;
export const medicinesSelector = state => state[reducer].medicines;
export const isFetchMedicinesSelector = state => state[reducer].isFetchMedicines;