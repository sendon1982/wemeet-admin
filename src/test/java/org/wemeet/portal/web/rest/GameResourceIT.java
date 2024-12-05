package org.wemeet.portal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.wemeet.portal.domain.GameAsserts.*;
import static org.wemeet.portal.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.wemeet.portal.IntegrationTest;
import org.wemeet.portal.domain.Game;
import org.wemeet.portal.repository.GameRepository;
import org.wemeet.portal.service.dto.GameDTO;
import org.wemeet.portal.service.mapper.GameMapper;

/**
 * Integration tests for the {@link GameResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class GameResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_RATING = 1D;
    private static final Double UPDATED_RATING = 2D;

    private static final String ENTITY_API_URL = "/api/games";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private MockMvc restGameMockMvc;

    private Game game;

    private Game insertedGame;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Game createEntity() {
        return new Game().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).rating(DEFAULT_RATING);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Game createUpdatedEntity() {
        return new Game().name(UPDATED_NAME).description(UPDATED_DESCRIPTION).rating(UPDATED_RATING);
    }

    @BeforeEach
    public void initTest() {
        game = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedGame != null) {
            gameRepository.delete(insertedGame);
            insertedGame = null;
        }
    }

    @Test
    void createGame() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);
        var returnedGameDTO = om.readValue(
            restGameMockMvc
                .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gameDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            GameDTO.class
        );

        // Validate the Game in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedGame = gameMapper.toEntity(returnedGameDTO);
        assertGameUpdatableFieldsEquals(returnedGame, getPersistedGame(returnedGame));

        insertedGame = returnedGame;
    }

    @Test
    void createGameWithExistingId() throws Exception {
        // Create the Game with an existing ID
        game.setId("existing_id");
        GameDTO gameDTO = gameMapper.toDto(game);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restGameMockMvc
            .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllGames() throws Exception {
        // Initialize the database
        insertedGame = gameRepository.save(game);

        // Get all the gameList
        restGameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(game.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING.doubleValue())));
    }

    @Test
    void getGame() throws Exception {
        // Initialize the database
        insertedGame = gameRepository.save(game);

        // Get the game
        restGameMockMvc
            .perform(get(ENTITY_API_URL_ID, game.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(game.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING.doubleValue()));
    }

    @Test
    void getNonExistingGame() throws Exception {
        // Get the game
        restGameMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingGame() throws Exception {
        // Initialize the database
        insertedGame = gameRepository.save(game);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the game
        Game updatedGame = gameRepository.findById(game.getId()).orElseThrow();
        updatedGame.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).rating(UPDATED_RATING);
        GameDTO gameDTO = gameMapper.toDto(updatedGame);

        restGameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gameDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(gameDTO))
            )
            .andExpect(status().isOk());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedGameToMatchAllProperties(updatedGame);
    }

    @Test
    void putNonExistingGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        game.setId(UUID.randomUUID().toString());

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, gameDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(gameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        game.setId(UUID.randomUUID().toString());

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(gameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        game.setId(UUID.randomUUID().toString());

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGameMockMvc
            .perform(put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(gameDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateGameWithPatch() throws Exception {
        // Initialize the database
        insertedGame = gameRepository.save(game);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the game using partial update
        Game partialUpdatedGame = new Game();
        partialUpdatedGame.setId(game.getId());

        partialUpdatedGame.description(UPDATED_DESCRIPTION);

        restGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGame.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGame))
            )
            .andExpect(status().isOk());

        // Validate the Game in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGameUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedGame, game), getPersistedGame(game));
    }

    @Test
    void fullUpdateGameWithPatch() throws Exception {
        // Initialize the database
        insertedGame = gameRepository.save(game);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the game using partial update
        Game partialUpdatedGame = new Game();
        partialUpdatedGame.setId(game.getId());

        partialUpdatedGame.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).rating(UPDATED_RATING);

        restGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedGame.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedGame))
            )
            .andExpect(status().isOk());

        // Validate the Game in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertGameUpdatableFieldsEquals(partialUpdatedGame, getPersistedGame(partialUpdatedGame));
    }

    @Test
    void patchNonExistingGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        game.setId(UUID.randomUUID().toString());

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, gameDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(gameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        game.setId(UUID.randomUUID().toString());

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(gameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        game.setId(UUID.randomUUID().toString());

        // Create the Game
        GameDTO gameDTO = gameMapper.toDto(game);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restGameMockMvc
            .perform(patch(ENTITY_API_URL).with(csrf()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(gameDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Game in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteGame() throws Exception {
        // Initialize the database
        insertedGame = gameRepository.save(game);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the game
        restGameMockMvc
            .perform(delete(ENTITY_API_URL_ID, game.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return gameRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Game getPersistedGame(Game game) {
        return gameRepository.findById(game.getId()).orElseThrow();
    }

    protected void assertPersistedGameToMatchAllProperties(Game expectedGame) {
        assertGameAllPropertiesEquals(expectedGame, getPersistedGame(expectedGame));
    }

    protected void assertPersistedGameToMatchUpdatableProperties(Game expectedGame) {
        assertGameAllUpdatablePropertiesEquals(expectedGame, getPersistedGame(expectedGame));
    }
}
