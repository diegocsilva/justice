import Api from './api';
import { getUrlByQueryPaginate } from '../../core/providers/api/util/index';
import { URL } from './api/config/index';
import history from '../../core/utils/history';

class ProviderCases {
  static all(params) {
    return Api.request({
      url: getUrlByQueryPaginate(URL.CASE_LIST, params),
    });
  }
  static save(params) {
    return Api.request({
      url: URL.CASE_SAVE,
      method: 'POST',
      data: params,
      headers: {
        'X-Content-Security-Policy': "allow 'self'",
        accept: '*/*',
        'Content-Type': 'application/json',
      },
    })
      .then((result) => {
        alert('Save success!');
      })
      .catch((error) => {
        if (error.response && error.response.data) {
          const data = error.response.data;
          if (data.message) {
            alert(data.message);
          } else {
            alert(data[0].message);
          }
        } else {
          alert('Save erro: ', error.response);
          history.push('caseList');
        }
      });
  }
}

export default ProviderCases;
