import {
    REG_CLINIC,
    FETCH_CLINICS_DATA,
    PUT_CLINICS_DATA,
    REG_CLINIC_ADMIN,
    PUT_IS_FETCH_CLINICS_DATA,
    FETCH_CLINIC_PAGINATION_DATA,
    SEARCH_CLINIC,
    PUT_PAGE_COUNT
} from './constants';

export const putPageCount = payload => ({
    type: PUT_PAGE_COUNT,
    payload
});


export const fetchClinicPaginationData = payload => ({
    type: FETCH_CLINIC_PAGINATION_DATA,
    payload
});

export const regClinicAdmin = payload => ({
    type: REG_CLINIC_ADMIN,
    payload
});

export const putClinicsData = payload => ({
    type: PUT_CLINICS_DATA,
    payload
});

export const putIsFetchClinicsData = payload => ({
    type: PUT_IS_FETCH_CLINICS_DATA,
    payload
});

export const regClinic = payload => ({
    type: REG_CLINIC,
    payload
});

export const fetchClinicsData = payload => ({
    type: FETCH_CLINICS_DATA,
    payload
});

export const searchClinic = payload => ({
    type: SEARCH_CLINIC,
    payload
});
