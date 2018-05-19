package cn.huanyan.mxwvideo.entity;

public class LoginBean {


    /**
     * code : 200
     * data : {"userinfo":{"user_id":6,"user_name":"15135123082","headimg":"http://mxw.ihuanyan.cn/statics/templates/default/img/headimg.png","fans":"8","follows":"0","is_post":0,"gender":0,"signature":null,"birthday":null,"phone":"15135123082"},"token":"Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYxMTJjMDA4NjI3MDNmMjQ2YzExYjMwOGI4ZjM1ZTY3ZDEzOTJjZDMyOGE1ZWExYTk1YTRiOWExMWNlN2QyZmQ3OTllYzUyYmZlOWNlZjdlIn0.eyJhdWQiOiIzIiwianRpIjoiNjExMmMwMDg2MjcwM2YyNDZjMTFiMzA4YjhmMzVlNjdkMTM5MmNkMzI4YTVlYTFhOTVhNGI5YTExY2U3ZDJmZDc5OWVjNTJiZmU5Y2VmN2UiLCJpYXQiOjE1MjY3MDg3MjQsIm5iZiI6MTUyNjcwODcyNCwiZXhwIjoxNTU4MjQ0NzI0LCJzdWIiOiI2Iiwic2NvcGVzIjpbXX0.g8ksS_O2TXdso9doUXEcpOiADjVEZ2IWn8cvMUcnmjXLnjknvLCB1_v1ELu-Kv3drWOkMwcAu2TU2ILdqpkGkFg5flbSQCC8nZbufqWSsLcy96_OxTmjBB2qgxBZymx006MWBp4kSyBHlc10Atto5PAEiM9J3ZOVJ0WcNT_sPhkspQKFaVoQiHZHhzdZuSPrlB9ExDaP49WVDwgFpdgyio36CXfd9Yeu0QXppqvD9REIY4YHIxYyvMWnEz5xQ-spy74OexoMCpZJnbTiCjTbN_DVvwYrA_KY8rRi-RHNl7ROuNu5yMLEl_1M9tmXVCGX0pA74rdmUsl7UIgis2ZZjv-wS9RKLi8VhRo3eHo7U_37c4O1zlOHnj4ADqGRoT6HQt84P_VEsQDbhduUwJVh-lwZ_QPNAYWmw-kCFnMadz7B6EkieHAIB5cNwM0IVWIgYD7gw3Q9d2lqD7ude7zEGVddf_zZZHaq6JeZFRYH8ftQLP45f8ywzE8UWl99LGuSYbkbJDQWQQsvWulxZY2gi0zlt0yTJmBe6Ii9-lXiJPXJbEwQs9SKipwnE5ZkzXhDgAHA_TEm7LLSjMkIJQJ-1VZTNDY92u6mFVFLaapFR-5RnAj58U6GuiQD5bA1ptPMBsOTlcJzJ_bUJI9QaRRlVoR2d0BokRukXrNhtUwc6R0"}
     * msg : 登陆成功！
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * userinfo : {"user_id":6,"user_name":"15135123082","headimg":"http://mxw.ihuanyan.cn/statics/templates/default/img/headimg.png","fans":"8","follows":"0","is_post":0,"gender":0,"signature":null,"birthday":null,"phone":"15135123082"}
         * token : Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYxMTJjMDA4NjI3MDNmMjQ2YzExYjMwOGI4ZjM1ZTY3ZDEzOTJjZDMyOGE1ZWExYTk1YTRiOWExMWNlN2QyZmQ3OTllYzUyYmZlOWNlZjdlIn0.eyJhdWQiOiIzIiwianRpIjoiNjExMmMwMDg2MjcwM2YyNDZjMTFiMzA4YjhmMzVlNjdkMTM5MmNkMzI4YTVlYTFhOTVhNGI5YTExY2U3ZDJmZDc5OWVjNTJiZmU5Y2VmN2UiLCJpYXQiOjE1MjY3MDg3MjQsIm5iZiI6MTUyNjcwODcyNCwiZXhwIjoxNTU4MjQ0NzI0LCJzdWIiOiI2Iiwic2NvcGVzIjpbXX0.g8ksS_O2TXdso9doUXEcpOiADjVEZ2IWn8cvMUcnmjXLnjknvLCB1_v1ELu-Kv3drWOkMwcAu2TU2ILdqpkGkFg5flbSQCC8nZbufqWSsLcy96_OxTmjBB2qgxBZymx006MWBp4kSyBHlc10Atto5PAEiM9J3ZOVJ0WcNT_sPhkspQKFaVoQiHZHhzdZuSPrlB9ExDaP49WVDwgFpdgyio36CXfd9Yeu0QXppqvD9REIY4YHIxYyvMWnEz5xQ-spy74OexoMCpZJnbTiCjTbN_DVvwYrA_KY8rRi-RHNl7ROuNu5yMLEl_1M9tmXVCGX0pA74rdmUsl7UIgis2ZZjv-wS9RKLi8VhRo3eHo7U_37c4O1zlOHnj4ADqGRoT6HQt84P_VEsQDbhduUwJVh-lwZ_QPNAYWmw-kCFnMadz7B6EkieHAIB5cNwM0IVWIgYD7gw3Q9d2lqD7ude7zEGVddf_zZZHaq6JeZFRYH8ftQLP45f8ywzE8UWl99LGuSYbkbJDQWQQsvWulxZY2gi0zlt0yTJmBe6Ii9-lXiJPXJbEwQs9SKipwnE5ZkzXhDgAHA_TEm7LLSjMkIJQJ-1VZTNDY92u6mFVFLaapFR-5RnAj58U6GuiQD5bA1ptPMBsOTlcJzJ_bUJI9QaRRlVoR2d0BokRukXrNhtUwc6R0
         */

        private UserinfoBean userinfo;
        private String token;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserinfoBean {
            /**
             * user_id : 6
             * user_name : 15135123082
             * headimg : http://mxw.ihuanyan.cn/statics/templates/default/img/headimg.png
             * fans : 8
             * follows : 0
             * is_post : 0
             * gender : 0
             * signature : null
             * birthday : null
             * phone : 15135123082
             */

            private int user_id;
            private String user_name;
            private String headimg;
            private String fans;
            private String follows;
            private int is_post;
            private int gender;
            private String signature;
            private String birthday;
            private String phone;

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

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getFans() {
                return fans;
            }

            public void setFans(String fans) {
                this.fans = fans;
            }

            public String getFollows() {
                return follows;
            }

            public void setFollows(String follows) {
                this.follows = follows;
            }

            public int getIs_post() {
                return is_post;
            }

            public void setIs_post(int is_post) {
                this.is_post = is_post;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
