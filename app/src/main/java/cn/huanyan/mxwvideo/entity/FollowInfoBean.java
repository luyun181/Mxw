package cn.huanyan.mxwvideo.entity;

import java.util.List;

public class FollowInfoBean {


    /**
     * code : 200
     * msg : 返回成功!
     * data : {"head":{"user_id":2,"user_name":"小路","fans":"0","headimg":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","signature":"就是要发！！！","follows":"0","gender":0,"status":10},"lists":{"current_page":1,"data":[{"user_id":2,"video_id":2,"title":"短视频顺利上线！！！","video_like":"0","video_commit":"0","thumb":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","headimg":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","user_name":"小路"}],"first_page_url":"http://mxw.ihuanyan.cn/api/videoInfo?page=1","from":1,"last_page":1,"last_page_url":"http://mxw.ihuanyan.cn/api/videoInfo?page=1","next_page_url":null,"path":"http://mxw.ihuanyan.cn/api/videoInfo","per_page":10,"prev_page_url":null,"to":1,"total":1}}
     */

    private int code;
    private String msg;
    private DataBeanX data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * head : {"user_id":2,"user_name":"小路","fans":"0","headimg":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","signature":"就是要发！！！","follows":"0","gender":0,"status":10}
         * lists : {"current_page":1,"data":[{"user_id":2,"video_id":2,"title":"短视频顺利上线！！！","video_like":"0","video_commit":"0","thumb":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","headimg":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","user_name":"小路"}],"first_page_url":"http://mxw.ihuanyan.cn/api/videoInfo?page=1","from":1,"last_page":1,"last_page_url":"http://mxw.ihuanyan.cn/api/videoInfo?page=1","next_page_url":null,"path":"http://mxw.ihuanyan.cn/api/videoInfo","per_page":10,"prev_page_url":null,"to":1,"total":1}
         */

        private HeadBean head;
        private ListsBean lists;

        public HeadBean getHead() {
            return head;
        }

        public void setHead(HeadBean head) {
            this.head = head;
        }

        public ListsBean getLists() {
            return lists;
        }

        public void setLists(ListsBean lists) {
            this.lists = lists;
        }

        public static class HeadBean {
            /**
             * user_id : 2
             * user_name : 小路
             * fans : 0
             * headimg : http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png
             * signature : 就是要发！！！
             * follows : 0
             * gender : 0
             * status : 10
             */

            private int user_id;
            private String user_name;
            private String fans;
            private String headimg;
            private String signature;
            private String follows;
            private int gender;
            private int status;

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

            public String getFollows() {
                return follows;
            }

            public void setFollows(String follows) {
                this.follows = follows;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class ListsBean {
            /**
             * current_page : 1
             * data : [{"user_id":2,"video_id":2,"title":"短视频顺利上线！！！","video_like":"0","video_commit":"0","thumb":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","headimg":"http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png","user_name":"小路"}]
             * first_page_url : http://mxw.ihuanyan.cn/api/videoInfo?page=1
             * from : 1
             * last_page : 1
             * last_page_url : http://mxw.ihuanyan.cn/api/videoInfo?page=1
             * next_page_url : null
             * path : http://mxw.ihuanyan.cn/api/videoInfo
             * per_page : 10
             * prev_page_url : null
             * to : 1
             * total : 1
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private Object next_page_url;
            private String path;
            private int per_page;
            private Object prev_page_url;
            private int to;
            private int total;
            private List<DataBean> data;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public String getFirst_page_url() {
                return first_page_url;
            }

            public void setFirst_page_url(String first_page_url) {
                this.first_page_url = first_page_url;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public String getLast_page_url() {
                return last_page_url;
            }

            public void setLast_page_url(String last_page_url) {
                this.last_page_url = last_page_url;
            }

            public Object getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(Object next_page_url) {
                this.next_page_url = next_page_url;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public Object getPrev_page_url() {
                return prev_page_url;
            }

            public void setPrev_page_url(Object prev_page_url) {
                this.prev_page_url = prev_page_url;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * user_id : 2
                 * video_id : 2
                 * title : 短视频顺利上线！！！
                 * video_like : 0
                 * video_commit : 0
                 * thumb : http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png
                 * headimg : http://mxw.ihuanyan.cn/uploads/images/2018-05-14/FIrDiEyfZho77iLVtomBV2tOVqyEEzKsoWJUhI6Z.png
                 * user_name : 小路
                 */

                private int user_id;
                private int video_id;
                private String title;
                private String video_like;
                private String video_commit;
                private String thumb;
                private String headimg;
                private String user_name;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getVideo_id() {
                    return video_id;
                }

                public void setVideo_id(int video_id) {
                    this.video_id = video_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getVideo_like() {
                    return video_like;
                }

                public void setVideo_like(String video_like) {
                    this.video_like = video_like;
                }

                public String getVideo_commit() {
                    return video_commit;
                }

                public void setVideo_commit(String video_commit) {
                    this.video_commit = video_commit;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }

                public String getHeadimg() {
                    return headimg;
                }

                public void setHeadimg(String headimg) {
                    this.headimg = headimg;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }
            }
        }
    }
}
