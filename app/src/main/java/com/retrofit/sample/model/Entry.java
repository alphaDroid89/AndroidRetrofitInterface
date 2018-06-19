
package com.retrofit.sample.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "im:name",
    "rights",
    "im:price",
    "im:image",
    "im:artist",
    "title",
    "link",
    "id",
    "im:contentType",
    "category",
    "im:releaseDate"
})
public class Entry {

    @JsonProperty("im:name")
    private ImName imName;
    @JsonProperty("rights")
    private Rights rights;
    @JsonProperty("im:price")
    private ImPrice imPrice;
    @JsonProperty("im:image")
    private List<ImImage> imImage = null;
    @JsonProperty("im:artist")
    private ImArtist imArtist;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("link")
    private Link link;
    @JsonProperty("id")
    private Id id;
    @JsonProperty("im:contentType")
    private ImContentType imContentType;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("im:releaseDate")
    private ImReleaseDate imReleaseDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("im:name")
    public ImName getImName() {
        return imName;
    }

    @JsonProperty("im:name")
    public void setImName(ImName imName) {
        this.imName = imName;
    }

    @JsonProperty("rights")
    public Rights getRights() {
        return rights;
    }

    @JsonProperty("rights")
    public void setRights(Rights rights) {
        this.rights = rights;
    }

    @JsonProperty("im:price")
    public ImPrice getImPrice() {
        return imPrice;
    }

    @JsonProperty("im:price")
    public void setImPrice(ImPrice imPrice) {
        this.imPrice = imPrice;
    }

    @JsonProperty("im:image")
    public List<ImImage> getImImage() {
        return imImage;
    }

    @JsonProperty("im:image")
    public void setImImage(List<ImImage> imImage) {
        this.imImage = imImage;
    }

    @JsonProperty("im:artist")
    public ImArtist getImArtist() {
        return imArtist;
    }

    @JsonProperty("im:artist")
    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    @JsonProperty("link")
    public Link getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(Link link) {
        this.link = link;
    }

    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("im:contentType")
    public ImContentType getImContentType() {
        return imContentType;
    }

    @JsonProperty("im:contentType")
    public void setImContentType(ImContentType imContentType) {
        this.imContentType = imContentType;
    }

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("im:releaseDate")
    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }

    @JsonProperty("im:releaseDate")
    public void setImReleaseDate(ImReleaseDate imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
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
