
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
    "im:bundleId"
})
public class IdAttributes{

    @JsonProperty("im:id")
    private String imId;
    @JsonProperty("im:bundleId")
    private String imBundleId;
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

    @JsonProperty("im:bundleId")
    public String getImBundleId() {
        return imBundleId;
    }

    @JsonProperty("im:bundleId")
    public void setImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
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
