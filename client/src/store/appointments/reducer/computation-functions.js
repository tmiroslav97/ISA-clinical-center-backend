export function putAppointmentTypes(state, payload) {
    return {
        ...state,
        appointmentTypes: payload
    };
}

export function putAppointment(state, payload) {
    return {
        ...state,
        appointment: payload
    };
}

export function putIsFetchAppointmentTypes(state, payload) {
    return {
        ...state,
        isFetchAppointmentTypes: payload
    };
}

export function putIsFetchAppointment(state, payload) {
    return {
        ...state,
        isFetchAppointment: payload
    };
}