import styled from 'styled-components';
import { FormControl } from '@material-ui/core';

const FormCtrl = styled(FormControl)`
  margin-top: 0.8rem !important;
  margin-bottom: 0.8rem !important;

  div input {
    display: flex;
    font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  }

  textarea {
    font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  }
`;

export default FormCtrl;
