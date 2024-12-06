package org.wemeet.portal.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.wemeet.portal.domain.BoardGameAsserts.*;
import static org.wemeet.portal.web.rest.TestUtil.createUpdateProxyForBean;
import static org.wemeet.portal.web.rest.TestUtil.sameInstant;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
import org.wemeet.portal.domain.BoardGame;
import org.wemeet.portal.repository.BoardGameRepository;
import org.wemeet.portal.service.dto.BoardGameDTO;
import org.wemeet.portal.service.mapper.BoardGameMapper;

/**
 * Integration tests for the {@link BoardGameResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BoardGameResourceIT {

    private static final String DEFAULT_ENGLISH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENGLISH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHINESE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHINESE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR_PUBLISHED = 1;
    private static final Integer UPDATED_YEAR_PUBLISHED = 2;

    private static final Integer DEFAULT_MIN_PLAYERS = 1;
    private static final Integer UPDATED_MIN_PLAYERS = 2;

    private static final Integer DEFAULT_MAX_PLAYERS = 1;
    private static final Integer UPDATED_MAX_PLAYERS = 2;

    private static final Integer DEFAULT_PLAY_TIME_MIN = 1;
    private static final Integer UPDATED_PLAY_TIME_MIN = 2;

    private static final Integer DEFAULT_PLAY_TIME_MAX = 1;
    private static final Integer UPDATED_PLAY_TIME_MAX = 2;

    private static final Integer DEFAULT_MIN_AGE = 1;
    private static final Integer UPDATED_MIN_AGE = 2;

    private static final Integer DEFAULT_SUGGESTED_AGE = 1;
    private static final Integer UPDATED_SUGGESTED_AGE = 2;

    private static final String DEFAULT_MECHANICS = "AAAAAAAAAA";
    private static final String UPDATED_MECHANICS = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORIES = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORIES = "BBBBBBBBBB";

    private static final String DEFAULT_PUBLISHERS = "AAAAAAAAAA";
    private static final String UPDATED_PUBLISHERS = "BBBBBBBBBB";

    private static final Double DEFAULT_RATING = 1D;
    private static final Double UPDATED_RATING = 2D;

    private static final Long DEFAULT_RATING_VOTES = 1L;
    private static final Long UPDATED_RATING_VOTES = 2L;

    private static final Double DEFAULT_COMPLEXITY = 1D;
    private static final Double UPDATED_COMPLEXITY = 2D;

    private static final Long DEFAULT_COMPLEXITY_VOTES = 1L;
    private static final Long UPDATED_COMPLEXITY_VOTES = 2L;

    private static final String DEFAULT_OFFICIAL_URL = "AAAAAAAAAA";
    private static final String UPDATED_OFFICIAL_URL = "BBBBBBBBBB";

    private static final String DEFAULT_BGG_URL = "AAAAAAAAAA";
    private static final String UPDATED_BGG_URL = "BBBBBBBBBB";

    private static final String DEFAULT_GSTONE_URL = "AAAAAAAAAA";
    private static final String UPDATED_GSTONE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL_URL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_URL = "BBBBBBBBBB";

    private static final String DEFAULT_LARGE_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_LARGE_IMAGE_URL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String ENTITY_API_URL = "/api/board-games";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BoardGameRepository boardGameRepository;

    @Autowired
    private BoardGameMapper boardGameMapper;

    @Autowired
    private MockMvc restBoardGameMockMvc;

    private BoardGame boardGame;

    private BoardGame insertedBoardGame;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BoardGame createEntity() {
        return new BoardGame()
            .englishName(DEFAULT_ENGLISH_NAME)
            .chineseName(DEFAULT_CHINESE_NAME)
            .description(DEFAULT_DESCRIPTION)
            .yearPublished(DEFAULT_YEAR_PUBLISHED)
            .minPlayers(DEFAULT_MIN_PLAYERS)
            .maxPlayers(DEFAULT_MAX_PLAYERS)
            .playTimeMin(DEFAULT_PLAY_TIME_MIN)
            .playTimeMax(DEFAULT_PLAY_TIME_MAX)
            .minAge(DEFAULT_MIN_AGE)
            .suggestedAge(DEFAULT_SUGGESTED_AGE)
            .mechanics(DEFAULT_MECHANICS)
            .categories(DEFAULT_CATEGORIES)
            .publishers(DEFAULT_PUBLISHERS)
            .rating(DEFAULT_RATING)
            .ratingVotes(DEFAULT_RATING_VOTES)
            .complexity(DEFAULT_COMPLEXITY)
            .complexityVotes(DEFAULT_COMPLEXITY_VOTES)
            .officialUrl(DEFAULT_OFFICIAL_URL)
            .bggUrl(DEFAULT_BGG_URL)
            .gstoneUrl(DEFAULT_GSTONE_URL)
            .thumbnailUrl(DEFAULT_THUMBNAIL_URL)
            .largeImageUrl(DEFAULT_LARGE_IMAGE_URL)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BoardGame createUpdatedEntity() {
        return new BoardGame()
            .englishName(UPDATED_ENGLISH_NAME)
            .chineseName(UPDATED_CHINESE_NAME)
            .description(UPDATED_DESCRIPTION)
            .yearPublished(UPDATED_YEAR_PUBLISHED)
            .minPlayers(UPDATED_MIN_PLAYERS)
            .maxPlayers(UPDATED_MAX_PLAYERS)
            .playTimeMin(UPDATED_PLAY_TIME_MIN)
            .playTimeMax(UPDATED_PLAY_TIME_MAX)
            .minAge(UPDATED_MIN_AGE)
            .suggestedAge(UPDATED_SUGGESTED_AGE)
            .mechanics(UPDATED_MECHANICS)
            .categories(UPDATED_CATEGORIES)
            .publishers(UPDATED_PUBLISHERS)
            .rating(UPDATED_RATING)
            .ratingVotes(UPDATED_RATING_VOTES)
            .complexity(UPDATED_COMPLEXITY)
            .complexityVotes(UPDATED_COMPLEXITY_VOTES)
            .officialUrl(UPDATED_OFFICIAL_URL)
            .bggUrl(UPDATED_BGG_URL)
            .gstoneUrl(UPDATED_GSTONE_URL)
            .thumbnailUrl(UPDATED_THUMBNAIL_URL)
            .largeImageUrl(UPDATED_LARGE_IMAGE_URL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
    }

    @BeforeEach
    public void initTest() {
        boardGame = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedBoardGame != null) {
            boardGameRepository.delete(insertedBoardGame);
            insertedBoardGame = null;
        }
    }

    @Test
    void createBoardGame() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the BoardGame
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);
        var returnedBoardGameDTO = om.readValue(
            restBoardGameMockMvc
                .perform(
                    post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(boardGameDTO))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            BoardGameDTO.class
        );

        // Validate the BoardGame in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedBoardGame = boardGameMapper.toEntity(returnedBoardGameDTO);
        assertBoardGameUpdatableFieldsEquals(returnedBoardGame, getPersistedBoardGame(returnedBoardGame));

        insertedBoardGame = returnedBoardGame;
    }

    @Test
    void createBoardGameWithExistingId() throws Exception {
        // Create the BoardGame with an existing ID
        boardGame.setId("existing_id");
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoardGameMockMvc
            .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(boardGameDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkEnglishNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        boardGame.setEnglishName(null);

        // Create the BoardGame, which fails.
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        restBoardGameMockMvc
            .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(boardGameDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkChineseNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        boardGame.setChineseName(null);

        // Create the BoardGame, which fails.
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        restBoardGameMockMvc
            .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(boardGameDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkMechanicsIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        boardGame.setMechanics(null);

        // Create the BoardGame, which fails.
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        restBoardGameMockMvc
            .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(boardGameDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCategoriesIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        boardGame.setCategories(null);

        // Create the BoardGame, which fails.
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        restBoardGameMockMvc
            .perform(post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(boardGameDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllBoardGames() throws Exception {
        // Initialize the database
        insertedBoardGame = boardGameRepository.save(boardGame);

        // Get all the boardGameList
        restBoardGameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boardGame.getId())))
            .andExpect(jsonPath("$.[*].englishName").value(hasItem(DEFAULT_ENGLISH_NAME)))
            .andExpect(jsonPath("$.[*].chineseName").value(hasItem(DEFAULT_CHINESE_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].yearPublished").value(hasItem(DEFAULT_YEAR_PUBLISHED)))
            .andExpect(jsonPath("$.[*].minPlayers").value(hasItem(DEFAULT_MIN_PLAYERS)))
            .andExpect(jsonPath("$.[*].maxPlayers").value(hasItem(DEFAULT_MAX_PLAYERS)))
            .andExpect(jsonPath("$.[*].playTimeMin").value(hasItem(DEFAULT_PLAY_TIME_MIN)))
            .andExpect(jsonPath("$.[*].playTimeMax").value(hasItem(DEFAULT_PLAY_TIME_MAX)))
            .andExpect(jsonPath("$.[*].minAge").value(hasItem(DEFAULT_MIN_AGE)))
            .andExpect(jsonPath("$.[*].suggestedAge").value(hasItem(DEFAULT_SUGGESTED_AGE)))
            .andExpect(jsonPath("$.[*].mechanics").value(hasItem(DEFAULT_MECHANICS)))
            .andExpect(jsonPath("$.[*].categories").value(hasItem(DEFAULT_CATEGORIES)))
            .andExpect(jsonPath("$.[*].publishers").value(hasItem(DEFAULT_PUBLISHERS)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].ratingVotes").value(hasItem(DEFAULT_RATING_VOTES.intValue())))
            .andExpect(jsonPath("$.[*].complexity").value(hasItem(DEFAULT_COMPLEXITY.doubleValue())))
            .andExpect(jsonPath("$.[*].complexityVotes").value(hasItem(DEFAULT_COMPLEXITY_VOTES.intValue())))
            .andExpect(jsonPath("$.[*].officialUrl").value(hasItem(DEFAULT_OFFICIAL_URL)))
            .andExpect(jsonPath("$.[*].bggUrl").value(hasItem(DEFAULT_BGG_URL)))
            .andExpect(jsonPath("$.[*].gstoneUrl").value(hasItem(DEFAULT_GSTONE_URL)))
            .andExpect(jsonPath("$.[*].thumbnailUrl").value(hasItem(DEFAULT_THUMBNAIL_URL)))
            .andExpect(jsonPath("$.[*].largeImageUrl").value(hasItem(DEFAULT_LARGE_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(sameInstant(DEFAULT_CREATED_AT))))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(sameInstant(DEFAULT_UPDATED_AT))));
    }

    @Test
    void getBoardGame() throws Exception {
        // Initialize the database
        insertedBoardGame = boardGameRepository.save(boardGame);

        // Get the boardGame
        restBoardGameMockMvc
            .perform(get(ENTITY_API_URL_ID, boardGame.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(boardGame.getId()))
            .andExpect(jsonPath("$.englishName").value(DEFAULT_ENGLISH_NAME))
            .andExpect(jsonPath("$.chineseName").value(DEFAULT_CHINESE_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.yearPublished").value(DEFAULT_YEAR_PUBLISHED))
            .andExpect(jsonPath("$.minPlayers").value(DEFAULT_MIN_PLAYERS))
            .andExpect(jsonPath("$.maxPlayers").value(DEFAULT_MAX_PLAYERS))
            .andExpect(jsonPath("$.playTimeMin").value(DEFAULT_PLAY_TIME_MIN))
            .andExpect(jsonPath("$.playTimeMax").value(DEFAULT_PLAY_TIME_MAX))
            .andExpect(jsonPath("$.minAge").value(DEFAULT_MIN_AGE))
            .andExpect(jsonPath("$.suggestedAge").value(DEFAULT_SUGGESTED_AGE))
            .andExpect(jsonPath("$.mechanics").value(DEFAULT_MECHANICS))
            .andExpect(jsonPath("$.categories").value(DEFAULT_CATEGORIES))
            .andExpect(jsonPath("$.publishers").value(DEFAULT_PUBLISHERS))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING.doubleValue()))
            .andExpect(jsonPath("$.ratingVotes").value(DEFAULT_RATING_VOTES.intValue()))
            .andExpect(jsonPath("$.complexity").value(DEFAULT_COMPLEXITY.doubleValue()))
            .andExpect(jsonPath("$.complexityVotes").value(DEFAULT_COMPLEXITY_VOTES.intValue()))
            .andExpect(jsonPath("$.officialUrl").value(DEFAULT_OFFICIAL_URL))
            .andExpect(jsonPath("$.bggUrl").value(DEFAULT_BGG_URL))
            .andExpect(jsonPath("$.gstoneUrl").value(DEFAULT_GSTONE_URL))
            .andExpect(jsonPath("$.thumbnailUrl").value(DEFAULT_THUMBNAIL_URL))
            .andExpect(jsonPath("$.largeImageUrl").value(DEFAULT_LARGE_IMAGE_URL))
            .andExpect(jsonPath("$.createdAt").value(sameInstant(DEFAULT_CREATED_AT)))
            .andExpect(jsonPath("$.updatedAt").value(sameInstant(DEFAULT_UPDATED_AT)));
    }

    @Test
    void getNonExistingBoardGame() throws Exception {
        // Get the boardGame
        restBoardGameMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    void putExistingBoardGame() throws Exception {
        // Initialize the database
        insertedBoardGame = boardGameRepository.save(boardGame);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the boardGame
        BoardGame updatedBoardGame = boardGameRepository.findById(boardGame.getId()).orElseThrow();
        updatedBoardGame
            .englishName(UPDATED_ENGLISH_NAME)
            .chineseName(UPDATED_CHINESE_NAME)
            .description(UPDATED_DESCRIPTION)
            .yearPublished(UPDATED_YEAR_PUBLISHED)
            .minPlayers(UPDATED_MIN_PLAYERS)
            .maxPlayers(UPDATED_MAX_PLAYERS)
            .playTimeMin(UPDATED_PLAY_TIME_MIN)
            .playTimeMax(UPDATED_PLAY_TIME_MAX)
            .minAge(UPDATED_MIN_AGE)
            .suggestedAge(UPDATED_SUGGESTED_AGE)
            .mechanics(UPDATED_MECHANICS)
            .categories(UPDATED_CATEGORIES)
            .publishers(UPDATED_PUBLISHERS)
            .rating(UPDATED_RATING)
            .ratingVotes(UPDATED_RATING_VOTES)
            .complexity(UPDATED_COMPLEXITY)
            .complexityVotes(UPDATED_COMPLEXITY_VOTES)
            .officialUrl(UPDATED_OFFICIAL_URL)
            .bggUrl(UPDATED_BGG_URL)
            .gstoneUrl(UPDATED_GSTONE_URL)
            .thumbnailUrl(UPDATED_THUMBNAIL_URL)
            .largeImageUrl(UPDATED_LARGE_IMAGE_URL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(updatedBoardGame);

        restBoardGameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, boardGameDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(boardGameDTO))
            )
            .andExpect(status().isOk());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBoardGameToMatchAllProperties(updatedBoardGame);
    }

    @Test
    void putNonExistingBoardGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        boardGame.setId(UUID.randomUUID().toString());

        // Create the BoardGame
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoardGameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, boardGameDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(boardGameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchBoardGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        boardGame.setId(UUID.randomUUID().toString());

        // Create the BoardGame
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardGameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(boardGameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamBoardGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        boardGame.setId(UUID.randomUUID().toString());

        // Create the BoardGame
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardGameMockMvc
            .perform(put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(boardGameDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateBoardGameWithPatch() throws Exception {
        // Initialize the database
        insertedBoardGame = boardGameRepository.save(boardGame);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the boardGame using partial update
        BoardGame partialUpdatedBoardGame = new BoardGame();
        partialUpdatedBoardGame.setId(boardGame.getId());

        partialUpdatedBoardGame
            .maxPlayers(UPDATED_MAX_PLAYERS)
            .categories(UPDATED_CATEGORIES)
            .publishers(UPDATED_PUBLISHERS)
            .ratingVotes(UPDATED_RATING_VOTES)
            .largeImageUrl(UPDATED_LARGE_IMAGE_URL)
            .updatedAt(UPDATED_UPDATED_AT);

        restBoardGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBoardGame.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBoardGame))
            )
            .andExpect(status().isOk());

        // Validate the BoardGame in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBoardGameUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBoardGame, boardGame),
            getPersistedBoardGame(boardGame)
        );
    }

    @Test
    void fullUpdateBoardGameWithPatch() throws Exception {
        // Initialize the database
        insertedBoardGame = boardGameRepository.save(boardGame);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the boardGame using partial update
        BoardGame partialUpdatedBoardGame = new BoardGame();
        partialUpdatedBoardGame.setId(boardGame.getId());

        partialUpdatedBoardGame
            .englishName(UPDATED_ENGLISH_NAME)
            .chineseName(UPDATED_CHINESE_NAME)
            .description(UPDATED_DESCRIPTION)
            .yearPublished(UPDATED_YEAR_PUBLISHED)
            .minPlayers(UPDATED_MIN_PLAYERS)
            .maxPlayers(UPDATED_MAX_PLAYERS)
            .playTimeMin(UPDATED_PLAY_TIME_MIN)
            .playTimeMax(UPDATED_PLAY_TIME_MAX)
            .minAge(UPDATED_MIN_AGE)
            .suggestedAge(UPDATED_SUGGESTED_AGE)
            .mechanics(UPDATED_MECHANICS)
            .categories(UPDATED_CATEGORIES)
            .publishers(UPDATED_PUBLISHERS)
            .rating(UPDATED_RATING)
            .ratingVotes(UPDATED_RATING_VOTES)
            .complexity(UPDATED_COMPLEXITY)
            .complexityVotes(UPDATED_COMPLEXITY_VOTES)
            .officialUrl(UPDATED_OFFICIAL_URL)
            .bggUrl(UPDATED_BGG_URL)
            .gstoneUrl(UPDATED_GSTONE_URL)
            .thumbnailUrl(UPDATED_THUMBNAIL_URL)
            .largeImageUrl(UPDATED_LARGE_IMAGE_URL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT);

        restBoardGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBoardGame.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBoardGame))
            )
            .andExpect(status().isOk());

        // Validate the BoardGame in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBoardGameUpdatableFieldsEquals(partialUpdatedBoardGame, getPersistedBoardGame(partialUpdatedBoardGame));
    }

    @Test
    void patchNonExistingBoardGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        boardGame.setId(UUID.randomUUID().toString());

        // Create the BoardGame
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoardGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, boardGameDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(boardGameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchBoardGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        boardGame.setId(UUID.randomUUID().toString());

        // Create the BoardGame
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardGameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(boardGameDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamBoardGame() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        boardGame.setId(UUID.randomUUID().toString());

        // Create the BoardGame
        BoardGameDTO boardGameDTO = boardGameMapper.toDto(boardGame);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardGameMockMvc
            .perform(
                patch(ENTITY_API_URL).with(csrf()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(boardGameDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BoardGame in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteBoardGame() throws Exception {
        // Initialize the database
        insertedBoardGame = boardGameRepository.save(boardGame);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the boardGame
        restBoardGameMockMvc
            .perform(delete(ENTITY_API_URL_ID, boardGame.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return boardGameRepository.count();
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

    protected BoardGame getPersistedBoardGame(BoardGame boardGame) {
        return boardGameRepository.findById(boardGame.getId()).orElseThrow();
    }

    protected void assertPersistedBoardGameToMatchAllProperties(BoardGame expectedBoardGame) {
        assertBoardGameAllPropertiesEquals(expectedBoardGame, getPersistedBoardGame(expectedBoardGame));
    }

    protected void assertPersistedBoardGameToMatchUpdatableProperties(BoardGame expectedBoardGame) {
        assertBoardGameAllUpdatablePropertiesEquals(expectedBoardGame, getPersistedBoardGame(expectedBoardGame));
    }
}
