import HttpClient from './HttpBaseClient';
import { history } from '../index';
import { format } from 'util';

const FINALPOINTS = {
    FETCH_ROOMS_DATA: '/room/all/%s/%s',
    SEARCH_ROOMS_DATA: '/room/search',
    DELETE_ROOMS_DATA: '/room/delete/%s',
    SEARCH_ROOMS: '/room/search-rooms',
    ADD_ROOM: '/room/add-room/%s',
    EDIT_ROOM: '/room/edit-room',
    DELETE_ROOM: '/room/delete-room/%s'

};

class RoomService extends HttpClient {

    fetchRoomsData = async payload => {
        try {
            const { data } = await this.getApiClient().get(
                format(FINALPOINTS.FETCH_ROOMS_DATA, payload.clinicId, payload.pageCnt)
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };
    addRoomsDate = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.ADD_ROOMS_DATA,
                payload
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };
    deleteRoomsData = async payload => {
        try {
            const { data } = await this.getApiClient().delete(
                FINALPOINTS.DELETE_ROOMS_DATA,
                payload
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };
    editRoomsData = async payload => {
        try {
            const { data } = await this.getApiClient().put(
                FINALPOINTS.EDIT_ROOMS_DATA,
                payload
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };

    searchRoomsData = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.SEARCH_ROOMS_DATA,
                payload
            );

            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };

    searchRooms = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.SEARCH_ROOMS,
                payload
            );
            return { data };
        } catch (error) {
            console.log(error.response.data);
        }
    };
    
    addRoom = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                format(FINALPOINTS.ADD_ROOM, payload.clinicId),
                payload
            );
            const response = data;
            return { response };
        } catch (error) {
            const response = error.response;
            return {response};
        }
    };

    editRoom = async payload => {
        try {
            const { data } = await this.getApiClient().post(
                FINALPOINTS.EDIT_ROOM,
                payload
            );
            const response = data;
            return { response };
        } catch (error) {
            const response = error.response.data;
            return {response};
        }
    };

    deleteRoom = async payload => {
        try{
            const {data} = await this.getApiClient().post(
                format(FINALPOINTS.DELETE_ROOM, payload.id),
                payload
            )
            const response = data;
            return { response };
        } catch (error) {
            const response = error.response.data;
            return {response};
        }
    }

}

export default new RoomService();