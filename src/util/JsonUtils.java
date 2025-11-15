package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtils {
    // Get first "definition" value from API JSON response
    public static String extractFirstDefinition(String json) {
        if (json == null) return null;
        // regex to find "definition":"...". This is simple and works for typical dictionary API responses.
        Pattern p = Pattern.compile("\"definition\"\\s*:\\s*\"([^\"]+)\"");
        Matcher m = p.matcher(json);
        if (m.find()) {
            return unescapeJsonString(m.group(1));
        }
        return null;
    }

    // minimal unescape (handles common escapes)
    private static String unescapeJsonString(String s) {
        return s.replace("\\n", "\n").replace("\\\"", "\"").replace("\\/", "/").replace("\\'", "'").replace("\\t", "\t");
    }
}
