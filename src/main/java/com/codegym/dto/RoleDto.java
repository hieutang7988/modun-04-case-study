package com.codegym.dto;

public class RoleDto {

    private Long id;
    private String name;
    private String desc;

    public RoleDto () {
    }

    public RoleDto (String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public RoleDto (Long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
