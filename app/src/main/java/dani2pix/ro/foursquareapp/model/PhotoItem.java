package dani2pix.ro.foursquareapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Domnica on 24.08.2016.
 */
public class PhotoItem {
    @SerializedName("prefix")
    private String prefix;
    @SerializedName("suffix")
    private String suffix;
    @SerializedName("width")
    private String width;
    @SerializedName("height")
    private String height;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
