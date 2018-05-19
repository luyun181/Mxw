package cn.huanyan.mxwvideo.entity;

public class ModifyBirthPostBean {
    private String birthday;
    private EquipmentBean bean;

    public ModifyBirthPostBean(String birthday, EquipmentBean bean) {
        this.birthday = birthday;
        this.bean = bean;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public EquipmentBean getBean() {
        return bean;
    }

    public void setBean(EquipmentBean bean) {
        this.bean = bean;
    }
}
