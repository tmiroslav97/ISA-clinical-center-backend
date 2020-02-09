const reducer = 'medicalReportReducer';

export const medicalReportSelector = state => state[reducer].medicalReport;
export const isFetchMedicalReportSelector = state => state[reducer].isFetchMedicalReport;
export const medicalReportsSelector = state => state[reducer].medicalReports;