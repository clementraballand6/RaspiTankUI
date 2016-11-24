package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dev on 23/11/16.
 */
public class Http {

    public static String API_PATH;

    public static String req(String method, String path) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(Http.API_PATH + "/" + path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static boolean auth(String login, String password) throws Exception {
        URL url = new URL(Http.API_PATH + "/log");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Auth-login", login);
        conn.setRequestProperty("Auth-password", password);
        conn.connect();

        if(conn.getResponseCode() == 200){
            return true;
        }
        return false;
    }

    public static String get(String path) throws Exception {
        return Http.req("GET", path);
    }

    public static String post(String path) throws Exception {
        return Http.req("POST", path);
    }

}
