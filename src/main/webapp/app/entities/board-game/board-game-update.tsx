import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { createEntity, getEntity, reset, updateEntity } from './board-game.reducer';

export const BoardGameUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const boardGameEntity = useAppSelector(state => state.boardGame.entity);
  const loading = useAppSelector(state => state.boardGame.loading);
  const updating = useAppSelector(state => state.boardGame.updating);
  const updateSuccess = useAppSelector(state => state.boardGame.updateSuccess);

  const handleClose = () => {
    navigate(`/board-game${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.yearPublished !== undefined && typeof values.yearPublished !== 'number') {
      values.yearPublished = Number(values.yearPublished);
    }
    if (values.minPlayers !== undefined && typeof values.minPlayers !== 'number') {
      values.minPlayers = Number(values.minPlayers);
    }
    if (values.maxPlayers !== undefined && typeof values.maxPlayers !== 'number') {
      values.maxPlayers = Number(values.maxPlayers);
    }
    if (values.playTimeMin !== undefined && typeof values.playTimeMin !== 'number') {
      values.playTimeMin = Number(values.playTimeMin);
    }
    if (values.playTimeMax !== undefined && typeof values.playTimeMax !== 'number') {
      values.playTimeMax = Number(values.playTimeMax);
    }
    if (values.minAge !== undefined && typeof values.minAge !== 'number') {
      values.minAge = Number(values.minAge);
    }
    if (values.suggestedAge !== undefined && typeof values.suggestedAge !== 'number') {
      values.suggestedAge = Number(values.suggestedAge);
    }
    if (values.rating !== undefined && typeof values.rating !== 'number') {
      values.rating = Number(values.rating);
    }
    if (values.ratingVotes !== undefined && typeof values.ratingVotes !== 'number') {
      values.ratingVotes = Number(values.ratingVotes);
    }
    if (values.complexity !== undefined && typeof values.complexity !== 'number') {
      values.complexity = Number(values.complexity);
    }
    if (values.complexityVotes !== undefined && typeof values.complexityVotes !== 'number') {
      values.complexityVotes = Number(values.complexityVotes);
    }
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...boardGameEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          ...boardGameEntity,
          createdAt: convertDateTimeFromServer(boardGameEntity.createdAt),
          updatedAt: convertDateTimeFromServer(boardGameEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="wemeetadminApp.boardGame.home.createOrEditLabel" data-cy="BoardGameCreateUpdateHeading">
            <Translate contentKey="wemeetadminApp.boardGame.home.createOrEditLabel">Create or edit a BoardGame</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="board-game-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.englishName')}
                id="board-game-englishName"
                name="englishName"
                data-cy="englishName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.chineseName')}
                id="board-game-chineseName"
                name="chineseName"
                data-cy="chineseName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.description')}
                id="board-game-description"
                name="description"
                data-cy="description"
                type="textarea"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.yearPublished')}
                id="board-game-yearPublished"
                name="yearPublished"
                data-cy="yearPublished"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.minPlayers')}
                id="board-game-minPlayers"
                name="minPlayers"
                data-cy="minPlayers"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.maxPlayers')}
                id="board-game-maxPlayers"
                name="maxPlayers"
                data-cy="maxPlayers"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.playTimeMin')}
                id="board-game-playTimeMin"
                name="playTimeMin"
                data-cy="playTimeMin"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.playTimeMax')}
                id="board-game-playTimeMax"
                name="playTimeMax"
                data-cy="playTimeMax"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.minAge')}
                id="board-game-minAge"
                name="minAge"
                data-cy="minAge"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.suggestedAge')}
                id="board-game-suggestedAge"
                name="suggestedAge"
                data-cy="suggestedAge"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.mechanics')}
                id="board-game-mechanics"
                name="mechanics"
                data-cy="mechanics"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.categories')}
                id="board-game-categories"
                name="categories"
                data-cy="categories"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.publishers')}
                id="board-game-publishers"
                name="publishers"
                data-cy="publishers"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.rating')}
                id="board-game-rating"
                name="rating"
                data-cy="rating"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.ratingVotes')}
                id="board-game-ratingVotes"
                name="ratingVotes"
                data-cy="ratingVotes"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.complexity')}
                id="board-game-complexity"
                name="complexity"
                data-cy="complexity"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.complexityVotes')}
                id="board-game-complexityVotes"
                name="complexityVotes"
                data-cy="complexityVotes"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.officialUrl')}
                id="board-game-officialUrl"
                name="officialUrl"
                data-cy="officialUrl"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.bggUrl')}
                id="board-game-bggUrl"
                name="bggUrl"
                data-cy="bggUrl"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.gstoneUrl')}
                id="board-game-gstoneUrl"
                name="gstoneUrl"
                data-cy="gstoneUrl"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.thumbnailUrl')}
                id="board-game-thumbnailUrl"
                name="thumbnailUrl"
                data-cy="thumbnailUrl"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.largeImageUrl')}
                id="board-game-largeImageUrl"
                name="largeImageUrl"
                data-cy="largeImageUrl"
                type="text"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.createdAt')}
                id="board-game-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('wemeetadminApp.boardGame.updatedAt')}
                id="board-game-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/board-game" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default BoardGameUpdate;
