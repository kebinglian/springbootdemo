package com.kbldemo.config.http.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * v1.0.1 2020/1/12.
 * 分页插件返回数据对象处理。
 */
@Data
public class PageHelperResult<T> {
    // 数据对象
    private List<T> items;
    // 每页条数
    private Long pageSize;
    // 数据页码
    private Long page;
    // 总记录数
    private Long total;

    public PageHelperResult(Page pageInfo){
        super();
        this.setItems(pageInfo.getRecords());
        this.setPage(pageInfo.getCurrent());
        this.setPageSize(pageInfo.getSize());
        this.setTotal(pageInfo.getTotal());
    }
}
