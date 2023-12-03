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

public class test_Case03 {
    
    /*
     * Testcase01: Verify the functionality of Login button on the Home page
     */
    @Test (priority = 3, groups = {"API TESTING"})
    public void TestCase03(){
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
        String userId = jsonPath.getString("data.id");
        String token = jsonPath.getString("data.token");
        Assert.assertTrue(jsonPath.getBoolean("success"), "Failed to validate that login is successful");
        Assert.assertEquals(logResBody.contains("token"),true, "Failed to validate that token is returned in login");
        Assert.assertEquals(logResBody.contains("id"),true, "Failed to validate that userId is returned in login");
        Assert.assertEquals(logResBody.contains("success"),true, "Failed to validate that login is sucessful");


        RequestSpecification httpRequest2 = RestAssured.given();
        JSONObject reqParams3 = new JSONObject();
        reqParams3.put("userId", userId);
        reqParams3.put("name", "Tomer");
        reqParams3.put("date", "2024-12-12");
        reqParams3.put("person", "2");
        reqParams3.put("adventure", "2447910730");
        httpRequest2.header("Content-Type", "application/json");
        httpRequest2.body(reqParams3.toString());
        httpRequest2.header("Authorization", "Bearer " + token);
        Response reservationRes = httpRequest2.request(Method.POST, "/reservations/new");
        System.out.println("--->"+reservationRes.getBody().asString());
        int reservationResStatusCode = reservationRes.getStatusCode();
        System.out.println("---------->"+reservationResStatusCode);
        Assert.assertEquals(reservationResStatusCode, 200, "Failed to verfiy that the post reservation is successful");

        RequestSpecification httpRequest3 = RestAssured.given().queryParam("id", userId);
        httpRequest3.header("Authorization", "Bearer "+token);
        Response regResevRes = httpRequest3.request(Method.GET, "/reservations");

        ResponseBody<?> regResevResBody =  regResevRes.body();
        int getReservationsStatusCode = regResevRes.getStatusCode();
        Assert.assertEquals(getReservationsStatusCode, 200);
        String regResevResBodyString = regResevResBody.asString();
        System.out.println("->"+regResevResBodyString);
        JsonPath jsonPath2 = new JsonPath(regResevResBodyString);
        String adventureId = jsonPath2.getString("adventure");
        Assert.assertEquals(adventureId, "[2447910730]", "Failed to validate the adventureId");
        String name = jsonPath2.getString("name");
        Assert.assertEquals(name, "[Tomer]", "Failed to validate the adventureName");//since used name as email

    }
    
}
