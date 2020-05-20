import React from 'react';
import { Router, Switch, Route } from 'react-router-dom';

import history from 'core/utils/history';
import Main from 'containers/Main';
import CaseList from './containers/CaseList/index';
import CaseForm from './containers/CaseForm/index';

const Routes = () => (
  <Router history={history}>
    <Switch>
      <Route exact path="/" component={Main} />
      <Route path="/caseList" component={CaseList} />
      <Route path="/caseform" component={CaseForm} />
    </Switch>
  </Router>
);

export default Routes;
