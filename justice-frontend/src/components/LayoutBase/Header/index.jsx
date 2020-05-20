import React from 'react';

import { HeaderStyled } from './styled';

const Header = (props) => {
return <HeaderStyled>{props.children}</HeaderStyled>;
};

export default Header;
