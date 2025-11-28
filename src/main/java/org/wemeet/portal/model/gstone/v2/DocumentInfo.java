package org.wemeet.portal.model.gstone.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "doc_list", "num" })
public class DocumentInfo {

    @JsonProperty("doc_list")
    private List<Doc> docList;

    @JsonProperty("num")
    private Integer num;

    @JsonProperty("doc_list")
    public List<Doc> getDocList() {
        return docList;
    }

    @JsonProperty("doc_list")
    public void setDocList(List<Doc> docList) {
        this.docList = docList;
    }

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }
}
