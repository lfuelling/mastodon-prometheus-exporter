package net.k40s.mastoexport.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Object representation of a single custom emoji.
 */
public class CustomEmoji implements Serializable {

    private String shortcode;

    private String url;

    @JsonProperty("static_url")
    private String staticUrl;

    @JsonProperty("visible_in_picker")
    private Boolean visibleInPicker;

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public Boolean getVisibleInPicker() {
        return visibleInPicker;
    }

    public void setVisibleInPicker(Boolean visibleInPicker) {
        this.visibleInPicker = visibleInPicker;
    }
}
