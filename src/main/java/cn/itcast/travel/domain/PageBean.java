package cn.itcast.travel.domain;

import java.util.List;

/**
 * @authon timber
 * @description 分页对象
 * @date 2020/6/24 下午3:03
 */
public class PageBean<T> {
    private int totalCount; // 总记录数
    private int totalPage; // 总页数
    private int currentPage; // 当前页码
    private int pageSize; // 每页显示的条数
    private List<T> list; // 每页显示的数据集合

    private int start = 0; // 查询的起始索引

    /*
     * @Author: timber
     * @Description: 计算查询的起始索引
     * @Date: 2020/6/24 下午5:52
     * @param currentPage
     * @param pageSize
     * @Return
     */
    public int handleStart(int currentPage, int pageSize){
        return (currentPage - 1) + pageSize;
    }

    /*
     * @Author: timber
     * @Description: 计算查询总页码
     * @Date: 2020/6/24 下午5:52
     * @param totalCount
     * @param pageSize
     * @Return
     */
    public int handlePage(int totalCount, int pageSize){
        return totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
