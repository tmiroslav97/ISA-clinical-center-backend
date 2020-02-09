import HttpClient from './HttpBaseClient';
import { history } from '../index';
import { format } from 'util';

const FINALPOINTS = {
    FETCH_CLINICS_DATA: '/clinic/%s',
    REG_CLINIC: '/clinic/reg-clinic',
    FETCH_CLINICS: '/clinic/clinics',
    REG_CLINIC_ADMIN: '/adm-cli/reg-clinic-admin',
    FETCH_CLINIC_PAGINATION_DATA: '/clinic/all/%s',
    SEARCH_CLINIC: 'clinic/all/search-clinics',
};

class ClinicService extends HttpClient {

    fetchClinicsData = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                FINALPOINTS.FETCH_CLINICS
            );
            const clinics = data;
            return { clinics };
        } catch (error) {
            return error.response;
        }
    };


    regClinic = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.REG_CLINIC,
                payload
            );

            return { data };
        } catch (error) {
            return error.response;
        }
    };

    regClinicAdmin = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.REG_CLINIC_ADMIN,
                payload
            );

            return { data };
        } catch (error) {
            return error.response;
        }
    };

    searchClinic = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.SEARCH_CLINIC,
                payload
            );

            return { data };
        } catch (error) {
            return error.response;
        }
    };
    
    fetchClinicPaginationData = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_CLINIC_PAGINATION_DATA, payload.pageCnt)
            );
            
            const clinicPag = data;
            return { clinicPag };
        } catch (error) {
            return error.response;
        }
    };

    /*fetchClinicByApp = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_CLINIC_BY_APP, payload.typeId)
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };*/


}

export default new ClinicService();