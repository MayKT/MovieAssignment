package com.example.loadingimageretrofit.mvp.presenter;

import android.util.Log;


import com.example.loadingimageretrofit.R;
import com.example.loadingimageretrofit.ServiceHelper;
import com.example.loadingimageretrofit.mvp.model.LoginRequestBody;
import com.example.loadingimageretrofit.mvp.model.ProfileInfoModel;
import com.example.loadingimageretrofit.mvp.model.ProfileInfoModelImpl;
import com.example.loadingimageretrofit.mvp.model.RequestTokenBody;
import com.example.loadingimageretrofit.mvp.view.LoginView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * Created by kaungkhantsoe on 2019-10-18.
 **/
public class LoginPresenterImpl extends BasePresenter implements LoginPresenter {

    private LoginView mView = null;

    private ServiceHelper.ApiService mService;
    private ProfileInfoModelImpl mProfileInfoModelImpl;

    public LoginPresenterImpl(ServiceHelper.ApiService mService){
        this.mService=mService;
        this.mProfileInfoModelImpl=new ProfileInfoModelImpl();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        this.mView = null;
    }

    @Override
    public void onUIReady() {
        this.mView.checkLogin();
    }

    @Override
    public void onAttachView(LoginView view) {
        this.mView = view;
    }

    @Override
    public void onClickLogin(String username, String password) {
        this.mView.showLoading();

        if (username.length() == 0) {
            this.mView.showDialogMsg(mView.context().getResources().getString(R.string.error), mView.context().getResources().getString(R.string.please_fill_in_username));
        } else if (password.length() == 0) {
            this.mView.showDialogMsg(mView.context().getResources().getString(R.string.error), mView.context().getResources().getString(R.string.please_fill_in_password));
        } else {

            getRequestToken(username, password);
        }
    }

    private void getRequestToken(final String username, final String password) {

        this.mProfileInfoModelImpl.getRequestTokenFromApi(mService).subscribe(new Observer<ProfileInfoModel>() {
            @Override
            public void onSubscribe(Disposable d) {

                addDisposableOberver(d);
            }

            @Override
            public void onNext(ProfileInfoModel profileInfoModel) {

                if (profileInfoModel != null) {
                    if (profileInfoModel.isFailure() || profileInfoModel.getRequest_token().length() == 0) {

                        mView.showDialogMsg(mView.context().getResources().getString(R.string.error),
                                mView.context().getResources().getString(R.string.error_retrieving_data));

                    }  else {

                        validateLogin(username,password,profileInfoModel.getRequest_token());
                    }
                } else {
                    mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                            mView.context().getResources().getString(R.string.please_check_your_internet_connection));
                }
            }

            @Override
            public void onError(Throwable e) {

                mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                        e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });

//        Disposable disposable = this.mInteractor.getRequestToken().subscribe(profileInfo -> {
//
//            if (profileInfo.getStatus_code() == 30) {
//                this.mView.showToastMsg(mView.context().getResources().getString(R.string.incorrect_user_name_or_password));
//            } else if (profileInfo.isFailure()) {
//                this.mView.showToastMsg(mView.context().getResources().getString(R.string.error_connecting));
//            } else {
//
//            }
//
//        });
//
//        addDisposableOberver(disposable);

    }

    private static final String TAG = "LoginPresenterImpl";

    public void validateLogin(String username, String password, final String requestToken) {

        Log.e(TAG, "validateLogin: " + username + " " + password + " " + requestToken );
        this.mProfileInfoModelImpl.getLoginValidteFromApi(mService,new LoginRequestBody(username,password,requestToken)).subscribe(new Observer<ProfileInfoModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposableOberver(d);
            }

            @Override
            public void onNext(ProfileInfoModel profileInfoModel) {

                if (profileInfoModel != null) {

                    if ( profileInfoModel.getStatus_code() == 30) {
                        mView.showDialogMsg(mView.context().getResources().getString(R.string.error),
                                mView.context().getResources().getString(R.string.incorrect_user_name_or_password));
                    } else  if (profileInfoModel.isFailure()) {
                        mView.showDialogMsg(mView.context().getResources().getString(R.string.error),
                                mView.context().getResources().getString(R.string.error_retrieving_data));
                    } else {
                        getSessionId(requestToken);
                    }

                } else {

                    mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                            mView.context().getResources().getString(R.string.please_check_your_internet_connection));
                }

            }

            @Override
            public void onError(Throwable e) {

                HttpException httpException = (HttpException) e;

                if(httpException.code() == 401) {
                    mView.showDialogMsg(mView.context().getResources().getString(R.string.error),
                            mView.context().getResources().getString(R.string.incorrect_user_name_or_password));
                } else {
                    mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                            e.getLocalizedMessage());
                }


            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getSessionId(String requestToken) {

        this.mProfileInfoModelImpl.getSessionByRequestTokenFromApi(mService,new RequestTokenBody(requestToken)).subscribe(new Observer<ProfileInfoModel>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposableOberver(d);
            }

            @Override
            public void onNext(ProfileInfoModel profileInfoModel) {
                if (profileInfoModel != null) {

                    if (profileInfoModel.isFailure()) {
                        mView.showDialogMsg(mView.context().getResources().getString(R.string.error),
                                mView.context().getResources().getString(R.string.error_retrieving_data));
                    } else {
                        mView.saveLoginData(profileInfoModel.getSession_id());
                    }

                }else {

                    mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                            mView.context().getResources().getString(R.string.please_check_your_internet_connection));
                }
            }

            @Override
            public void onError(Throwable e) {

                mView.showDialogMsg(mView.context().getResources().getString(R.string.error_connecting),
                        e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                mView.onLoginComplete();
                mView.showToastMsg("login success");
            }
        });
    }
}
