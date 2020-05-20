import { TextField } from '@material-ui/core';
import styled from 'styled-components';

const TxtFieldStyled = styled(TextField)`
  margin-top: 0.8rem !important;
  margin-bottom: 0.8rem !important;

  input {
    display: flex;
    font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  }

  textarea {
    font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  }
`;

export default TxtFieldStyled;
