package org.wemeet.portal.service.mapper;

import org.mapstruct.*;
import org.wemeet.portal.domain.BoardGame;
import org.wemeet.portal.service.dto.BoardGameDTO;

/**
 * Mapper for the entity {@link BoardGame} and its DTO {@link BoardGameDTO}.
 */
@Mapper(componentModel = "spring")
public interface BoardGameMapper extends EntityMapper<BoardGameDTO, BoardGame> {}
