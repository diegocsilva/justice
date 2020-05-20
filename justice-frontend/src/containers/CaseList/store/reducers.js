import { CASE_LIST_PAGINATE } from './actions';

const initialState = {
  content: [],
  pageable: {
    pageNumber: 0,
  },
  totalElements: 0,
};

export default function (state = initialState, action) {
  switch (action.type) {
    case CASE_LIST_PAGINATE:
      return {
        ...state,
        list: action.payload,
      };
    default:
      return state;
  }
}