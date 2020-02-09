import {
    PUT_REG_REQS_DATA,
    PUT_IS_FETCH_REG_REQS,
    PUT_REG_REQS_CNT
} from '../constants';

import * as computationFunctions from './computation-functions';

const initialState = {
    reqs:[],
    isFetchRegReqs:false,
    regReqsPageCnt: 0
};

const regReqsReducer = (state = initialState, { type, payload })=>{
    if(actionHandler.hasOwnProperty(type)){
        return actionHandler[type](state,payload);
    }
    return state;
};

const actionHandler = {
    [PUT_REG_REQS_DATA]: computationFunctions.putRegReqsData,
    [PUT_IS_FETCH_REG_REQS]: computationFunctions.putIsFetchRegReqs,
    [PUT_REG_REQS_CNT]: computationFunctions.putRegReqsCnt
  };

export default regReqsReducer;