package com.example.brusewu.newgan.data.source.remote;


import com.example.brusewu.newgan.data.model.BaseResponse;
import com.example.brusewu.newgan.data.model.Ganhuo;
import com.example.brusewu.newgan.data.model.XianDu;
import com.example.brusewu.newgan.data.model.XianDuCategory;
import com.example.brusewu.newgan.data.model.XianDuSubCategory;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankIOService {

    @GET("data/{category}/" + 20 + "/{page}")
    Observable<BaseResponse<Ganhuo>> getGanHuo(
            @Path("category") String category,@Path("page") int page
    );

    @GET("xiandu/categories")
    Observable<BaseResponse<XianDuCategory>> getXianDuCategories(

    );
    @GET("xiandu/category/{en_name}")
    Observable<BaseResponse<XianDuSubCategory>> getXianDuSubCategories(@Path("en_name") String enName);

    @GET("xiandu/data/id/{cid}/count" + 20 + "/page/{page}")
    Observable<BaseResponse<XianDu>> getXianDu(@Path("cid") String categoryId, @Path("page") int page);

    @GET("search/query/listview/category/all/count/" + 20 + "/page/{page}")
    Observable<BaseResponse<Ganhuo>> searchGanHuo(@Path("page") int page);
}
