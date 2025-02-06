import React from 'react';
import { Translate } from 'react-jhipster';
import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/board-game">
        <Translate contentKey="global.menu.entities.boardGame" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/tag-info">
        <Translate contentKey="global.menu.entities.tagInfo" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
