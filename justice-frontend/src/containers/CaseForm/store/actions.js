import ProviderCases from '../../../core/providers/cases';

export const CASE_SET_CASE_EDIT = 'CASE_SET_CASE_EDIT';

export const setCaseEdit = (caseEdit) => {
  return {
    type: CASE_SET_CASE_EDIT,
    payload: caseEdit,
  };
};

export const create = (values) => {
  ProviderCases.save(values);
  return {
    type: 'TEMP',
  };
};
