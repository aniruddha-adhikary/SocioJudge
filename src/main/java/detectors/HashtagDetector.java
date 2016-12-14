package detectors;

public class HashtagDetector extends SplitDetector {

    final int MINIMUM_HASHTAG_ACCEPTANCE_COUNT = 4;
    final char HASHTAG_CHAR = '#';

    @Override
    public boolean checkString(String string) {
        string = string.toLowerCase();
        String[] splitted = splitString(string);

        int count = 0;

        for (String word : splitted) {

            if (!word.matches(".*\\d+.*") && word.toCharArray()[0] == HASHTAG_CHAR) {
                count++;
            }

        }

        return count < MINIMUM_HASHTAG_ACCEPTANCE_COUNT;

    }
}
