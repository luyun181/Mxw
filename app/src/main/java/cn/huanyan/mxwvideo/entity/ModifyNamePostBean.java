package cn.huanyan.mxwvideo.entity;

public class ModifyNamePostBean {
    private String user_name;
    private EquipmentBean bean;

    public ModifyNamePostBean(String user_name, EquipmentBean bean) {
        this.user_name = user_name;
        this.bean = bean;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public EquipmentBean getBean() {
        return bean;
    }

    public void setBean(EquipmentBean bean) {
        this.bean = bean;
    }
}
