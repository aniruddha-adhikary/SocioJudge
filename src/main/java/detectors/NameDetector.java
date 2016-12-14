package detectors;

import java.util.ArrayList;

public class NameDetector extends SplitDetector {

    private ArrayList<String> badwords = new ArrayList<String>();

    NameDetector() {

    }

    @Override
    public boolean checkString(String string) {
        string = string.toLowerCase();
        String[] splitted = splitString(string);

        for (String badword : badwords) {
            for (String part : splitted) {
                if (badword.toLowerCase().equals(part.toLowerCase())) {
                    return false;
                }
            }
        }

        return true;
    }
}
