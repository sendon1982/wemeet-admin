import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './board-game.reducer';

export const BoardGameDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const boardGameEntity = useAppSelector(state => state.boardGame.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="boardGameDetailsHeading">
          <Translate contentKey="wemeetadminApp.boardGame.detail.title">BoardGame</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.id}</dd>
          <dt>
            <span id="englishName">
              <Translate contentKey="wemeetadminApp.boardGame.englishName">English Name</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.englishName}</dd>
          <dt>
            <span id="chineseName">
              <Translate contentKey="wemeetadminApp.boardGame.chineseName">Chinese Name</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.chineseName}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="wemeetadminApp.boardGame.description">Description</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.description}</dd>
          <dt>
            <span id="yearPublished">
              <Translate contentKey="wemeetadminApp.boardGame.yearPublished">Year Published</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.yearPublished}</dd>
          <dt>
            <span id="minPlayers">
              <Translate contentKey="wemeetadminApp.boardGame.minPlayers">Min Players</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.minPlayers}</dd>
          <dt>
            <span id="maxPlayers">
              <Translate contentKey="wemeetadminApp.boardGame.maxPlayers">Max Players</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.maxPlayers}</dd>
          <dt>
            <span id="playTimeMin">
              <Translate contentKey="wemeetadminApp.boardGame.playTimeMin">Play Time Min</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.playTimeMin}</dd>
          <dt>
            <span id="playTimeMax">
              <Translate contentKey="wemeetadminApp.boardGame.playTimeMax">Play Time Max</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.playTimeMax}</dd>
          <dt>
            <span id="minAge">
              <Translate contentKey="wemeetadminApp.boardGame.minAge">Min Age</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.minAge}</dd>
          <dt>
            <span id="suggestedAge">
              <Translate contentKey="wemeetadminApp.boardGame.suggestedAge">Suggested Age</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.suggestedAge}</dd>
          <dt>
            <span id="mechanics">
              <Translate contentKey="wemeetadminApp.boardGame.mechanics">Mechanics</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.mechanics}</dd>
          <dt>
            <span id="categories">
              <Translate contentKey="wemeetadminApp.boardGame.categories">Categories</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.categories}</dd>
          <dt>
            <span id="publishers">
              <Translate contentKey="wemeetadminApp.boardGame.publishers">Publishers</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.publishers}</dd>
          <dt>
            <span id="rating">
              <Translate contentKey="wemeetadminApp.boardGame.rating">Rating</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.rating}</dd>
          <dt>
            <span id="ratingVotes">
              <Translate contentKey="wemeetadminApp.boardGame.ratingVotes">Rating Votes</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.ratingVotes}</dd>
          <dt>
            <span id="complexity">
              <Translate contentKey="wemeetadminApp.boardGame.complexity">Complexity</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.complexity}</dd>
          <dt>
            <span id="complexityVotes">
              <Translate contentKey="wemeetadminApp.boardGame.complexityVotes">Complexity Votes</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.complexityVotes}</dd>
          <dt>
            <span id="officialUrl">
              <Translate contentKey="wemeetadminApp.boardGame.officialUrl">Official Url</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.officialUrl}</dd>
          <dt>
            <span id="bggUrl">
              <Translate contentKey="wemeetadminApp.boardGame.bggUrl">Bgg Url</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.bggUrl}</dd>
          <dt>
            <span id="gstoneUrl">
              <Translate contentKey="wemeetadminApp.boardGame.gstoneUrl">Gstone Url</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.gstoneUrl}</dd>
          <dt>
            <span id="thumbnailUrl">
              <Translate contentKey="wemeetadminApp.boardGame.thumbnailUrl">Thumbnail Url</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.thumbnailUrl}</dd>
          <dt>
            <span id="largeImageUrl">
              <Translate contentKey="wemeetadminApp.boardGame.largeImageUrl">Large Image Url</Translate>
            </span>
          </dt>
          <dd>{boardGameEntity.largeImageUrl}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="wemeetadminApp.boardGame.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {boardGameEntity.createdAt ? <TextFormat value={boardGameEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="wemeetadminApp.boardGame.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {boardGameEntity.updatedAt ? <TextFormat value={boardGameEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/board-game" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/board-game/${boardGameEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BoardGameDetail;
