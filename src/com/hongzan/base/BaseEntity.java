package com.hongzan.base;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * 基础实体类父类
 */
public class BaseEntity implements Serializable {
    private String id;//对应id

    @NotEmpty
    private String name;//对应name
    private Integer currentPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        if (currentPage!=null&&currentPage>0){
            this.currentPage=(currentPage-1*5);
        }
        this.currentPage = currentPage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
