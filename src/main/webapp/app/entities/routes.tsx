import React from 'react';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import { Route } from 'react-router-dom';
import BoardGame from './board-game';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="board-game/*" element={<BoardGame />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
