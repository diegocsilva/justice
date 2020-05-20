import styled from "styled-components";

const Button = styled.button.attrs()`
  background-color: ${(props) => props.backgroundColor || '#ddd'};
  color: ${(props) => props.colorText || '#222'} !important;
  display: inline-block;
  padding: 0.35em 1.2em;
  border: 0.1em solid ${(props) => props.colorBorder || '#565252'};
  margin: 0 0.3em 0.3em 0;
  border-radius: 0.12em;
  box-sizing: border-box;
  text-decoration: none;
  font-family: 'Roboto', 'Helvetica', 'Arial', sans-serif;
  font-weight: 300;
  text-align: center;
  transition: all 0.2s;
  font-size: 1.5rem;
  margin-left: ${(props) => props.alignRight || '0'};

  :hover {
    color: ${(props) => props.colorTextHover || '#000000'};
    background-color: ${(props) => props.backgroundColorHover || '#827777'};
  }
`;

export default Button;