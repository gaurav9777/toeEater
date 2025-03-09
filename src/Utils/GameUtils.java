package Utils;

public class GameUtils {
    public static boolean isStringEmptyAndBlank(String string) {
        if (string == null || string.isBlank()) {
            return true;
        }
        return false;
    }
}
