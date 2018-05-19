package cn.huanyan.mxwvideo.entity;

import java.util.List;

public class BannerBean {

    /**
     * code : 200
     * data : [{"id":2,"img":"uploads/images/2018-05-14/OURLSCogS4jATUs1GmhyfwQwkF35tHVz6zFFGMKs.png"},{"id":1,"img":"uploads/images/2018-05-14/aP2XWYKrBrkiDgSlvOh4XdoeFcFvtz3jcveEwJGk.png"}]
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
         * id : 2
         * img : uploads/images/2018-05-14/OURLSCogS4jATUs1GmhyfwQwkF35tHVz6zFFGMKs.png
         */

        private int id;
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
