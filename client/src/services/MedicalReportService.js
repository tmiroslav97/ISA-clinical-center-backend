import HttpClient from './HttpBaseClient';
import { format } from 'util';

const FINALPOINTS = {
    ADD_MEDICAL_REPORT: '/med-rep/add',
    FETCH_DOCTORS_MEDICAL_REPORTS: '/med-rep/all/%s',
    EDIT_MEDICAL_REPORT: '/med-rep/edit'
};

class MedicalReportService extends HttpClient {
    addMedicalReport = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.ADD_MEDICAL_REPORT,
                payload
            );

            return { data };
        } catch (error) {
            return error.response;
        }
    };

    fetchDoctorsMedicalReports = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_DOCTORS_MEDICAL_REPORTS, payload.doctorId)
            );

            return { data };
        } catch (error) {
            return error.response;
        }
    };

    editMedicalReport = async payload => {
        try {
            const { data } = await this.getApiClient().put(
                FINALPOINTS.EDIT_MEDICAL_REPORT,
                payload
            );
            const msg = data;
            return { msg };
        } catch (error) {
            const msg = error.response;
            return { msg };
        }
    };
}

export default new MedicalReportService();
