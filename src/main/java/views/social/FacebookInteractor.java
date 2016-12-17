package views.social;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import core.Profile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.net.URI;


public class FacebookInteractor {

    public static String getAccessToken(String code) {
        try {
            HttpResponse httpResponse = Unirest.post("https://graph.facebook.com/v2.6/device/login_status")
                    .field("access_token", "1251959248183083|470cb214983d9f26e46d0eb247b073ad")
                    .field("code", code)
                    .asJson();

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

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(userCode), null);

            try {
                Desktop.getDesktop().browse(new URI(verificationUri));
            } catch (Exception e) {
            }

            return code;

        } catch (UnirestException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static Profile getProfile(String accessToken) {
        Profile profile = new Profile();

        try {
            HttpResponse httpResponse = Unirest.get("https://graph.facebook.com/v2.8/me")
                    .queryString("fields", "id,name,education,work")
                    .queryString("access_token", accessToken)
                    .asJson();

            JSONObject jsonObject = new JSONObject(httpResponse.getBody().toString());

            profile.setName(jsonObject.getString("name"));
            profile.setProfileId(jsonObject.getString("id"));

            JSONArray schools = jsonObject.getJSONArray("education");

            for (int i = 0; i < schools.length(); i++) {
                profile.addSchool(schools.getJSONObject(i).getJSONObject("school").getString("name"));
            }

            JSONArray works = jsonObject.getJSONArray("work");

            for (int i = 0; i < works.length(); i++) {
                profile.addWorkplace(works.getJSONObject(i).getJSONObject("employer").getString("name"));
            }

            HttpResponse httpResponse2 = Unirest.get("https://graph.facebook.com/v2.8/me/posts")
                    .queryString("access_token", accessToken)
                    .asJson();

            JSONObject jsonObject2 = new JSONObject(httpResponse2.getBody().toString());

            JSONArray posts = jsonObject2.getJSONArray("data");

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.getJSONObject(i);
                if (post.has("message")) {
                    profile.addPost(post.getString("message"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return profile;
    }

}
