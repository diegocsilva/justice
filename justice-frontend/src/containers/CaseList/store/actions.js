export const CASE_LIST_PAGINATE = 'CASE_LIST_PAGINATE';

export const listCases = (query) => {
  return {
    type: CASE_LIST_PAGINATE,
    payload: query,
  };
};