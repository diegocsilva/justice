import React, { Component } from 'react';
import { bindActionCreators } from 'redux';
import * as Yup from 'yup';
import { connect } from 'react-redux';

import history from '../../core/utils/history';
import Form from '../../components/Form';
import { create } from './store/actions';
import AppContainer from '../AppContainer/index';
import { Container } from './styled';
import Column from '../../components/Grid/Column';
import Row from '../../components/Grid/Row';
import TxtField from '../../components/TextField';
import FormCtrl from '../../components/FormControl';
import DatePicker from '../../components/DatePicker';
import Button from '../../components/Button';
import { InputLabel, Select, OutlinedInput, MenuItem } from '@material-ui/core';
import { Tag } from '../../components/Tag';

const initialValues = {
  customers: '',
  folder: '',
  title: '',
  tags: [],
  description: '',
  comments: '',
  responsible: '',
  access: '',
  creationDate: '',
};

const KeyCodes = {
  comma: 188,
  enter: 13,
};

const delimiters = [KeyCodes.comma, KeyCodes.enter];

class CaseForm extends Component {
  create = () => this.props.create;

  render() {
    return (
      <AppContainer>
        <Container>
          <Form
            title={'Case Form'}
            render={renderForm}
            initialValues={initialValues}
            onSubmit={create}
          />
        </Container>
      </AppContainer>
    );
  }
}

export const renderForm = (props) => {
  const {
    values: {
      customers,
      folder,
      title,
      tags,
      description,
      comments,
      responsible,
      access,
      creationDate,
    },
    onSubmit,
    handleSubmit,
    errors,
    touched,
    handleChange,
    isValid,
    setFieldTouched,
  } = props;

  const change = (name, e) => {
    e.persist();
    handleChange(e);
    setFieldTouched(name, true, false);
  };

  const onChangeDate = (date) => {
    creationDate = date;
    change.bind(null, 'creationDate');
  };

  const handleAddChip = (tag) => {
    tags = [...tags, tag];
    change.bind(null, 'tag');
  };

  const handleDeleteChip = (tag, i) => {
    tags = tags.filter((t, index) => index !== i);
    change.bind(null, 'tag');
  };

  return (
    <form onSubmit={handleSubmit}>
      <Column>
        <TxtField
          id="customers"
          name="customers"
          label="Customers"
          variant="outlined"
          required={true}
          value={customers}
          helperText={touched.customers ? errors.customers : ''}
          error={touched.customers && Boolean(errors.customers)}
          onChange={change.bind(null, 'customers')}
        />
        <TxtField
          id="folder"
          name="folder"
          label="Folder"
          variant="outlined"
          value={folder}
          helperText={touched.folder ? errors.folder : ''}
          error={touched.folder && Boolean(errors.folder)}
          onChange={change.bind(null, 'folder')}
        />
        <TxtField
          id="title"
          name="title"
          label="Title"
          variant="outlined"
          required={true}
          value={title}
          helperText={touched.title ? errors.title : ''}
          error={touched.title && Boolean(errors.title)}
          onChange={change.bind(null, 'title')}
        />

        <Tag
          id="tag"
          name="tag"
          label="Tag"
          value={tags}
          onAdd={(tag) => handleAddChip(tag)}
          onDelete={(tag, index) => handleDeleteChip(tag, index)}
        />

        <TxtField
          id="description"
          name="description"
          placeholder="Case description"
          multiline
          rows={4}
          rowsMax={6}
          variant="outlined"
          value={description}
          helperText={touched.description ? errors.description : ''}
          error={touched.description && Boolean(errors.description)}
          onChange={change.bind(null, 'description')}
        />
        <TxtField
          id="comments"
          name="comments"
          placeholder="Comments"
          multiline
          rows={4}
          rowsMax={6}
          variant="outlined"
          value={comments}
          helperText={touched.comments ? errors.comments : ''}
          error={touched.comments && Boolean(errors.comments)}
          onChange={change.bind(null, 'comments')}
        />
        <TxtField
          id="responsible"
          name="responsible"
          label="Responsible"
          variant="outlined"
          required={true}
          value={responsible}
          helperText={touched.responsible ? errors.responsible : ''}
          error={touched.responsible && Boolean(errors.responsible)}
          onChange={change.bind(null, 'responsible')}
        />

        <FormCtrl variant="outlined" fullWidth={true}>
          <InputLabel id="accessLabel">Access</InputLabel>
          <Select
            id="access"
            name="access"
            label="Access"
            input={<OutlinedInput labelWidth={100} />}
            value={access}
            error={touched.access && Boolean(errors.access)}
            onChange={change.bind(null, 'access')}
          >
            <MenuItem value="">
              <em>None</em>
            </MenuItem>
            <MenuItem value={'PRIVATE'}>Private</MenuItem>
            <MenuItem value={'PUBLIC'}>Public</MenuItem>
          </Select>
        </FormCtrl>

        <DatePicker
          id="creationDate"
          name="creationDate"
          label="Creation Date"
          variant="outlined"
          required={true}
          value={creationDate}
          error={touched.creationDate && Boolean(errors.creationDate)}
          onChange={onChangeDate}
        />
      </Column>
      <Row>
        <Button type="submit" disabled={!isValid}>
          Save
        </Button>
        <Button
          backgroundColor="#db4144"
          colorText="#949494"
          margin="3rem 1rem 1rem 1rem"
          alignRight="auto"
          onClick={() => history.push('/caseList')}
        >
          Cancel
        </Button>
      </Row>
    </form>
  );
};

const mapDispatchToProps = (dispatch) =>
  bindActionCreators({ create }, dispatch);

export default connect()(CaseForm);
