package org.wemeet.portal.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A BoardGame.
 */
@Document(collection = "board_game")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BoardGame implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull
    @Field("english_name")
    private String englishName;

    @NotNull
    @Field("chinese_name")
    private String chineseName;

    @Field("description")
    private String description;

    @Field("year_published")
    private Integer yearPublished;

    @Field("min_players")
    private Integer minPlayers;

    @Field("max_players")
    private Integer maxPlayers;

    @Field("play_time_min")
    private Integer playTimeMin;

    @Field("play_time_max")
    private Integer playTimeMax;

    @Field("min_age")
    private Integer minAge;

    @Field("suggested_age")
    private Integer suggestedAge;

    @NotNull
    @Field("mechanics")
    private String mechanics;

    @NotNull
    @Field("categories")
    private String categories;

    @Field("publishers")
    private String publishers;

    @Field("rating")
    private Double rating;

    @Field("rating_votes")
    private Long ratingVotes;

    @Field("complexity")
    private Double complexity;

    @Field("complexity_votes")
    private Long complexityVotes;

    @Field("official_url")
    private String officialUrl;

    @Field("bgg_url")
    private String bggUrl;

    @Field("gstone_url")
    private String gstoneUrl;

    @Field("thumbnail_url")
    private String thumbnailUrl;

    @Field("large_image_url")
    private String largeImageUrl;

    @Field("created_at")
    private ZonedDateTime createdAt;

    @Field("updated_at")
    private ZonedDateTime updatedAt;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public BoardGame id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public BoardGame englishName(String englishName) {
        this.setEnglishName(englishName);
        return this;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public BoardGame chineseName(String chineseName) {
        this.setChineseName(chineseName);
        return this;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getDescription() {
        return this.description;
    }

    public BoardGame description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearPublished() {
        return this.yearPublished;
    }

    public BoardGame yearPublished(Integer yearPublished) {
        this.setYearPublished(yearPublished);
        return this;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Integer getMinPlayers() {
        return this.minPlayers;
    }

    public BoardGame minPlayers(Integer minPlayers) {
        this.setMinPlayers(minPlayers);
        return this;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    public BoardGame maxPlayers(Integer maxPlayers) {
        this.setMaxPlayers(maxPlayers);
        return this;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getPlayTimeMin() {
        return this.playTimeMin;
    }

    public BoardGame playTimeMin(Integer playTimeMin) {
        this.setPlayTimeMin(playTimeMin);
        return this;
    }

    public void setPlayTimeMin(Integer playTimeMin) {
        this.playTimeMin = playTimeMin;
    }

    public Integer getPlayTimeMax() {
        return this.playTimeMax;
    }

    public BoardGame playTimeMax(Integer playTimeMax) {
        this.setPlayTimeMax(playTimeMax);
        return this;
    }

    public void setPlayTimeMax(Integer playTimeMax) {
        this.playTimeMax = playTimeMax;
    }

    public Integer getMinAge() {
        return this.minAge;
    }

    public BoardGame minAge(Integer minAge) {
        this.setMinAge(minAge);
        return this;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getSuggestedAge() {
        return this.suggestedAge;
    }

    public BoardGame suggestedAge(Integer suggestedAge) {
        this.setSuggestedAge(suggestedAge);
        return this;
    }

    public void setSuggestedAge(Integer suggestedAge) {
        this.suggestedAge = suggestedAge;
    }

    public String getMechanics() {
        return this.mechanics;
    }

    public BoardGame mechanics(String mechanics) {
        this.setMechanics(mechanics);
        return this;
    }

    public void setMechanics(String mechanics) {
        this.mechanics = mechanics;
    }

    public String getCategories() {
        return this.categories;
    }

    public BoardGame categories(String categories) {
        this.setCategories(categories);
        return this;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getPublishers() {
        return this.publishers;
    }

    public BoardGame publishers(String publishers) {
        this.setPublishers(publishers);
        return this;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public Double getRating() {
        return this.rating;
    }

    public BoardGame rating(Double rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getRatingVotes() {
        return this.ratingVotes;
    }

    public BoardGame ratingVotes(Long ratingVotes) {
        this.setRatingVotes(ratingVotes);
        return this;
    }

    public void setRatingVotes(Long ratingVotes) {
        this.ratingVotes = ratingVotes;
    }

    public Double getComplexity() {
        return this.complexity;
    }

    public BoardGame complexity(Double complexity) {
        this.setComplexity(complexity);
        return this;
    }

    public void setComplexity(Double complexity) {
        this.complexity = complexity;
    }

    public Long getComplexityVotes() {
        return this.complexityVotes;
    }

    public BoardGame complexityVotes(Long complexityVotes) {
        this.setComplexityVotes(complexityVotes);
        return this;
    }

    public void setComplexityVotes(Long complexityVotes) {
        this.complexityVotes = complexityVotes;
    }

    public String getOfficialUrl() {
        return this.officialUrl;
    }

    public BoardGame officialUrl(String officialUrl) {
        this.setOfficialUrl(officialUrl);
        return this;
    }

    public void setOfficialUrl(String officialUrl) {
        this.officialUrl = officialUrl;
    }

    public String getBggUrl() {
        return this.bggUrl;
    }

    public BoardGame bggUrl(String bggUrl) {
        this.setBggUrl(bggUrl);
        return this;
    }

    public void setBggUrl(String bggUrl) {
        this.bggUrl = bggUrl;
    }

    public String getGstoneUrl() {
        return this.gstoneUrl;
    }

    public BoardGame gstoneUrl(String gstoneUrl) {
        this.setGstoneUrl(gstoneUrl);
        return this;
    }

    public void setGstoneUrl(String gstoneUrl) {
        this.gstoneUrl = gstoneUrl;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public BoardGame thumbnailUrl(String thumbnailUrl) {
        this.setThumbnailUrl(thumbnailUrl);
        return this;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLargeImageUrl() {
        return this.largeImageUrl;
    }

    public BoardGame largeImageUrl(String largeImageUrl) {
        this.setLargeImageUrl(largeImageUrl);
        return this;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public BoardGame createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public BoardGame updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardGame)) {
            return false;
        }
        return getId() != null && getId().equals(((BoardGame) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BoardGame{" +
            "id=" + getId() +
            ", englishName='" + getEnglishName() + "'" +
            ", chineseName='" + getChineseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", yearPublished=" + getYearPublished() +
            ", minPlayers=" + getMinPlayers() +
            ", maxPlayers=" + getMaxPlayers() +
            ", playTimeMin=" + getPlayTimeMin() +
            ", playTimeMax=" + getPlayTimeMax() +
            ", minAge=" + getMinAge() +
            ", suggestedAge=" + getSuggestedAge() +
            ", mechanics='" + getMechanics() + "'" +
            ", categories='" + getCategories() + "'" +
            ", publishers='" + getPublishers() + "'" +
            ", rating=" + getRating() +
            ", ratingVotes=" + getRatingVotes() +
            ", complexity=" + getComplexity() +
            ", complexityVotes=" + getComplexityVotes() +
            ", officialUrl='" + getOfficialUrl() + "'" +
            ", bggUrl='" + getBggUrl() + "'" +
            ", gstoneUrl='" + getGstoneUrl() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", largeImageUrl='" + getLargeImageUrl() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
}
