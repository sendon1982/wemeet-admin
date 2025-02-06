package org.wemeet.portal.model.gstone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status {

    private int id;
    private String value;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
