/*
信息:
*/
package yd.itcast.core.action;

import com.opensymphony.xwork2.ActionSupport;
import yd.itcast.core.utils.PageHelper;

public class BaseAction extends ActionSupport {
    //    当前页号
    private int currentPageNo;
    //    当前页面大小
    private int pageSize;
    //    分页工具类
    protected PageHelper pageHelper;

    protected static String oldsAttributes;

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getPageSize() {
        if (pageSize<1) pageSize=3;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper = pageHelper;
    }
}
