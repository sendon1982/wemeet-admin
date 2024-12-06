import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BoardGame from './board-game';
import BoardGameDetail from './board-game-detail';
import BoardGameUpdate from './board-game-update';
import BoardGameDeleteDialog from './board-game-delete-dialog';

const BoardGameRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<BoardGame />} />
    <Route path="new" element={<BoardGameUpdate />} />
    <Route path=":id">
      <Route index element={<BoardGameDetail />} />
      <Route path="edit" element={<BoardGameUpdate />} />
      <Route path="delete" element={<BoardGameDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BoardGameRoutes;
