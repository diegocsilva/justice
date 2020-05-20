import caseListReducer from './containers/CaseList/store/reducers';
import caseEditReducer from './containers/CaseForm/store/reducers';
import { combineReducers } from 'redux';

const reducers = combineReducers({
  caseList: caseListReducer,
  caseEdit: caseEditReducer,
});

export default reducers;
