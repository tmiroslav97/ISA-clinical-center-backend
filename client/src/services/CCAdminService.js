import HttpClient from './HttpBaseClient';
import { history } from '../index';
import { format } from 'util';

const FINALPOINTS = {
    FETCH_CCADMIN_DATA: '/cca/%s',
    FETCH_REG_REQS_DATA: '/reg/regreqs/%s',
    APPROVE_REG_REQ: '/reg/approve/%s',
    REJECT_REG_REQ: '/reg/reject/%s/%s',
    REG_CC_ADMIN: '/cca/reg-cca/%s',
    REG_CLINIC: '/cca/reg-clinic',
    FETCH_CLINICS: '/cca/clinics',
};

class CCAdminService extends HttpClient {

    fetchCCAdminData = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_CCADMIN_DATA, payload.ccAdminId)
            );
            return { data };
        } catch (error) {
            return error.response;
        }
    };

    fetchRegReqsData = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_REG_REQS_DATA, payload.pageCnt)
            );
            const reqData = data;
            return { reqData };
        } catch (error) {
            return error.response;
        }
    };

    approveRegReq = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                format(FINALPOINTS.APPROVE_REG_REQ, payload.regReqId)
            );
            return { data };
        } catch (error) {
            return error.response;
        }

    };

    rejectRegReq = async payload => {
        try {
            const { data } = await this.getApiClient().post(format(FINALPOINTS.REJECT_REG_REQ, payload.reqId, payload.message));
            return { data };
        } catch (error) {
            return error.response;
        }
    };

    regCCAdmin = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                format(FINALPOINTS.REG_CC_ADMIN, payload.ccaId),
                payload
            );

            return { data };
        } catch (error) {
            return error.response;
        }
    };

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

}

export default new CCAdminService();