package social;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URI;

public class FacebookInteractor {

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

            return "success";

        } catch (UnirestException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
