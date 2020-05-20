import React, { PureComponent } from 'react';
import { AppContainerStyled } from './styled';
import SideBar from '../../components/LayoutBase/SideBar';
import Content from '../../components/LayoutBase/Content';
import Header from '../../components/LayoutBase/Header';

class AppContainer extends PureComponent {
  render() {
    return (
      <AppContainerStyled>
        <SideBar></SideBar>
        <Header>
          <span>Justice</span>
        </Header>
        <Content>
          {this.props.children}
        </Content>
      </AppContainerStyled>
    );
  }
}

export default AppContainer;