import { CASE_SET_CASE_EDIT } from './actions';

const initialState = {
  caseEdit: {}
};

export default function (state = initialState, action) {
  switch (action.type) {
    case CASE_SET_CASE_EDIT:
      return {
        ...state,
        caseEdit: action.payload,
      };
    default:
      return state;
  }
}
