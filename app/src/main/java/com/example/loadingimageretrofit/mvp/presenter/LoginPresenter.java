package com.example.loadingimageretrofit.mvp.presenter;


import com.example.loadingimageretrofit.mvp.view.LoginView;

/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public interface LoginPresenter {

    void onUIReady();
    void onAttachView(LoginView view);

    void onClickLogin(String username, String password);
}
