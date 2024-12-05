import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Game from './game';
import GameDetail from './game-detail';
import GameUpdate from './game-update';
import GameDeleteDialog from './game-delete-dialog';

const GameRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Game />} />
    <Route path="new" element={<GameUpdate />} />
    <Route path=":id">
      <Route index element={<GameDetail />} />
      <Route path="edit" element={<GameUpdate />} />
      <Route path="delete" element={<GameDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default GameRoutes;
