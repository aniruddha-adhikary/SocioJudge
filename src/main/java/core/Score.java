package core;

import detectors.Detector;

import java.util.ArrayList;
import java.util.HashMap;

public class Score {
    private boolean nameOk;
    private boolean schoolOk;
    private boolean workplaceOk;
    private boolean hashtagOk;
    private boolean taklaOk;

    public Score() {
    }

    public Score(boolean nameOk, boolean schoolOk, boolean workplaceOk, boolean hashtagOk, boolean taklaOk) {
        this.nameOk = nameOk;
        this.schoolOk = schoolOk;
        this.workplaceOk = workplaceOk;
        this.hashtagOk = hashtagOk;
        this.taklaOk = taklaOk;
    }

    public boolean isNameOk() {
        return nameOk;
    }

    public void checkAndSetNameOk(Detector detector, String name) {
        this.nameOk = detector.checkString(name);
    }

    public boolean isSchoolOk() {
        return schoolOk;
    }

    public void checkAndSetSchoolOk(Detector detector, ArrayList<String> schoolNames) {
        this.schoolOk = true;
        for(String schoolName: schoolNames) {
            boolean verdictOk = detector.checkString(schoolName);
            if (!verdictOk) {
                this.schoolOk = false;
                break;
            }
        }
    }

    public boolean isWorkplaceOk() {
        return workplaceOk;
    }

    public void checkAndSetWorkplaceOk(Detector detector, ArrayList<String> workplaceNames) {
        this.workplaceOk = true;
        for(String workplaceName: workplaceNames) {
            boolean verdictOk = detector.checkString(workplaceName);
            if (!verdictOk) {
                this.workplaceOk = false;
                break;
            }
        }
    }

    public boolean isHashtagOk() {
        return hashtagOk;
    }

    public void checkAndSetHashtagOk(Detector detector, ArrayList<String> posts) {
        this.hashtagOk = true;
        for(String post: posts) {
            boolean verdictOk = detector.checkString(post);
            if (!verdictOk) {
                this.hashtagOk = false;
                break;
            }
        }
    }

    public boolean isTaklaOk() {
        return taklaOk;
    }

    public void checkAndSetTaklaOk(Detector detector, ArrayList<String> posts) {
        this.taklaOk = true;
        for(String post: posts) {
            boolean verdictOk = detector.checkString(post);
            if (!verdictOk) {
                this.taklaOk = false;
                break;
            }
        }
    }

    private String transformScore(Boolean score) {
        if(score)
            return "Passed!";
        else
            return "Failed!";
    }

    public HashMap<String, String> getScoreMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("nameOk", transformScore(this.nameOk));
        map.put("workOk", transformScore(this.workplaceOk));
        map.put("educationOk", transformScore(this.schoolOk));
        map.put("hashtagOk", transformScore(this.hashtagOk));
        map.put("taklaOk", transformScore(this.taklaOk));
        return map;
    }
}
