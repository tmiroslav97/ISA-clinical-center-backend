import {
    FETCH_ROOMS_DATA,
    PUT_ROOMS_DATA,
    PUT_IS_FETCH_ROOMS,
    DELETE_ROOM,
    //ADD_ROOMS_DATA,
    //EDIT_ROOMS_DATA,
    SEARCH_ROOMS,
    SEARCH_ROOMS_DATA,
    PUT_PAGE_COUNT,
    ADD_ROOM,
    EDIT_ROOM
} from './constants';

export const putPageCount = payload => ({
    type: PUT_PAGE_COUNT,
    payload
});

export const fetchRoomsData = payload => ({
    type: FETCH_ROOMS_DATA,
    payload
});

export const putRoomsData = payload => ({
    type: PUT_ROOMS_DATA,
    payload
});

export const putIsFetchRooms = payload => ({
    type: PUT_IS_FETCH_ROOMS,
    payload
});

export const searchRoomsData = payload => ({
    type: SEARCH_ROOMS_DATA,
    payload
});

export const searchRooms = payload => ({
    type: SEARCH_ROOMS,
    payload
});

export const addRoom = payload => ({
    type: ADD_ROOM,
    payload
});

export const editRoom = payload => ({
    type: EDIT_ROOM,
    payload
});

export const deleteRoom = payload => ({
    type: DELETE_ROOM,
    payload
});
 /*
export const deleteRoomsData = payload => ({
    type: DELETE_ROOMS_DATA,
    payload
});

export const addRoomsData = payload => ({
    type: ADD_ROOMS_DATA,
    payload
});

export const editRoomsData = payload => ({
    type: EDIT_ROOMS_DATA,
    payload
});


*/