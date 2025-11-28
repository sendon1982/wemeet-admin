package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "sleeves_hight", "description", "sleeves_width", "card_number", "id", "sleeves_size" })
public class SleevesL {

    @JsonProperty("sleeves_hight")
    private Double sleevesHight;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sleeves_width")
    private Double sleevesWidth;

    @JsonProperty("card_number")
    private Integer cardNumber;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("sleeves_size")
    private SleevesSize sleevesSize;

    @JsonProperty("sleeves_hight")
    public Double getSleevesHight() {
        return sleevesHight;
    }

    @JsonProperty("sleeves_hight")
    public void setSleevesHight(Double sleevesHight) {
        this.sleevesHight = sleevesHight;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("sleeves_width")
    public Double getSleevesWidth() {
        return sleevesWidth;
    }

    @JsonProperty("sleeves_width")
    public void setSleevesWidth(Double sleevesWidth) {
        this.sleevesWidth = sleevesWidth;
    }

    @JsonProperty("card_number")
    public Integer getCardNumber() {
        return cardNumber;
    }

    @JsonProperty("card_number")
    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("sleeves_size")
    public SleevesSize getSleevesSize() {
        return sleevesSize;
    }

    @JsonProperty("sleeves_size")
    public void setSleevesSize(SleevesSize sleevesSize) {
        this.sleevesSize = sleevesSize;
    }
}
