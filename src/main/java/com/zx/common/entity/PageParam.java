package com.zx.common.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:35:16
 * //TODO 分页工具类
 */
public class PageParam implements Serializable {

    private static final long serialVersionUID = 6297178964005032338L;

    /**
     * 当前页数
     */
    private int pageNum; 

    /**
     * 每页记录数
     */
    private int numPerPage;

    public PageParam() {

    }

    public Map<String, Object> getParamMap() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("begin", (pageNum - 1) * numPerPage);
        paramMap.put("size", numPerPage);
        return paramMap;
    }

    public PageParam(int pageNum, int numPerPage) {
        super();
        this.pageNum = pageNum;
        this.numPerPage = numPerPage;
    }

    /** 当前页数 */
    public int getPageNum() {
        return pageNum;
    }

    /** 当前页数 */
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    /** 每页记录数 */
    public int getNumPerPage() {
        return numPerPage;
    }

    /** 每页记录数 */
    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    @Override
    public String toString() {
        return "PageParam [pageNum=" + pageNum + ", numPerPage=" + numPerPage + "]";
    }

}
