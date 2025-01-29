package org.wemeet.portal.model.gstone;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private int isMm;
    private int totalTime;
    private List<String> subCategory;
    private List<String> proCategory;
    private int isExpansion;
    private int modType;
    private int isPg;
    private String boxUrl;
    private String primaryLanguage;
    private int id;
    private List<Category> category;
    private String coverUrlS;
    private Map<String, Object> ifLoginInfo;
    private int averageTimePerPlayer;
    private List<Theme> theme;
    private int salesModeId;
    private RoleSexInfo roleSexInfo;
    private SalesMode salesMode;
    private Status status;
    private List<String> designer;
    private int expansionType;
    private double widthHeight;
    private int isGlight;
    private OrderInfo orderInfo;
    private String coverUrl;
    private double bayesAverage;
    private Picture firstPicture;
    private List<Integer> playerNum;
    private String name;
    private int difficulty;
    private Map<String, Object> mmRatingInfo;
    private double gameHotnessValue;
    private int publishYear;
    private Mode mode;
    private String boxUrlS;
    private double gstoneRating;
    private Map<String, Object> priceInfo;
    // Getters and setters
}
