package packone;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestOne {

    int id;

    public RequestSpecification getRequestSpecification() {
        RequestSpecification requestSpecification = RestAssured.given()
                .contentType("application/json")
                .accept("application/json");
        return requestSpecification;
    }

    @Test(priority = 1)
    public void postResource() {

        System.out.println("In post resource method");

        RestAssured.baseURI = "http://dummy.restapiexample.com";

        JSONObject job = new JSONObject();
        job.put("name", "TestUser1");
        job.put("salary", "1000");
        job.put("age", "25");

        RequestSpecification postRequestSpecification = getRequestSpecification()
                .body(job.toJSONString());

        Response postResponse = postRequestSpecification.request(Method.POST, "/api/v1/create");

        int statusCode = postResponse.getStatusCode();
        System.out.println("Status code is :: " + statusCode);
        assertThat(statusCode).isEqualTo(200);

        String statusLine = postResponse.getStatusLine();
        System.out.println("Status Line is :: " + statusLine);
        assertThat(statusLine).isEqualTo("HTTP/1.1 200 OK");

        String postBody = postResponse.body().asString();

        System.out.println("Response body is :: " + postBody);

        assertThat(postBody.contains("TestUser1")).isTrue();

        id = postResponse.jsonPath().get("data.id");
        //System.out.println("Id is :: " + id);



    }

    @Test(priority = 2)
    public void getResource() {

        System.out.println("In get resource method");

        RestAssured.baseURI = "http://dummy.restapiexample.com";

        JSONObject job = new JSONObject();
        job.put("name", "TestUser2");
        job.put("salary", "1000");
        job.put("age", "25");

        Response response = getRequestSpecification()
                .body(job.toJSONString()).post("/api/v1/create");


        String postBody = response.body().asString();

        System.out.println("Response body is :: " + postBody);

        assertThat(postBody.contains("TestUser2")).isEqualTo(true);

        id = response.jsonPath().get("data.id");
        System.out.println("Post Id is :: " + id);


        //RequestSpecification getRequestSpecification = getRequestSpecification();
        Response getResponse = getRequestSpecification().get("/api/v1/employee/"+id);

        int statusCode = getResponse.getStatusCode();
        System.out.println("Get Status code is :: " + statusCode);
        assertThat(statusCode).isEqualTo(200);

        String statusLine = getResponse.getStatusLine();
        System.out.println("Status Line is :: " + statusLine);
        assertThat(statusLine).isEqualTo("HTTP/1.1 200 OK");

      /*  id = getResponse.jsonPath().get("id");
        System.out.println("Get Id is :: " + id);*/

        String getBody = getResponse.body().asString();

        System.out.println("Get Response body is :: " + getBody);

        assertThat(getBody.contains("TestUser2")).isTrue();


    }


    @Test(priority = 3)
    public void updateResource() {

        RestAssured.baseURI = "http://dummy.restapiexample.com";

        JSONObject job = new JSONObject();
        job.put("name", "TestUser1");
        job.put("age", "30");

        JSONObject putBody = new JSONObject();
        putBody.put("name", "TestUser_01");
        putBody.put("age", "30");

        Response response = getRequestSpecification()
                .body(job.toJSONString()).post("/api/v1/create");

        int statusCode = response.getStatusCode();
        System.out.println("Status code is :: " + statusCode);
        assertThat(statusCode).isEqualTo(200);

        String statusLine = response.getStatusLine();
        System.out.println("Status Line is :: " + statusLine);
        assertThat(statusLine).isEqualTo("HTTP/1.1 200 OK");

        String body = response.body().asString();

        //System.out.println("Response body is :: "+body);


        Response response2 = getRequestSpecification()
                .body(job.toJSONString()).put("/api/v1/update"+id);

        String body2 = response2.body().asString();

        assertThat(body2.contains("TestUser_01")).isTrue();


    }



    public void m1(){

    /*


     // Get resource details
        System.out.println("***** Get resource details ****");

       // RequestSpecification getRequestSpecification = getRequestSpecification();
        Response getResponse = postRequestSpecification.request(Method.GET, "/api/v1/employee/" + String.valueOf(id));

        int statusCode = getResponse.getStatusCode();
        System.out.println("Status code is :: "+statusCode);
        assertThat(statusCode).isEqualTo(200);

        String statusLine = getResponse.getStatusLine();
        System.out.println("Status Line is :: "+statusLine);
        assertThat(statusLine).isEqualTo("HTTP/1.1 200 OK");

    String getResponseBody = getResponse.body().asString();

        System.out.println("Response body is :: "+getResponseBody);

    assertThat(getResponseBody.contains("TestUser1")).

    isEqualTo(true);

    // Update resource details
        System.out.println("***** Update resource details ****");

    // RestAssured.baseURI = "http://dummy.restapiexample.com";

    JSONObject job1 = new JSONObject();
        job1.put("name","TestUser_01");
        job1.put("age","30");

    RequestSpecification updateRequestSpecification = getRequestSpecification()
            .body(job1.toJSONString());

    Response updateResponse = updateRequestSpecification.request(Method.PUT, "/api/v1/update/" + String.valueOf(id));

    int statusCode2 = updateResponse.getStatusCode();
        System.out.println("Status code is :: "+statusCode2);

    assertThat(statusCode2).

    isEqualTo(200);

    String statusLine2 = updateResponse.getStatusLine();
        System.out.println("Status Line is :: "+statusLine2);

    assertThat(statusLine2).

    isEqualTo("HTTP/1.1 200 OK");

    String updatedBody = updateResponse.body().asString();

        System.out.println("Response body is :: "+updatedBody);

    assertThat(updatedBody.contains("TestUser_01")).

    isEqualTo(true);

     */

}


}
