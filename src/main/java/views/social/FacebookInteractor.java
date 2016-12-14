package views.social;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URI;


public class FacebookInteractor {

    public static String getAccessToken(String code) {
        try {
            HttpResponse httpResponse = Unirest.post("https://graph.facebook.com/v2.6/device/login_status")
                    .field("access_token", "1251959248183083|470cb214983d9f26e46d0eb247b073ad")
                    .field("code", code)
                    .asJson();

            System.out.println(httpResponse.getBody().toString());

            JSONObject jsonObject = new JSONObject(httpResponse.getBody().toString());
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static String getCode() {
        try {
            HttpResponse httpResponse = Unirest.post("https://graph.facebook.com/v2.6/device/login")
                    .field("access_token", "1251959248183083|470cb214983d9f26e46d0eb247b073ad")
                    .field("scope", "public_profile,user_education_history,user_work_history,user_posts")
                    .asJson();

            JSONObject jsonObject = new JSONObject(httpResponse.getBody().toString());

            String verificationUri = jsonObject.getString("verification_uri");
            String code = jsonObject.getString("code");
            String userCode = jsonObject.getString("user_code");
            int interval = jsonObject.getInt("interval");
            int expiresIn = jsonObject.getInt("expires_in");

            StringSelection stringSelection = new StringSelection(userCode);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);

            try {
                Desktop.getDesktop().browse(new URI(verificationUri));
            } catch (Exception e) {}

            return code;

        } catch (UnirestException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
