import styled from 'styled-components';

import SideNav, {
  Toggle,
  Nav,
  NavItem,
  NavIcon,
  NavText,
} from '@trendmicro/react-sidenav';
import { Home } from '@styled-icons/fa-solid/Home';
import { List } from '@styled-icons/fa-solid/List';
import { FolderOpen } from '@styled-icons/fa-solid/FolderOpen';

// SideNav
const StyledSideNav = styled(SideNav)`
  background-color: #fff;
  border-right: 1px solid #ddd;
`;
StyledSideNav.defaultProps = SideNav.defaultProps;

// Toggle
const StyledToggle = styled(Toggle)`
  background-color: #db3d44;
`;
StyledToggle.defaultProps = Toggle.defaultProps;

// Nav
const StyledNav = styled(Nav)`
  background-color: #fff;
  && > [class*='sidenav-navitem--'],
  && > [class*='sidenav-navitem--']:hover {
    > [class*='navitem--'] {
      [class*='navicon--'] {
        &,
        > * {
          color: #666;
        }
      }
      [class*='navtext--'] {
        &,
        > * {
          color: #666;
          font-weight: bold;
        }
      }
    }
  }
  && > [class*='sidenav-navitem--'][class*='highlighted--'],
  && > [class*='sidenav-navitem--'][class*='highlighted--']:hover {
    > [class*='navitem--'] {
      [class*='navicon--'],
      [class*='navtext--'] {
        &,
        > * {
          color: #db3d44;
        }
      }
      [class*='navtext--'] {
        font-weight: 700;
        font-weight: bold;
      }
    }
  }
`;
StyledNav.defaultProps = Nav.defaultProps;

// NavItem
const StyledNavItem = styled(NavItem)`
  &&&:hover {
    [class*='navtext--'] {
      color: #222;
    }
  }
`;
StyledNavItem.defaultProps = NavItem.defaultProps;

// NavIcon
const StyledNavIcon = styled(NavIcon)`
  color: #222;
`;
StyledNavIcon.defaultProps = NavIcon.defaultProps;

// NavText
const StyledNavText = styled(NavText)``;

StyledNavText.defaultProps = NavText.defaultProps;

const HomeIcon = styled(Home).attrs({ width: '1.75em' })``;
const ListIcon = styled(List).attrs({ width: '1.75em' })``;
const FolderOpenIcon = styled(FolderOpen).attrs({ width: '1.75em' })``;

const NavHeader = styled.div`
  display: ${(props) => (props.expanded ? 'block' : 'none')};
  white-space: nowrap;
  background-color: #db3d44;
  color: #fff;
  > * {
    color: inherit;
    background-color: inherit;
  }
`;

const NavTitle = styled.div`
  font-size: 2em;
  line-height: 20px;
  padding: 22px 0;
`;

export {
  StyledSideNav as SideNav,
  StyledToggle as Toggle,
  StyledNav as Nav,
  StyledNavItem as NavItem,
  StyledNavIcon as NavIcon,
  StyledNavText as NavText,
  HomeIcon,
  ListIcon,
  FolderOpenIcon,
  NavHeader,
  NavTitle,
};
