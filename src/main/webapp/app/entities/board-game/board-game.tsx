import React, { useEffect, useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { JhiItemCount, JhiPagination, TextFormat, Translate, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortDown, faSortUp } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './board-game.reducer';

export const BoardGame = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const boardGameList = useAppSelector(state => state.boardGame.entities);
  const loading = useAppSelector(state => state.boardGame.loading);
  const totalItems = useAppSelector(state => state.boardGame.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    }
    return order === ASC ? faSortUp : faSortDown;
  };

  return (
    <div>
      <h2 id="board-game-heading" data-cy="BoardGameHeading">
        <Translate contentKey="wemeetadminApp.boardGame.home.title">Board Games</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="wemeetadminApp.boardGame.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/board-game/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="wemeetadminApp.boardGame.home.createLabel">Create new Board Game</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {boardGameList && boardGameList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="wemeetadminApp.boardGame.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('englishName')}>
                  <Translate contentKey="wemeetadminApp.boardGame.englishName">English Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('englishName')} />
                </th>
                <th className="hand" onClick={sort('chineseName')}>
                  <Translate contentKey="wemeetadminApp.boardGame.chineseName">Chinese Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('chineseName')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="wemeetadminApp.boardGame.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('yearPublished')}>
                  <Translate contentKey="wemeetadminApp.boardGame.yearPublished">Year Published</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('yearPublished')} />
                </th>
                <th className="hand" onClick={sort('minPlayers')}>
                  <Translate contentKey="wemeetadminApp.boardGame.minPlayers">Min Players</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('minPlayers')} />
                </th>
                <th className="hand" onClick={sort('maxPlayers')}>
                  <Translate contentKey="wemeetadminApp.boardGame.maxPlayers">Max Players</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('maxPlayers')} />
                </th>
                <th className="hand" onClick={sort('playTimeMin')}>
                  <Translate contentKey="wemeetadminApp.boardGame.playTimeMin">Play Time Min</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('playTimeMin')} />
                </th>
                <th className="hand" onClick={sort('playTimeMax')}>
                  <Translate contentKey="wemeetadminApp.boardGame.playTimeMax">Play Time Max</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('playTimeMax')} />
                </th>
                <th className="hand" onClick={sort('minAge')}>
                  <Translate contentKey="wemeetadminApp.boardGame.minAge">Min Age</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('minAge')} />
                </th>
                <th className="hand" onClick={sort('suggestedAge')}>
                  <Translate contentKey="wemeetadminApp.boardGame.suggestedAge">Suggested Age</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('suggestedAge')} />
                </th>
                <th className="hand" onClick={sort('mechanics')}>
                  <Translate contentKey="wemeetadminApp.boardGame.mechanics">Mechanics</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mechanics')} />
                </th>
                <th className="hand" onClick={sort('categories')}>
                  <Translate contentKey="wemeetadminApp.boardGame.categories">Categories</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('categories')} />
                </th>
                <th className="hand" onClick={sort('publishers')}>
                  <Translate contentKey="wemeetadminApp.boardGame.publishers">Publishers</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('publishers')} />
                </th>
                <th className="hand" onClick={sort('rating')}>
                  <Translate contentKey="wemeetadminApp.boardGame.rating">Rating</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rating')} />
                </th>
                <th className="hand" onClick={sort('ratingVotes')}>
                  <Translate contentKey="wemeetadminApp.boardGame.ratingVotes">Rating Votes</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ratingVotes')} />
                </th>
                <th className="hand" onClick={sort('complexity')}>
                  <Translate contentKey="wemeetadminApp.boardGame.complexity">Complexity</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('complexity')} />
                </th>
                <th className="hand" onClick={sort('complexityVotes')}>
                  <Translate contentKey="wemeetadminApp.boardGame.complexityVotes">Complexity Votes</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('complexityVotes')} />
                </th>
                <th className="hand" onClick={sort('officialUrl')}>
                  <Translate contentKey="wemeetadminApp.boardGame.officialUrl">Official Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('officialUrl')} />
                </th>
                <th className="hand" onClick={sort('bggUrl')}>
                  <Translate contentKey="wemeetadminApp.boardGame.bggUrl">Bgg Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bggUrl')} />
                </th>
                <th className="hand" onClick={sort('gstoneUrl')}>
                  <Translate contentKey="wemeetadminApp.boardGame.gstoneUrl">Gstone Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('gstoneUrl')} />
                </th>
                <th className="hand" onClick={sort('thumbnailUrl')}>
                  <Translate contentKey="wemeetadminApp.boardGame.thumbnailUrl">Thumbnail Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('thumbnailUrl')} />
                </th>
                <th className="hand" onClick={sort('largeImageUrl')}>
                  <Translate contentKey="wemeetadminApp.boardGame.largeImageUrl">Large Image Url</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('largeImageUrl')} />
                </th>
                <th className="hand" onClick={sort('createdAt')}>
                  <Translate contentKey="wemeetadminApp.boardGame.createdAt">Created At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdAt')} />
                </th>
                <th className="hand" onClick={sort('updatedAt')}>
                  <Translate contentKey="wemeetadminApp.boardGame.updatedAt">Updated At</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedAt')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {boardGameList.map((boardGame, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/board-game/${boardGame.id}`} color="link" size="sm">
                      {boardGame.id}
                    </Button>
                  </td>
                  <td>{boardGame.englishName}</td>
                  <td>{boardGame.chineseName}</td>
                  <td>{boardGame.description}</td>
                  <td>{boardGame.yearPublished}</td>
                  <td>{boardGame.minPlayers}</td>
                  <td>{boardGame.maxPlayers}</td>
                  <td>{boardGame.playTimeMin}</td>
                  <td>{boardGame.playTimeMax}</td>
                  <td>{boardGame.minAge}</td>
                  <td>{boardGame.suggestedAge}</td>
                  <td>{boardGame.mechanics}</td>
                  <td>{boardGame.categories}</td>
                  <td>{boardGame.publishers}</td>
                  <td>{boardGame.rating}</td>
                  <td>{boardGame.ratingVotes}</td>
                  <td>{boardGame.complexity}</td>
                  <td>{boardGame.complexityVotes}</td>
                  <td>{boardGame.officialUrl}</td>
                  <td>{boardGame.bggUrl}</td>
                  <td>{boardGame.gstoneUrl}</td>
                  <td>{boardGame.thumbnailUrl}</td>
                  <td>{boardGame.largeImageUrl}</td>
                  <td>{boardGame.createdAt ? <TextFormat type="date" value={boardGame.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{boardGame.updatedAt ? <TextFormat type="date" value={boardGame.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/board-game/${boardGame.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/board-game/${boardGame.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/board-game/${boardGame.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="wemeetadminApp.boardGame.home.notFound">No Board Games found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={boardGameList && boardGameList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default BoardGame;
