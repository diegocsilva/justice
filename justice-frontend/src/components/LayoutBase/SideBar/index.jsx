import React, { Component } from 'react';
import {
  SideNav,
  Toggle,
  Nav,
  NavIcon,
  NavItem,
  NavText,
  HomeIcon,
  NavHeader,
  NavTitle,
  FolderOpenIcon,
} from './styled';
import history from 'core/utils/history';

const navWidthCollapsed = 64;
const navWidthExpanded = 280;

export default class SideBar extends Component {
  state = {
    selected: 'home',
    expanded: false,
  };

  onSelect = (selected) => {
    this.setState({ selected: selected });
  };
  onToggle = (expanded) => {
    this.setState({ expanded: expanded });
  };
  navigate = (pathname) => () => {
    history.push(pathname);
    this.setState({ selected: pathname });
  };

  render() {
    const { expanded, selected } = this.state;

    return (
      <SideNav
        style={{ minWidth: expanded ? navWidthExpanded : navWidthCollapsed }}
        onSelect={this.onSelect}
        onToggle={this.onToggle}
      >
        <Toggle />
        <NavHeader expanded={expanded}>
          <NavTitle>Justice</NavTitle>
        </NavHeader>
        <Nav defaultSelected={selected}>
          <NavItem eventKey="home" onClick={this.navigate('/')}>
            <NavIcon>
              <HomeIcon />
            </NavIcon>
            <NavText style={{ paddingRight: 32 }} title="HOME">
              HOME
            </NavText>
          </NavItem>
          <NavItem eventKey="cases" onClick={this.navigate('/caseList')}>
            <NavIcon>
              <FolderOpenIcon />
            </NavIcon>
            <NavText style={{ paddingRight: 32 }} title="CASES">
              CASES
            </NavText>
          </NavItem>
        </Nav>
      </SideNav>
    );
  }
}
