package com.lhs.weichat.bean;

/**
 * GroupClass
 *
 * @author longhuashen
 * @since 16/2/15
 */
public class GroupClass {

    public final static int TYPE_BIG_CLASS = 0;
    public final static int TYPE_SMALL_CLASS = 1;

    /**
     * id
     */
    private int id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类类型
     */
    private int type;

    /**
     * 所属大类
     */
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
