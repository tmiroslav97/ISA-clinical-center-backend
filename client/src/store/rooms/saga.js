import { take, put, call } from 'redux-saga/effects';

import {
    FETCH_ROOMS_DATA,
    SEARCH_ROOMS_DATA,
    SEARCH_ROOMS,
    ADD_ROOM,
    EDIT_ROOM,
    DELETE_ROOM
} from './constants';

import RoomService from '../../services/RoomService';

import {
    putRoomsData,
    putIsFetchRooms,
    putPageCount
} from './actions';


import {
    putSuccessMsg,
    putErrorMsg
} from '../common/actions';


export function* fetchRoomsData() {
    const { payload } = yield take(FETCH_ROOMS_DATA);
    yield put(putIsFetchRooms(false));
    const { data } = yield call(RoomService.fetchRoomsData, payload);
    yield put(putRoomsData(data.rooms));
    yield put(putPageCount(data.pageCount));
    yield put(putIsFetchRooms(true));
}

export function* searchRoomsData(){
    const { payload } = yield take (SEARCH_ROOMS_DATA);
    yield put(putIsFetchRooms(false));
    const { data } = yield call(RoomService.searchRoomsData, payload);
    yield put(putRoomsData(data.rooms));
    yield put(putPageCount(data.pageCount));
    yield put(putIsFetchRooms(true));
}

export function* searchRooms(){
    const { payload } = yield take (SEARCH_ROOMS);
    yield put(putIsFetchRooms(false));
    const { data } = yield call(RoomService.searchRooms, payload);
    yield put(putRoomsData(data));
    yield put(putPageCount(data.pageCount));
    yield put(putIsFetchRooms(true));
}

export function* addRoom() {
    const { payload } = yield take(ADD_ROOM);
    const { response } = yield call(RoomService.addRoom, payload);
    if (response === 'Successfully added room') {
        yield put(putSuccessMsg(response));
        yield put(putSuccessMsg(null));
        yield put(putIsFetchRooms(false));
        const { data } = yield call(RoomService.fetchRoomsData, payload);
        yield put(putRoomsData(data.rooms));
        yield put(putPageCount(data.pageCount));
        yield put(putIsFetchRooms(true));
    } else {
        yield put(putErrorMsg(response));
        yield put(putErrorMsg(null));
    }
    
}

export function* editRoom() {
    const { payload } = yield take(EDIT_ROOM);
    const {response} = yield call(RoomService.editRoom, payload);
    if (response === 'Successfully edited room') {
        yield put(putSuccessMsg(response));
        yield put(putSuccessMsg(null));
        yield put(putIsFetchRooms(false));
        const { data } = yield call(RoomService.fetchRoomsData, payload);
        yield put(putRoomsData(data.rooms));
        yield put(putPageCount(data.pageCount));
        yield put(putIsFetchRooms(true));
    } else {
        yield put(putErrorMsg(response));
        yield put(putErrorMsg(null));
    }
}

export function* deleteRoom() {
    const { payload } = yield take(DELETE_ROOM);
    const { response } = yield call(RoomService.deleteRoom, {id:payload.id});
    if (response === 'Successfully deleted room') {
        yield put(putSuccessMsg(response));
        yield put(putSuccessMsg(null));
        yield put(putIsFetchRooms(false));
        const { data } = yield call(RoomService.fetchRoomsData, payload);
        yield put(putRoomsData(data.rooms));
        yield put(putPageCount(data.pageCount));
        yield put(putIsFetchRooms(true));
    } else {
        yield put(putErrorMsg(response));
        yield put(putErrorMsg(null));
    }
}


/*export function* deleteRoomsData(){
    const { payload } = yield take (DELETE_ROOMS_DATA);
    const { data } = yield call(RoomService.deleteRoomsData, payload);
    const { rooms } = yield call(RoomService.fetchRoomsData, {});
    yield put(putRoomsData(rooms));
}*/
/*export function* addRoomsData(){
    const { payload } = yield take (ADD_ROOMS_DATA);
    const { data } = yield call(RoomService.addRoomsData, payload);
    const { rooms } = yield call(RoomService.fetchRoomsData, {});
    yield put(putRoomsData(rooms));
}*/
/*export function* editRoomsData(){
    const { payload } = yield take (EDIT_ROOMS_DATA);
    const { data } = yield call(RoomService.editRoomsData, payload);
    const { rooms } = yield call(RoomService.fetchRoomsData, {});
    yield put(putRoomsData(rooms));
}*/

