package com.founder.interservice.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EasyUIPage implements Serializable {
    private static final long serialVersionUID = 1L;

    public EasyUIPage() {
    }

    private int page = 1;

    private int begin;

    private int end;

    private int total = 0;

    private String sort;

    private String order;

    private int rownum = 20;

    private List<?> rows = new ArrayList();
    private String flag;
    private String pagePara;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public List<?> getRows() {
        return rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setPagePara(Integer rows) {
        if (rows != null) {
            this.begin = (this.page - 1) * rows;
            this.end = this.page * rows;
        } else {
            this.begin = (this.page - 1) * rownum;
            this.end = this.page * rownum;
        }
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    /**
     * @return the pagePara
     */
    public String getPagePara() {
        return pagePara;
    }

    /**
     * @param pagePara the pagePara to set
     */
    public void setPagePara(String pagePara) {
        this.pagePara = pagePara;
    }
}
