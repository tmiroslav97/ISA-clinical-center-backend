import HttpBaseClient from './HttpBaseClient';
import { history } from '../index';

const FINALPOINTS = {
    LOGIN: '/sec/login',
    REGISTRATION: '/sec/registration',
    CHANGE_PASSWORD: '/sec/change-password'
};

class AuthSecurityService extends HttpBaseClient {
    login = async credentials => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.LOGIN,
                credentials
            );
            

            localStorage.setItem('token', data.token);
            //brisi dole
            localStorage.setItem('userID', data.id);
            localStorage.setItem('roles', JSON.stringify(data.roles));

            /* this.AuthenticatorAssertionResponse({
                Authorization: `Bearer ${data.token}`
            }); */
            return { data };

        } catch (error) {
            console.log(error.response.data);
        }
    };

    registration = async userData => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.REGISTRATION,
                userData
            );

            history.push('/');

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };

    changePassword = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.CHANGE_PASSWORD,
                payload
            );

            //putting new token in local storage
            localStorage.setItem('token', data.token);
            
            return { data };
        } catch (error) {
            console.log(error.response.data);
            return error.response;
        }
    };

}

export default new AuthSecurityService(); 