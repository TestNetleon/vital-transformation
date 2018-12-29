package org.vitaltransformation.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import org.vitaltransformation.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class Requester {

    protected abstract Call<BaseResponse> getRetrofitCall();

    public abstract Context getContext();

    public abstract void onNetworkConnectionError();

    protected abstract void onApiRequestSucceed(BaseResponse response, int requestType);

    protected abstract void onApiRequestFailure(BaseResponse response, int requestType);

    protected void executeApiRequester(final int requestType) {
        if (!Utils.isNetworkAvailable(getContext())) {
            onNetworkConnectionError();
            return;
        }
//      String re = getRetrofitCall().requestJobDetails().body().toString();
        if (getRetrofitCall() != null)
            getRetrofitCall().enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(@NonNull Call<BaseResponse> call,
                                       @NonNull Response<BaseResponse> response) {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse != null && baseResponse.getStatus())
                            onApiRequestSucceed(baseResponse, requestType);
                        else onApiRequestFailure(baseResponse, requestType);
                    } else {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse == null) {
                            baseResponse = new BaseResponse();
                            baseResponse.setMessage(response.message());
                        }
                        if (TextUtils.isEmpty(baseResponse.getMessage()))
                            baseResponse.setMessage("Please check your network connection.");
                        onApiRequestFailure(baseResponse, requestType);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<BaseResponse> call, @NonNull Throwable t) {
                    BaseResponse baseResponse = new BaseResponse();
                    if (!TextUtils.isEmpty(t.getMessage()))
                        baseResponse.setMessage(t.getMessage());
                    else baseResponse.setMessage("Please check your network connection.");
                    onApiRequestFailure(baseResponse, requestType);
                }
            });
    }
}