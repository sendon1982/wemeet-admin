package org.wemeet.portal.model.gstone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GstoneRequest {

    private int page;

    private int category;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
