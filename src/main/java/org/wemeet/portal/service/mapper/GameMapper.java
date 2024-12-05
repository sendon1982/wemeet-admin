package org.wemeet.portal.service.mapper;

import org.mapstruct.*;
import org.wemeet.portal.domain.Game;
import org.wemeet.portal.service.dto.GameDTO;

/**
 * Mapper for the entity {@link Game} and its DTO {@link GameDTO}.
 */
@Mapper(componentModel = "spring")
public interface GameMapper extends EntityMapper<GameDTO, Game> {}
