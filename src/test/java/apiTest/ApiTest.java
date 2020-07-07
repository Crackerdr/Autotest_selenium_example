package apiTest;


import org.json.JSONObject;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.logging.Logger;

public class ApiTest {

    final static Logger LOGGER = Logger.getLogger(ApiTest.class.getName());

@Test
public void headersRequest() throws IOException {
    LOGGER.info("headersRequest test: Start");
    JSONObject obj = HttpRequest.sentGetRequst(HttpRequest.HTTP_BIN_API_BASE,"headers");
    int code = HttpRequest.connectHttp(HttpRequest.HTTP_BIN_API_BASE,"headers").getResponseCode();
    LOGGER.info("GET \"https://httpbin.org/headers\"");
    JSONObject headers = obj.getJSONObject("headers");
    System.out.println(headers);
    LOGGER.info("\"Accept\": "+headers.get("Accept"));
//LOGGER.info("\"Accept-Encoding\": "+headers.get("Accept-Encoding"));
//    LOGGER.info("\"Accept-Language\": "+headers.get("Accept-Language"));
    LOGGER.info("\"Host\": "+headers.get("Host"));
    LOGGER.info("\"Referer\": "+ headers.get("Referer"));
//    LOGGER.info("\"Sec-Fetch-Dest\": "+headers.get("Sec-Fetch-Dest"));
//    LOGGER.info("\"Sec-Fetch-Mode\": "+headers.get("Sec-Fetch-Mode"));
//    LOGGER.info("Sec-Fetch-Site: )"+headers.get("Sec-Fetch-Site"));
    LOGGER.info("User-Agent: "+ headers.get("User-Agent") );
    LOGGER.info("X-Amzn-Trace-Id: " +headers.get("X-Amzn-Trace-Id") );
    Assert.assertEquals("Mozilla/5.0",headers.get("User-Agent"));
    Assert.assertEquals(200,code);
    LOGGER.info("headersRequest test: Finnish");
}
@Test
public void deleteRequest() throws IOException {
    LOGGER.info("deleteRequest test: Start");
    LOGGER.info("DELETE \"https://httpbin.org/status/200\"");
    int response = HttpRequest.deleteRequest(HttpRequest.HTTP_BIN_API_BASE,"status/200");
    LOGGER.info("Response: "+response);
    Assert.assertEquals(200,response);
    LOGGER.info("deleteRequest test: Finnish");
}
@Test
public void getErrorStatus() throws IOException {
    LOGGER.info("getErrorStatus test: Start");
    LOGGER.info("GET \"https://httpbin.org/status/400\"");
    int code = HttpRequest.connectHttp(HttpRequest.HTTP_BIN_API_BASE,"status/400").getResponseCode();
    Assert.assertEquals(400,code);
    LOGGER.info("Response:" + code);
    LOGGER.info("getErrorStatus test: Finnish");
}

@Test
public void postRequest() throws IOException {
    LOGGER.info("postRequest test: Start");
    LOGGER.info("PATCH \"https://httpbin.org/status/200\"");
    int code = HttpRequest.postRequest(HttpRequest.HTTP_BIN_API_BASE,"status/200");
    Assert.assertEquals(200,code);
    LOGGER.info("Response:" + code);
    LOGGER.info("postRequest test: Finnish");
}
    @Test
    public void putRequest() throws IOException {
        LOGGER.info("putRequest test: Start");
        LOGGER.info("PATCH \"https://httpbin.org/status/200\"");
        int code = HttpRequest.putRequest(HttpRequest.HTTP_BIN_API_BASE,"status/200");
        Assert.assertEquals(200,code);
        LOGGER.info("Response:" + code);
        LOGGER.info("putRequest test: Finnish");
    }

    @Test
    public void redirectGetRequest() throws IOException {
        LOGGER.info("putRequest test: Start");
        LOGGER.info("PATCH \"https://httpbin.org/absolute-redirect/2\"");
        int code = HttpRequest.putRequest(HttpRequest.HTTP_BIN_API_BASE,"redirect/2");
//        Assert.assertEquals(200,code);
        LOGGER.info("Response:" + code);
        LOGGER.info("putRequest test: Finnish");
    }

}
