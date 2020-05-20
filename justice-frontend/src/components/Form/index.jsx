import React, { Component, Fragment } from 'react';
import { Formik } from 'formik';
import Paper from '@material-ui/core/Paper';

class InputForm extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { initialValues, validations, onSubmit, render } = this.props;
    return (
      <Fragment>
        <Paper elevation={1}>
          <h1>{this.props.title}</h1>
          <Formik
            onSubmit={onSubmit}
            render={(props) => render({ ...props })}
            initialValues={initialValues}
            validationSchema={validations}
          />
        </Paper>
      </Fragment>
    );
  }
}

export default InputForm;
