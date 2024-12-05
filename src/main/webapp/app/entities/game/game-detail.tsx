import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './game.reducer';

export const GameDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const gameEntity = useAppSelector(state => state.game.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="gameDetailsHeading">
          <Translate contentKey="wemeetadminApp.game.detail.title">Game</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{gameEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="wemeetadminApp.game.name">Name</Translate>
            </span>
          </dt>
          <dd>{gameEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="wemeetadminApp.game.description">Description</Translate>
            </span>
          </dt>
          <dd>{gameEntity.description}</dd>
          <dt>
            <span id="rating">
              <Translate contentKey="wemeetadminApp.game.rating">Rating</Translate>
            </span>
          </dt>
          <dd>{gameEntity.rating}</dd>
        </dl>
        <Button tag={Link} to="/game" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/game/${gameEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default GameDetail;
