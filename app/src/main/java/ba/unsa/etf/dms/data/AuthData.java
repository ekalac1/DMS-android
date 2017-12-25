package ba.unsa.etf.dms.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hugsby on 12/25/17.
 */

public class AuthData {


    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public AuthData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
