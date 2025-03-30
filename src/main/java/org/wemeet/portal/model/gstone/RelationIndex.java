package org.wemeet.portal.model.gstone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelationIndex {

    public int id;
    public String name;

    public int is_expansion;
    public int expansion_type;

    public int publish_year;
    public int publish_month;
}
