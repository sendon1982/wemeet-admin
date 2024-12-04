import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Game from './game';
import { Route } from 'react-router-dom';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="game/*" element={<Game />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
