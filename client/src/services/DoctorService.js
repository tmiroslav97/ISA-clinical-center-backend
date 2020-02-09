import HttpClient from './HttpBaseClient';
import { format } from 'util';

const FINALPOINTS = {
    FETCH_DOCTOR_DATA: '/doctor/%s',
    ADD_DOCTOR: '/doctor/add-doctor-on-clinic/%s',
    FETCH_DOCTORS_DATA: '/doctor/doctors',
    FETCH_DOCTORS_BY_CLINICID: '/doctor/all/%s',
    DELETE_DOCTOR: '/doctor/delete/%s',
    SEARCH_DOCTORS: '/doctor/search-doctors'
};

class DoctorService extends HttpClient {

    fetchDoctorsByClinicId = async payload => {
        try {
            console.log(payload);
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_DOCTORS_BY_CLINICID, payload.clinicId)
            );
            const doctors = data;
            return { doctors };
        } catch (error) {
            console.log(error.response.data);
        }
    };

    fetchDoctorData = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_DOCTOR_DATA, payload.id)
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };


    fetchDoctorsData = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                FINALPOINTS.FETCH_DOCTORS_DATA
            );

            const doctors = data;

            return { doctors };
        } catch (error) {
            console.log(error.response.data);
        }
    };

    addDoctor = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                format(FINALPOINTS.ADD_DOCTOR, payload.clinicId),
                payload
            );
            const response = data;
            return { response };
        } catch (error) {
            const response = error.response;
            return {response};
        }
    };

    //search bez paginije
    searchDoctors = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.SEARCH_DOCTORS,
                payload
            );
            console.log("DATA u service");
            console.log(data);
            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };

    searchDoctor = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.SEARCH_DOCTOR,
                payload
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };

    deleteDoctor = async payload => {
        try {
            console.log("U service")
           console.log(payload);
            const { data } = await this.getApiClient().post(
                format(FINALPOINTS.DELETE_DOCTOR, payload.id),
                payload
            );
            console.log("ISPIS DATA");
            console.log(data);

            const response = data;
            return { response };
        } catch (error) {
            const response = error.response;
            return {response};
        }
    };

}

export default new DoctorService();