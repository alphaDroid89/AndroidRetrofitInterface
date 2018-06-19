
package com.retrofit.sample.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "im:id",
    "term",
    "scheme",
    "label"
})
public class CategoryAttributes{

    @JsonProperty("im:id")
    private String imId;
    @JsonProperty("term")
    private String term;
    @JsonProperty("scheme")
    private String scheme;
    @JsonProperty("label")
    private String label;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("im:id")
    public String getImId() {
        return imId;
    }

    @JsonProperty("im:id")
    public void setImId(String imId) {
        this.imId = imId;
    }

    @JsonProperty("term")
    public String getTerm() {
        return term;
    }

    @JsonProperty("term")
    public void setTerm(String term) {
        this.term = term;
    }

    @JsonProperty("scheme")
    public String getScheme() {
        return scheme;
    }

    @JsonProperty("scheme")
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
