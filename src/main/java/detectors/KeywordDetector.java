package detectors;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class KeywordDetector extends SplitDetector {

    private ArrayList<String> badwords = new ArrayList<String>();
    private String wordListFileName;

    public KeywordDetector(String wordListFileName) {
        this.setWordListFileName(wordListFileName);
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.getWordListFileName());

        try {
            Scanner scanner = new Scanner(is);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                badwords.add(line);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWordListFileName() {
        return wordListFileName;
    }

    public void setWordListFileName(String wordListFileName) {
        this.wordListFileName = wordListFileName;
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
