package qtrip_dynamic_API;

import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.restassured.http.Method;
import org.json.JSONObject;
import java.io.File;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class test_Case02 {

    /*
     * Testcase01: Verify the functionality of Login button on the Home page
     */
    @Test (priority = 2, groups = {"API TESTING"})
    public void TestCase02() {
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net/api/v1/";
        RequestSpecification httpRequest = RestAssured.given().queryParam("q", "beng");
        Response response = httpRequest.request(Method.GET, "/cities");
        ResponseBody body = response.body();
        int searchCityStatusCode = response.getStatusCode();
        Assert.assertEquals(searchCityStatusCode, 200, "Failed to validte that the search city resopnse status code is 200");
        String resBody = body.asString();
        JsonPath jpath = new JsonPath(resBody);
        String description = jpath.getString("description");
        Assert.assertEquals(resBody.contains("description"), true, "Failed to validate that the description field is present in the search response");
        Assert.assertEquals(description, "[100+ Places]", "Failed to validate the contents of search response description failed");
        int searchResSize = jpath.getInt("size()");
        Assert.assertEquals(searchResSize, 1, "Failed to validate that the search size response is 1");
        String schemaPath = "./src/test/resources/jsonSchema.json";
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }

}