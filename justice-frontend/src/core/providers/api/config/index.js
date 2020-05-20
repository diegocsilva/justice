const getConfig = () => {
  const config = {
    baseUrl: process.env.REACT_APP_API,
  };

  return {
    baseURL: config.baseUrl,
    headers: {},
  };
};

export const URL = {
  CASE_LIST: 'case?',
  CASE_SAVE: 'case',
};

export default getConfig;