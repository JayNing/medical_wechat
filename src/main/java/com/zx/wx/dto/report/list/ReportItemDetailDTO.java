package com.zx.wx.dto.report.list;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/9.
 */
public class ReportItemDetailDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String item;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ReportItemDetailDTO{" +
                "id='" + id + '\'' +
                ", item='" + item + '\'' +
                '}';
    }
}
