import HttpClient from './HttpBaseClient';

const FINALPOINTS = {
    ADD_APPOINTMENT_REQUIREMENT: '/app-req/add'
};

class AppointmentRequirementService extends HttpClient {
    addAppointmentRequirement = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.ADD_APPOINTMENT_REQUIREMENT,
                payload
            );
            return { data };
        } catch (error) {
            return error.response;
        }
    };
}
export default new AppointmentRequirementService();