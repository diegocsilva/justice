import React from 'react';
import MaterialTable from 'material-table';
import { getUrlByQueryPaginate } from '../../core/providers/api/util';

const CustomTable = (props) => {
  const { columns, actions, urlList, title } = props;
  
  return (
    <MaterialTable
      columns={columns}
      data={(query) =>
        new Promise((resolve, reject) => {
          let url = getUrlByQueryPaginate(urlList, query);
          fetch(url)
            .then((response) => response.json())
            .then((result) => {
              resolve({
                data: result.content,
                page: result.pageable.pageNumber,
                totalCount: result.totalElements,
              });
            })
            .catch((err) => {
              resolve({
                data: [],
                page: 0,
                totalCount: 0,
              });
            });
        })
      }
      title={title}
      options={{
        actionsColumnIndex: 10,
        selection: false,
        search: false,
      }}
      actions={actions ? actions : ''}
    />
  );
};

export default CustomTable;
