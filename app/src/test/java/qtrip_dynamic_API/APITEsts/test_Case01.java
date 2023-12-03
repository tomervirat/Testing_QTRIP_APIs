package qtrip_dynamic_API;

import java.util.UUID;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.restassured.http.Method;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class test_Case01 {
    
    /*
     * Testcase01: Verify that a new user can be registered and login using APIs of QTrip
     */
    @Test (priority = 1, groups = {"API TESTING"})
    public void TestCase01(){
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net/api/v1/";
        RequestSpecification httpRequest = RestAssured.given();
        String email = UUID.randomUUID().toString() + "_testUser@gmail.com";
        String password = "090909";
        JSONObject reqParams1 = new JSONObject();
        reqParams1.put("email", email);
        reqParams1.put("password", password);
        reqParams1.put("confirmpassword", password);
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(reqParams1.toString());
        Response regRes = httpRequest.request(Method.POST, "/register");
        int statusCode = regRes.getStatusCode();
        System.out.println("-->"+statusCode);
        Assert.assertEquals(statusCode, 201, "Unable to register a new user");

        RequestSpecification httpRequest1 = RestAssured.given();
        JSONObject reqParams2 = new JSONObject();
        reqParams2.put("email", email);
        reqParams2.put("password", password);
        httpRequest1.header("Content-Type", "application/json");
        httpRequest1.body(reqParams2.toString());
        Response logRes = httpRequest1.request(Method.POST, "/login");
        int statusCode1 = logRes.getStatusCode();
        String logResBody = logRes.getBody().asString();
        System.out.println("-->"+statusCode1);
        JsonPath jsonPath = new JsonPath(logRes.asString());
        Assert.assertTrue(jsonPath.getBoolean("success"), "Failed to validate that login is successful");
        Assert.assertEquals(logResBody.contains("token"),true, "Failed to validate that token is returned in login");
        Assert.assertEquals(logResBody.contains("id"),true, "Failed to validate that userId is returned in login");
        Assert.assertEquals(logResBody.contains("success"),true, "Failed to validate that login is sucessful");

    }
}