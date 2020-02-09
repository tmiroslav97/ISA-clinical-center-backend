import {
    PUT_CALENDAR_DATA,
    PUT_IS_FETCH_CALENDAR
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    calendar: {
        id: '',
        calendarItemResponses: []
    },
    isFetchCalendar: false
};

const calendarReducer = (state = initialState, { type, payload }) => {
    if (actionHandler.hasOwnProperty(type)) {
        return actionHandler[type](state, payload);
    }
    return state;
};

const actionHandler = {
    [PUT_CALENDAR_DATA]: computationFunctions.putCalendarData,
    [PUT_IS_FETCH_CALENDAR]: computationFunctions.putIsFetchCalendar
};

export default calendarReducer;