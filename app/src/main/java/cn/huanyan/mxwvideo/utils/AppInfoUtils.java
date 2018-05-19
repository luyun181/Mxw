package cn.huanyan.mxwvideo.utils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;

import cn.huanyan.mxwvideo.entity.EquipmentBean;

public class AppInfoUtils {
    private static EquipmentBean instance;

    public static EquipmentBean getInstance() {
        if (instance == null) {
            //设备参数
            String appVersionName = AppUtils.getAppVersionName();
            //Android版本
            String androidID = android.os.Build.VERSION.RELEASE;
            //设备型号
            String model = DeviceUtils.getModel();
            instance = new EquipmentBean(model, androidID, "0", appVersionName);
        }
        return instance;
    }
}
