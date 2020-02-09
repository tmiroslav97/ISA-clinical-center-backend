import { take, put, call } from 'redux-saga/effects';

import {
    FETCH_CALENDAR,
} from './constants';

import PersonnelService from '../../services/PersonnelService';

import {
    putCalendarData,
    putIsFetchCalendar
} from './actions';

export function* fetchCalendar() {
    const { payload } = yield take(FETCH_CALENDAR);
    yield put(putIsFetchCalendar(false));
    const { calendar } = yield call(PersonnelService.fetchCalendar, payload);
    yield put(putCalendarData(calendar));
    yield put(putIsFetchCalendar(true));
}