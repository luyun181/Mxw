package cn.huanyan.mxwvideo.ui.activity.login;

import cn.huanyan.mxwvideo.entity.EquipmentBean;

public class VerificationBean {
    private String phone;
    private String code;
    private String password;
    private String two_password;
    private EquipmentBean bean;

    public VerificationBean(String phone, String code, String password, String two_password, EquipmentBean bean) {
        this.phone = phone;
        this.code = code;
        this.password = password;
        this.two_password = two_password;
        this.bean = bean;
    }

    public VerificationBean(String phone, String code, String password, EquipmentBean bean) {
        this.phone = phone;
        this.code = code;
        this.password = password;
        this.bean = bean;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EquipmentBean getBean() {
        return bean;
    }

    public void setBean(EquipmentBean bean) {
        this.bean = bean;
    }


}
