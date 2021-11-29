package com.zavazapp.zzhorizontalscroll;

import java.net.URI;

/**
 * Default model for adapter
 */
public class ScrollItemModel {

    private int imageRes;
    private String imageUrl;
    private URI uri;
    private String dsc;
    private String link;
    private String badge;

    /**
     * @param imageRes - Image to be shown on specific scroll item
     * @param dsc - Description. Usually used in OnSetTitle callback
     * @param link - Link. Usually used in OnScrollItemClickListener callback
     * @param badge - Small text to be shown as badge on specific scroll item
     */
    public ScrollItemModel(int imageRes, String dsc, String link, String badge) {
        this.imageRes = imageRes;
        this.dsc = dsc;
        this.link = link;
        this.badge = badge;
    }

    /**
     * @param imageUrl - http link for image to be shown on specific scroll item
     * @param dsc - Description. Usually used in OnSetTitle callback
     * @param link - Link. Usually used in OnScrollItemClickListener callback
     * @param badge - Small text to be shown as badge on specific scroll item
     */
    public ScrollItemModel(String imageUrl, String dsc, String link, String badge) {
        this.imageUrl = imageUrl;
        this.dsc = dsc;
        this.link = link;
        this.badge = badge;
    }

    public ScrollItemModel(URI uri, String dsc, String link, String badge) {
        this.uri = uri;
        this.dsc = dsc;
        this.link = link;
        this.badge = badge;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
