import styled from 'styled-components';
import { OutlinedInput } from '@material-ui/core';

export const Container = styled.div`
  font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  background-color: #fff;
  min-height: 85vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 2rem;
  color: #222;

  input,
  textarea {
    min-width: 80vmin;
  }

  ul > li {
    margin: 15px 0;
    list-style: none;
    display: flex;
    align-items: center;

    img {
      border-radius: 25px;
      margin-right: 15px;
    }
  }

  a {
    padding: 10px 0;
    color: tomato;
    display: block;
  }
`;

export const OutlinedInputStyled = styled(OutlinedInput)``;
