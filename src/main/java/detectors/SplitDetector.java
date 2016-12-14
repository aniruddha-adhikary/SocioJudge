package detectors;

public abstract class SplitDetector extends Detector {
    public String[] splitString(String string) {
        return string.split("\\s+");
    }
}
