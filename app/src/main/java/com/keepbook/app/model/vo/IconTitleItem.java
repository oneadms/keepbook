package com.keepbook.app.model.vo;

/**
 * com.keepbook.app.view.fragment.record.PayFragment 的 recyclerview 的图标文字item
 */
public class IconTitleItem {
    private String  title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImageId() {
        return ImageId;
    }

    public void setImageId(Integer imageId) {
        ImageId = imageId;
    }

    public IconTitleItem() {

    }

    public IconTitleItem(String title, Integer imageId) {
        this.title = title;
        ImageId = imageId;
    }

    private Integer ImageId;
}
