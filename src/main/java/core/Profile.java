package core;

import detectors.Detector;
import detectors.HashtagDetector;
import detectors.KeywordDetector;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile {
    private String profileId;
    private String name;
    private ArrayList<String> schools = new ArrayList<>();
    private ArrayList<String> workplaces = new ArrayList<>();
    private ArrayList<String> posts = new ArrayList<>();

    public ArrayList<String> getPosts() {
        return posts;
    }

    public void addPost(String post) {
        this.posts.add(post);
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSchools() {
        return schools;
    }

    public void addSchool(String institution) {
        this.schools.add(institution);
    }

    public ArrayList<String> getWorkplaces() {
        return workplaces;
    }

    public void addWorkplace(String organization) {
        this.workplaces.add(organization);
    }

    public Score score() {
        Score score = new Score();

        Detector nameDetector = new KeywordDetector("names.txt");
        Detector schoolDetector = new KeywordDetector("school.txt");
        Detector workDetector = new KeywordDetector("work.txt");

        Detector taklaDetector = new KeywordDetector("takla.txt");
        Detector hashtagDetector = new HashtagDetector();

        score.checkAndSetNameOk(nameDetector, this.name);
        score.checkAndSetSchoolOk(schoolDetector, this.schools);
        score.checkAndSetWorkplaceOk(workDetector, this.workplaces);

        score.checkAndSetHashtagOk(hashtagDetector, this.posts);
        score.checkAndSetHashtagOk(taklaDetector, this.posts);

        return score;
    }

}
