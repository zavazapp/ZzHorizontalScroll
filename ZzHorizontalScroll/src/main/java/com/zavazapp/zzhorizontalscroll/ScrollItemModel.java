package com.zavazapp.zzhorizontalscroll;

/**
 * Default model for adapter
 */
public class ScrollItemModel {

    private int imageRes;
    private String imageUrl;
    private String dsc;
    private String link;
    private String badge;

    public ScrollItemModel(int imageRes, String dsc, String link, String badge) {
        this.imageRes = imageRes;
        this.dsc = dsc;
        this.link = link;
        this.badge = badge;
    }

    public ScrollItemModel(String imageUrl, String dsc, String link, String badge) {
        this.imageUrl = imageUrl;
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
