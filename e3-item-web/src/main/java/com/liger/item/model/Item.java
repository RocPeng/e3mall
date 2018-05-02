package com.liger.item.model;

/**
 * Created by roc_peng on 2017/9/1.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class Item extends com.liger.model.Item {
    private String[] images;

    public void setImages(String[] images) {
        this.images = images;
    }
    public String[] getImages() {
        String image2 = this.getImage();
        if (image2 != null && !"".equals(image2)) {
            String[] strings = image2.split(",");
            return strings;
        }
        return null;
    }

    public Item() {

    }

    public Item(com.liger.model.Item item) {
        this.setBarcode(item.getBarcode());
        this.setCid(item.getCid());
        this.setCreated(item.getCreated());
        this.setId(item.getId());
        this.setImage(item.getImage());
        this.setNum(item.getNum());
        this.setPrice(item.getPrice());
        this.setSellPoint(item.getSellPoint());
        this.setStatus(item.getStatus());
        this.setTitle(item.getTitle());
        this.setUpdated(item.getUpdated());
    }

}
