import React, { Component } from 'react';
import CustomTable from '../../components/Table/index';
import getConfig, { URL } from '../../core/providers/api/config/index';
import AppContainer from '../AppContainer/index';
import history from '../../core/utils/history';
import { connect } from 'react-redux';

import { setCaseEdit } from '../CaseForm/store/actions';

const URL_LIST =  getConfig().baseURL + URL.CASE_LIST;

const columns = [
  { title: 'Folder', field: 'folder' },
  { title: 'Title', field: 'title' },
  { title: 'Responsible', field: 'responsible' },
  { title: 'Access', field: 'access' },
  { title: 'Creation Date', field: 'formattedDate' },
];

class CaseList extends Component {
  render() {
    const actions = [
      {
        icon: 'edit',
        tooltip: 'Edit Case',
        onClick: (event, rowData) => {
          this.props.setEdit(rowData);
          history.push('caseForm');
        },
      },
      (rowData) => ({
        icon: 'delete',
        tooltip: 'Delete User',
        onClick: (event, rowData) => alert('You want to delete ' + rowData.id),
        disabled: rowData.birthYear < 2000,
      }),
    ];

    return (
      <AppContainer>
        <CustomTable
          columns={columns}
          urlList={URL_LIST}
          title="Cases"
          actions={actions}
        />
      </AppContainer>
    );
  }
}

const mapStateToProps = (props) => {
  return {
    caseEdit: props.caseEdit.caseEdit,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    setEdit(row){
      const action = setCaseEdit(row)
      dispatch(action);
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(CaseList);
