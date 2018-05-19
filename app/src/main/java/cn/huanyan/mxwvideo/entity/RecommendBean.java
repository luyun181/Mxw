package cn.huanyan.mxwvideo.entity;

import java.util.List;

public class RecommendBean {


    /**
     * code : 200
     * data : [{"user_id":6,"user_name":"15135123082","fans":"0","headimg":"http://mxw.ihuanyan.cn/","signature":null,"status":20,"img":[]},{"user_id":5,"user_name":"18534969396","fans":"0","headimg":"http://mxw.ihuanyan.cn/","signature":null,"status":20,"img":[]},{"user_id":2,"user_name":"小路","fans":"0","headimg":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","signature":"就是要发！！！","status":20,"img":["http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png"]},{"user_id":1,"user_name":"子君","fans":"0","headimg":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","signature":"就是要发！！！","status":20,"img":["http://mxw.ihuanyan.cn/uploads/images/2018-05-14/ntVPVgTwnwtG14RmkdDxh9hY1rqcjS1yccZCpan0.png","http://mxw.ihuanyan.cn/uploads/images/2018-05-14/ntVPVgTwnwtG14RmkdDxh9hY1rqcjS1yccZCpan0.png"]}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 6
         * user_name : 15135123082
         * fans : 0
         * headimg : http://mxw.ihuanyan.cn/
         * signature : null
         * status : 20
         * img : []
         */

        private int user_id;
        private String user_name;
        private String fans;
        private String headimg;
        private String signature;
        private int status;
        private List<String> img;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }
    }
}
