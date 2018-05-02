package com.liger.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by roc_peng on 2017/8/31.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class SearchResult implements Serializable {
    private long recordCount;
    private int totalPages;
    private List<SearchItem> itemList;
    public long getRecordCount() {
        return recordCount;
    }
    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public List<SearchItem> getItemList() {
        return itemList;
    }
    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "recordCount=" + recordCount +
                ", totalPages=" + totalPages +
                ", itemList=" + itemList +
                '}';
    }
}
