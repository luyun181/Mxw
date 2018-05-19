package cn.huanyan.mxwvideo.interf;


import cn.huanyan.mxwvideo.entity.BannerBean;
import cn.huanyan.mxwvideo.entity.FollowInfoBean;
import cn.huanyan.mxwvideo.entity.IndexBean;
import cn.huanyan.mxwvideo.entity.LoginBean;
import cn.huanyan.mxwvideo.entity.ModifyBean;
import cn.huanyan.mxwvideo.entity.NoticeBean;
import cn.huanyan.mxwvideo.entity.RecommendBean;
import cn.huanyan.mxwvideo.entity.CommonBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by admin on 2017/11/22.
 */

public interface ApiService {
    //首页
    @GET("api/notice")
    Observable<NoticeBean> getNoticeDetail();

    @GET("api/banner")
    Observable<BannerBean> getBannerDetail();

    @GET("api/index")
    Observable<IndexBean> getIndexDetail(@Query("page") int page,
                                         @Query("perPage") int pageSize);

    //推荐关注
    @GET("api/recomment")
    Observable<RecommendBean> getRecommend();

    //注册
    @POST("api/register")
    Observable<CommonBean> getRegisterResult(@Query("ihy") String content);

    //验证码
    @POST("api/sendSms")
    Observable<CommonBean> getCode(@Query("phone") String content);

    //登录
    @POST("api/login")
    Observable<LoginBean> getLogin(@Query("ihy") String content);

    //点击关注
    @GET("api/follow")
    Observable<CommonBean> getClickFollow(@Header("Authorization") String token,
                                          //10已关注 20未关注
                                          @Query("status") int status,
                                          @Query("user_id") String user_id);

    //我的关注
    @GET("api/myfollow")
    Observable<RecommendBean> getMyFollow(@Header("Authorization") String token,
                                          //10已关注 20未关注
                                          @Query("page") int page,
                                          @Query("perPage") int perPage);

    //关注详情
    @GET("api/videoInfo")
    Observable<FollowInfoBean> getVideoInfo(@Header("Authorization") String token,
                                            @Query("user_id") int user_id,
                                            @Query("page") int page,
                                            @Query("perPage") int perPage);

    //我的发布
    @GET("api/myRelease")
    Observable<IndexBean> getRelease(@Header("Authorization") String token,
                                     @Query("page") int page,
                                     @Query("perPage") int perPage);

    //用户名
    @POST("api/editUsername")
    Observable<ModifyBean> getName(@Header("Authorization") String token,
                                   @Query("ihy") String content);
}
