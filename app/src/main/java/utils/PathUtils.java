package utils;

public class PathUtils {

    private static final String P_PATH = "/img/";

    public static String getRealPath(String relativePath) {
        return P_PATH + relativePath;
    }
}
