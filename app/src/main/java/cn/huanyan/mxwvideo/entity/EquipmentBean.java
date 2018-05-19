package cn.huanyan.mxwvideo.entity;

public class EquipmentBean {
    //设备型号
    private String d_model;
    //设备版本号
    private String d_platform;
    //0-安卓,
    //1-苹果
    private String iiod;
    //APP 版本号
    private String v_code;

    public EquipmentBean(String d_model, String d_platform, String iiod, String v_code) {
        this.d_model = d_model;
        this.d_platform = d_platform;
        this.iiod = iiod;
        this.v_code = v_code;
    }

    public String getD_model() {
        return d_model;
    }

    public void setD_model(String d_model) {
        this.d_model = d_model;
    }

    public String getD_platform() {
        return d_platform;
    }

    public void setD_platform(String d_platform) {
        this.d_platform = d_platform;
    }

    public String getIiod() {
        return iiod;
    }

    public void setIiod(String iiod) {
        this.iiod = iiod;
    }

    public String getV_code() {
        return v_code;
    }

    public void setV_code(String v_code) {
        this.v_code = v_code;
    }
}
