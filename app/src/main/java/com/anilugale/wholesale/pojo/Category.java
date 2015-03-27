package com.anilugale.wholesale.pojo;

/**
 * Category Anil
 */
public class Category  implements java.io.Serializable {


    private int id;
    private String name;
    private Integer parentId;

    public Category() {
    }


    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(int id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }




}


