import { TextField } from '@material-ui/core';
import styled from 'styled-components';

export const DatePickerStyled = styled(TextField)`
  margin-top: 0.8rem !important;
  margin-bottom: 0.8rem !important;
  margin-left: 1vh !important;
  height: 9vh;

  box-shadow: 0 0 0px 1px #c4c4c4;
  border-radius: 3px;
  outline: none !important;

  :hover {
    box-shadow: 0 0 0px 1px #222;
    border-radius: 3px;
  }

  :active {
    box-shadow: 0 0 0px 1px #3f51b5;
    border-radius: 3px;
  }
  /* box-shadow: 0 0 0px 1px ;
    border-radius: 3px; */

  div {
    display: flex;
    /* min-width: 84vmin; */
    font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;

    border-bottom: 0px !important;
    :before{
      border-bottom: 0px !important;
    }
    :after{
      border-bottom: 0px;
    }
    :hover{
      border-bottom: 0px;
    }
    :focus{
      border-bottom: 0px;
    }
  }    

  input {
    padding: 16px 0 7px;
  }
`;
