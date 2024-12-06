package org.wemeet.portal.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link org.wemeet.portal.domain.BoardGame} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BoardGameDTO implements Serializable {

    private String id;

    @NotNull
    private String englishName;

    @NotNull
    private String chineseName;

    private String description;

    private Integer yearPublished;

    private Integer minPlayers;

    private Integer maxPlayers;

    private Integer playTimeMin;

    private Integer playTimeMax;

    private Integer minAge;

    private Integer suggestedAge;

    @NotNull
    private String mechanics;

    @NotNull
    private String categories;

    private String publishers;

    private Double rating;

    private Long ratingVotes;

    private Double complexity;

    private Long complexityVotes;

    private String officialUrl;

    private String bggUrl;

    private String gstoneUrl;

    private String thumbnailUrl;

    private String largeImageUrl;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(Integer minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getPlayTimeMin() {
        return playTimeMin;
    }

    public void setPlayTimeMin(Integer playTimeMin) {
        this.playTimeMin = playTimeMin;
    }

    public Integer getPlayTimeMax() {
        return playTimeMax;
    }

    public void setPlayTimeMax(Integer playTimeMax) {
        this.playTimeMax = playTimeMax;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getSuggestedAge() {
        return suggestedAge;
    }

    public void setSuggestedAge(Integer suggestedAge) {
        this.suggestedAge = suggestedAge;
    }

    public String getMechanics() {
        return mechanics;
    }

    public void setMechanics(String mechanics) {
        this.mechanics = mechanics;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getPublishers() {
        return publishers;
    }

    public void setPublishers(String publishers) {
        this.publishers = publishers;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getRatingVotes() {
        return ratingVotes;
    }

    public void setRatingVotes(Long ratingVotes) {
        this.ratingVotes = ratingVotes;
    }

    public Double getComplexity() {
        return complexity;
    }

    public void setComplexity(Double complexity) {
        this.complexity = complexity;
    }

    public Long getComplexityVotes() {
        return complexityVotes;
    }

    public void setComplexityVotes(Long complexityVotes) {
        this.complexityVotes = complexityVotes;
    }

    public String getOfficialUrl() {
        return officialUrl;
    }

    public void setOfficialUrl(String officialUrl) {
        this.officialUrl = officialUrl;
    }

    public String getBggUrl() {
        return bggUrl;
    }

    public void setBggUrl(String bggUrl) {
        this.bggUrl = bggUrl;
    }

    public String getGstoneUrl() {
        return gstoneUrl;
    }

    public void setGstoneUrl(String gstoneUrl) {
        this.gstoneUrl = gstoneUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public void setLargeImageUrl(String largeImageUrl) {
        this.largeImageUrl = largeImageUrl;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardGameDTO)) {
            return false;
        }

        BoardGameDTO boardGameDTO = (BoardGameDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, boardGameDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BoardGameDTO{" +
            "id='" + getId() + "'" +
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
