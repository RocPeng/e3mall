package com.liger.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by roc_peng on 2017/8/29.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class EasyUIDataGridResult implements Serializable {

    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public EasyUIDataGridResult() {
    }

    public EasyUIDataGridResult (Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDataGridResult (Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyUIDataGridResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
