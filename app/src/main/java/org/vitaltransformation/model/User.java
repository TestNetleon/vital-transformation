package org.vitaltransformation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_login")
    @Expose
    private String userLogin;
    @SerializedName("display_name")
    @Expose
    private String displayName;

    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getDisplayName() {
        return displayName;
    }
}
