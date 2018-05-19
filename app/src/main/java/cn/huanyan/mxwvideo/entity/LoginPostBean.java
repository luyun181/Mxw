package cn.huanyan.mxwvideo.entity;

public class LoginPostBean {
    private String phone;
    private String password;
    private EquipmentBean bean;

    public LoginPostBean(String phone, String password, EquipmentBean bean) {
        this.phone = phone;
        this.password = password;
        this.bean = bean;
    }

    public EquipmentBean getBean() {
        return bean;
    }

    public void setBean(EquipmentBean bean) {
        this.bean = bean;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
