package com.hongzan.base;

import java.util.List;

/**
 * 页面分页查询
 *
 * @param <T>
 * @author Administrator
 */
public class Page<T> {
    private String currentPage;// 当前页
    private String pageNum;// 页面数
    private Integer totalRecord;// 总条数
    private String totalPage;// 总页数
    private String previousPage;// 上一页
    private String nextPage;// 下一页
    private String start;
    private List<T> list;

    public Page() {  }

    // 构造方法
    public Page(String currentPage, String pageNum, Integer totalRecord, List<T> list) {
        this.currentPage = currentPage;
        this.pageNum = pageNum;
        this.totalRecord = totalRecord;
        /* 使用方法设置 */
        this.totalPage = getTotalPage(totalRecord, pageNum);
        this.previousPage = getPreviousPage(currentPage);
        this.nextPage = getNextPage(totalPage, currentPage);
        this.list = list;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    // 下一页
    private String getNextPage(String totalPage2, String currentPage2) {
        int result = 0;
        if (currentPage2 != null && totalPage2 != null) {
            int totalPageInt = Integer.parseInt(totalPage2);
            int currentPageInt = Integer.parseInt(currentPage2);
            if (currentPageInt >= totalPageInt) {
                result = currentPageInt;
            } else {
                result = currentPageInt + 1;
            }
        }
        return result + "";
    }

    // 上一页
    private String getPreviousPage(String currentPage2) {
        int result = 0;
        if (currentPage2 != null) {
            int currentPageInt = Integer.parseInt(currentPage2);
            if (currentPageInt <= 1) {
                result = currentPageInt;
            } else {
                result = currentPageInt - 1;
            }
        }
        return result + "";
    }

    // 获取总页数
    private String getTotalPage(Integer totalRecord2, String pageNum2) {
        int quoitient = 0;
        if (pageNum2 != null && totalRecord2 != null) {
            int totalRecordInt = totalRecord2;
            int pageNumInt = Integer.parseInt(pageNum2);
            if (pageNumInt == 0) {
                return quoitient + "";
            }
            quoitient = totalRecordInt % pageNumInt;
            if (quoitient == 0) {
                quoitient = totalRecordInt / pageNumInt;
            } else {
                quoitient = (totalRecordInt / pageNumInt) + 1;
            }
        }
        return quoitient + "";
    }

}
