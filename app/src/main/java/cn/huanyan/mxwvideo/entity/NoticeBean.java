package cn.huanyan.mxwvideo.entity;

import java.util.List;

public class NoticeBean {

    /**
     * code : 200
     * data : [{"id":2,"title":"短视频顺利上线！！！","content":"<p>短视频顺利上线！<\/p>"},{"id":1,"title":"短视频顺利上线！","content":"<p>短视频顺利上线！<\/p>"}]
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
         * title : 短视频顺利上线！！！
         * content : <p>短视频顺利上线！</p>
         */

        private int id;
        private String title;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
