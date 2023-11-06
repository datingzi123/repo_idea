package com.lagou.domain;

import java.util.List;

public class Tmenu {

    private Integer id;
    private String name;
    private Integer pid;

    private List<Tmenu> childs;//集合：封装子菜单

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<Tmenu> getChilds() {
        return childs;
    }

    public void setChilds(List<Tmenu> childs) {
        this.childs = childs;
    }
}
