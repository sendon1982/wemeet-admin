package org.wemeet.portal.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelationGameIndex {

    public int is_mm;
    public String sub_category;
    public int is_expansion;
    public int mod_type;
    public int is_pg;
    public String box_url;
    public String primary_language;
    public int id;
    public String cover_url_s;
    public int publish_year;

    public int status;
    public int expansion_type;
    public double width_height;
    public int is_glight;
    public String cover_url;
    public FirstPicture first_picture;

    public String name;
    public double game_hotness_value;
    public int sales_mode_id;
    public String box_url_s;
    public int publish_month;
}
