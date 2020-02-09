const reducer = 'calendarReducer';

export const calendarDataSelector = state => state[reducer].calendar;
export const isFetchCalendarSelector = state => state[reducer].isFetchCalendar;