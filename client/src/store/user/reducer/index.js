import {
    PUT_USER_DATA,
    PUT_USER_TOKEN,
    PUT_IS_FETCH_USER_DATA,
    PUT_USER_ID
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    data: {
        id: window.localStorage.getItem('userID'),
        firstName: '',
        lastName: '',
        email: '',
        firstLogin: true,
        roles: JSON.parse(window.localStorage.getItem('roles'))==null?[]:JSON.parse(window.localStorage.getItem('roles'))
    },
    token: window.localStorage.getItem('token'),
    id: window.localStorage.getItem('userID'),
    isFetchUserData: false
};

const userReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_USER_DATA]: computationFunctions.putUserData,
    [PUT_USER_TOKEN]: computationFunctions.putUserToken,
    [PUT_IS_FETCH_USER_DATA]: computationFunctions.putIsFetchUserData,
    [PUT_USER_ID]: computationFunctions.putUserId
};

export default userReducer;