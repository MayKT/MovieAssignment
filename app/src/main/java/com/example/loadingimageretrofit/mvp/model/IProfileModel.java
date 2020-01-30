package com.example.loadingimageretrofit.mvp.model;



import com.example.loadingimageretrofit.ServiceHelper;

import io.reactivex.Observable;

/**
 * Created by kaungkhantsoe on 2019-10-21.
 **/
public interface IProfileModel {

    Observable<ProfileInfoModel> getRequestTokenFromApi(ServiceHelper.ApiService service);
    Observable<ProfileInfoModel> getLoginValidteFromApi(ServiceHelper.ApiService service, LoginRequestBody requestBody);
    Observable<ProfileInfoModel> getSessionByRequestTokenFromApi(ServiceHelper.ApiService service, RequestTokenBody requestTokenBody);
}
