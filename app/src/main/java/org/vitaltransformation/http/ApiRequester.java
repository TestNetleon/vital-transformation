package org.vitaltransformation.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.Gson;
import org.vitaltransformation.R;
import org.vitaltransformation.model.User;
import org.vitaltransformation.utils.Constants;
import retrofit2.Call;

public class ApiRequester extends Requester {

    private Context mContext;
    private BaseApiRequesterListener mListener;
    private Call<BaseResponse> mCall;

    public ApiRequester(Context context, @NonNull BaseApiRequesterListener listener) {
        mContext = context;
        this.mListener = listener;
    }


    public void requestLogin(String email, String password) {
        mCall = ApiClient.getService().requestLogin(email, password);
        executeApiRequester(Constants.RequestType.LOGIN);
    }


    public void requestRegister(String name, String email, String mobile, String password) {
        mCall = ApiClient.getService().requestRegister(name, email, mobile, password);
        executeApiRequester(Constants.RequestType.REGISTER);
    }


   /*
    public void requestAddIssue(int issueNature, int subIssueNature, String otherIssueNature,
                                String name, String email, String mobile, String description, boolean isMember,
                                File file) {
        mCall = ApiClient.getService().addIssue(
                RequestBody.create(MediaType.parse("multipart/form-data"), "" + issueNature),
                RequestBody.create(MediaType.parse("multipart/form-data"), "" + subIssueNature),
                RequestBody.create(MediaType.parse("multipart/form-data"), otherIssueNature),
                RequestBody.create(MediaType.parse("multipart/form-data"), name),
                RequestBody.create(MediaType.parse("multipart/form-data"), email),
                RequestBody.create(MediaType.parse("multipart/form-data"), mobile),
                RequestBody.create(MediaType.parse("multipart/form-data"), description),
                RequestBody.create(MediaType.parse("multipart/form-data"), isMember ? "1" : "0"),
//                RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(workStageId)),
                file != null ? (MultipartBody.Part.createFormData("file", file.getName(),
                        (RequestBody.create(MediaType.parse("multipart/form-data"), file)))) : null);
        executeApiRequester(Constants.RequestType.ADD_ISSUE);
    }

    public void requestReplyIssue(String id, String reply, File file) {
        mCall = ApiClient.getService().replyIssue(
                RequestBody.create(MediaType.parse("multipart/form-data"), id),
                RequestBody.create(MediaType.parse("multipart/form-data"), reply),
                file != null ? (MultipartBody.Part.createFormData("file", file.getName(),
                        (RequestBody.create(MediaType.parse("multipart/form-data"), file)))) : null);
        executeApiRequester(Constants.RequestType.REPLY_ISSUE);
    }*/


    @Override
    protected Call<BaseResponse> getRetrofitCall() {
        return mCall;
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void onNetworkConnectionError() {
        if (mListener != null) mListener.onNetworkConnectionError();
    }

    @Override
    protected void onApiRequestSucceed(BaseResponse response, int requestType) {
        if (response.getStatus()) {

            Gson gson = new Gson();
            /*if (requestType == Constants.RequestType.DISTRICT) {
                Type listType = new TypeToken<ArrayList<Location>>() {
                }.getType();

                ArrayList<Location> locations = new Gson().fromJson(gson.toJson(response.getData()),
                        listType);
                    ((HomeApiRequestListener) mListener).onDistrictDataSucceed(getLocation(locations));
            }*/
            if (mListener != null && requestType == Constants.RequestType.LOGIN) {
                User user = new Gson().fromJson(gson.toJson(response.getData()), User.class);
                if (user != null)
                    ((LoginRequestListener) mListener).onLoginSuccess(user);
                else mListener.onRequestFailed(TextUtils.isEmpty(response.getMessage()) ?
                        mContext.getString(R.string.no_result) : response.getMessage());
            }
            if (mListener != null && requestType == Constants.RequestType.REGISTER) {
                User user = new Gson().fromJson(gson.toJson(response.getData()), User.class);
                if (user != null)
                    ((RegisterRequestListener) mListener).onRegisterSuccess(user);
                else mListener.onRequestFailed(TextUtils.isEmpty(response.getMessage()) ?
                        mContext.getString(R.string.no_result) : response.getMessage());
            }
        } else if (mListener != null)
            mListener.onRequestFailed(TextUtils.isEmpty(response.getMessage()) ?
                    mContext.getString(R.string.no_result) : response.getMessage());
    }

    @Override
    protected void onApiRequestFailure(BaseResponse response, int requestType) {
        if (mListener != null)
            mListener.onRequestFailed(TextUtils.isEmpty(response.getMessage()) ?
                    mContext.getString(R.string.no_result) : response.getMessage());
    }

    public interface BaseApiRequesterListener {
        void onRequestFailed(String message);

        void onNetworkConnectionError();
    }

    public interface LoginRequestListener extends BaseApiRequesterListener {
        void onLoginSuccess(User user);
    }

    public interface RegisterRequestListener extends BaseApiRequesterListener {
        void onRegisterSuccess(User user);
    }

}