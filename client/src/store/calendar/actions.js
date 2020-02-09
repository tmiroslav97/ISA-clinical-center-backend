import {
    FETCH_CALENDAR,
    PUT_CALENDAR_DATA,
    PUT_IS_FETCH_CALENDAR
} from './constants';

export const fetchCalendar = payload => ({
    type: FETCH_CALENDAR,
    payload
});

export const putIsFetchCalendar = payload => ({
    type: PUT_IS_FETCH_CALENDAR,
    payload
});

export const putCalendarData = payload => ({
    type: PUT_CALENDAR_DATA,
    payload
});