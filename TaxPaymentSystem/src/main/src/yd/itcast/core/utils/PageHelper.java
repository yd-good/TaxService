/*
信息:
*/
package yd.itcast.core.utils;

import java.util.ArrayList;
import java.util.List;
public class PageHelper {
//    总记录数
    private long totalCount;
//    当前页号
    private int currentPageNo;
//    总页数
    private int totalPagesCount;
//    页大小
    private int pageSize;
//    列表记录
    private List items;

    public PageHelper() {
    }

    public PageHelper(long totalCount, int currentPageNo, int pageSize, List items) {
        this.totalCount = totalCount;
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.items = items==null?new ArrayList():items;
        if (totalCount!=0){
            this.totalPagesCount=(totalCount%pageSize==0)?(int)(totalCount/pageSize):(int)(totalCount/pageSize)+1;
        }else{
             this.totalPagesCount=0;
        }
    }

    @Override
    public String toString() {
        return "PageHelper{" +
                "totalCount=" + totalCount +
                ", currentPageNo=" + currentPageNo +
                ", totalPagesCount=" + totalPagesCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                '}';
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getTotalPagesCount() {
        return totalPagesCount;
    }

    public void setTotalPagesCount(int totalPagesCount) {
        this.totalPagesCount = totalPagesCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }
}
