package org.wemeet.portal.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A BoardGame.
 */
@Document(collection = "board_game_v2")
@Setter
@Getter
@Data
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BoardGameV2 implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Indexed
    private String englishName;

    @Indexed
    private String chineseName;

    private int totalTime;

    private int averageTimePerPlayer;

    private String primaryLanguage;

    private Set<String> categories;

    private Set<String> themes;

    private String mode;

    private String boxUrl;

    private String coverUrl;

    private String status;

    private int difficulty;

    private int publishYear;

    private List<Integer> playerNums;

    private int minPlayers;

    private int maxPlayers;

    private double gameHotnessValue;

    private double wemeetRating;

    private double gstoneRating;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
