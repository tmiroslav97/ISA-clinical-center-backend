export function putCalendarData(state, payload) {
    return {
        ...state,
        calendar: payload
    };
}

export function putIsFetchCalendar(state, payload) {
    return {
        ...state,
        isFetchCalendar: payload
    };
}