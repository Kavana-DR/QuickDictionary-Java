package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import exceptions.ApiException;

public class DictionaryApi {
    // returns raw JSON text from the public API
    public static String fetchRawJson(String word) throws ApiException {
    try {
        if (word == null || word.trim().isEmpty()) {
            throw new ApiException("Word cannot be empty.");
        }

        String urlStr = "https://api.dictionaryapi.dev/api/v2/entries/en/" 
                + java.net.URLEncoder.encode(word, "UTF-8");

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int code = conn.getResponseCode();

        // If the word doesn't exist → API returns 404 with an error JSON
        if (code == 404) {
            throw new ApiException("Word not found. Please try another word.");
        }

        // No internet or server down
        if (code != 200) {
            throw new ApiException("API error: " + code + ". Please check your connection.");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) sb.append(line);
        br.close();

        return sb.toString();

    } catch (ApiException ae) {
        throw ae;
    } catch (java.net.UnknownHostException e) {
        throw new ApiException("No internet connection. Please check your network.");
    } catch (Exception e) {
        throw new ApiException("Failed to fetch meaning: " + e.getMessage(), e);
    }
}
}