package apiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

public class HttpRequest {

    public final static String HTTP_BIN_API_BASE =  "https://httpbin.org/";

    public static JSONObject sentGetRequst(String base, String query) throws IOException {

        HttpURLConnection con = connectHttp(base,query);
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        con.setRequestProperty("Referer", "https://httpbin.org/");

        con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + con.getURL());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String

        JSONObject myResponse = new JSONObject(response.toString());
        return myResponse;
    }
    static String sendRequest(String base, String query, String inputString) throws IOException {
        HttpURLConnection con = connectHttp(base,query);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = inputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        } catch (IOException e){

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8));
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            throw new IOException("Ошибка отправки запроса: сервис вернул ошибку");
        } finally {
            System.out.println("Результаты отправки запроса: "+response.toString());
            con.disconnect();
        }
    }

    public static int deleteRequest(String base, String query) throws IOException {
        HttpURLConnection con = connectHttp(base,query);
        con.setDoOutput(true);
        con.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
        con.setRequestMethod("DELETE");
        con.connect();
        int responce = con.getResponseCode();
        return responce;
    }

    public static int postRequest(String base, String query) throws IOException {
        String urlParameters  = "param1=a&param2=b&param3=c";
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        HttpURLConnection con = connectHttp(base,query);
        con.setDoOutput(true);
        con.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
        con.setDoOutput( true );
        con.setInstanceFollowRedirects( false );
        con.setRequestMethod("POST");
        con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        con.setUseCaches( false );
        try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
            wr.write( postData );
        }

        con.connect();
        int responce = con.getResponseCode();
        return responce;
    }

    public static int putRequest(String base, String query) throws IOException {
        String urlParameters  = "param1=a&param2=b&param3=c";
        byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
        int    postDataLength = postData.length;
        HttpURLConnection con = connectHttp(base,query);
        con.setDoOutput(true);
        con.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
        con.setDoOutput( true );
        con.setInstanceFollowRedirects( false );
        con.setRequestMethod("PUT");
        con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        con.setUseCaches( false );
        try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
            wr.write( postData );
        }
        con.getInputStream();

        con.connect();
        int responce = con.getResponseCode();
        return responce;
    }

    public static JSONObject redirectGetRequest(String base, String query) throws IOException {

        HttpURLConnection con = connectHttp(base,query);
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        con.setRequestProperty("Referer", "https://httpbin.org/");

        con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + con.getURL());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print in String

        JSONObject myResponse = new JSONObject(response.toString());
        return myResponse;
    }

    public static HttpURLConnection connectHttp(String base, String query) throws IOException {
        URL url = new URL(base+query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        return con;
    }
}
