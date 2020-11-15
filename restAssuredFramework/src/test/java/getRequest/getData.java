package getRequest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.json.simple.JSONObject;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
public class getData {
	@Test
	public void testResponsecode() {
		
		Response response = get("https://reqres.in/api/users/2");
        System.out.println(response.asString());
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode());
        
        System.out.println("=======================================");
        
        
	}
	
	//Number 1
	@Test
	void test_02() {
		given().get("https://reqres.in/api/users/2")
		.then().statusCode(200)
		.body("data.email", equalTo("janet.weaver@reqres.in"));
	}
	@Test
	void post_login_successful() {
		Response response = get("https://reqres.in/api/login");
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
       
		
	}
	
	//Number2
	@Test
    void post() {
		RestAssured.baseURI ="https://reqres.in/api";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		request.header("Content-Type","application/json");
		requestParams.put("id", 1); 
		requestParams.put("name", "cerulean");
		requestParams.put("year", 2000);
		requestParams.put("color", "#98B2D1");
		requestParams.put("pantone_value", "15-4020");
		requestParams.put("email", "eve.holt@reqres.in");
		requestParams.put("password", "cityslicka");
		 request.body(requestParams.toJSONString());
		 Response response = request.post("https://reqres.in/api/login");

			String successCode = response.jsonPath().get("token");
            Assert.assertEquals(successCode,"QpwL5tke4Pnpja7X4");
		 int statusCode = response.getStatusCode();
		 System.out.println("The status code recieved: " + statusCode);
		 System.out.println("The token is "+successCode);
		 System.out.println("Response body: " + response.body().asString());
		}
	//Number 3
	@Test
	void delete() {

		 RestAssured.baseURI = "https://reqres.in/api/users/2";
		 RequestSpecification request = RestAssured.given(); 
		 
		 // Add a header stating the Request body is a JSON
		 request.header("Content-Type", "application/json"); 
		 
		       // Delete the request and check the response
		 Response response = request.delete("/delete/"); 
		 
		 int statusCode = response.getStatusCode();
		 System.out.println("The status code is "+statusCode);
		 System.out.println(response.asString());
		 Assert.assertEquals(statusCode, 204);
		 
	}
	//Number 5
	@Test
	
	void updateUsingPut() {
		
		int emptID=15050;
		RestAssured.baseURI ="https://reqres.in/api/users/2";
		 RequestSpecification request = RestAssured.given();
		 // Add a header stating the Request body is a JSON
		 request.header("Content-Type", "application/json"); 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("name", "Morpheus2"); // Cast
		 requestParams.put("job", "zion resident");
		 
		 request.body(requestParams.toJSONString());
		 Response response = request.put("/update/"+emptID);
		 
		 int statusCode = response.getStatusCode();
		
		 System.out.println(response.asString());
         System.out.println("The body is "+response.getBody());
		// System.out.println(response.getTime());
		 Assert.assertEquals(statusCode, 200); 
	
		 
	}
	
	//Number4
	@Test
	
void updateUsingPatch() {
		
		int emptID=15051;
		RestAssured.baseURI ="https://reqres.in/api/users/2";
		 RequestSpecification request = RestAssured.given();
		 // Add a header stating the Request body is a JSON
		 request.header("Content-Type", "application/json"); 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("name", "Morpheus2"); // Cast
		 requestParams.put("job", "zion resident");
		 
		 request.body(requestParams.toJSONString());
		 Response response = request.patch("/update/"+emptID);
		 
		 int statusCode = response.getStatusCode();
		
		 System.out.println(response.asString());
         System.out.println("The body is "+response.getBody());
		// System.out.println(response.getTime());
		 Assert.assertEquals(statusCode, 200); 
	
		 
	}
	
    //Number6
	@Test
	void singleUser() {
		RestAssured.baseURI ="https://reqres.in/api/users/2";
		 RequestSpecification request = RestAssured.given();
		 // Add a header stating the Request body is a JSON
		 request.header("Content-Type", "application/json"); 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("id", 2); // Cast
		 requestParams.put("email", "janet.weaver@reqres.in");
		 requestParams.put("first_name","Janet");
		 requestParams.put("last_name","Weaver"); 
		 requestParams.put( "avatar","https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg");
		 
		 request.body(requestParams.toJSONString());
		 Response response = request.post("/singleUser/");
		 
		 ResponseBody body = response.getBody();
		 
		 // Deserialize the Response body into SingleUser
		 SingleUser responseBody = body.as(SingleUser.class);
		 
		 // Use the RegistrationSuccessResponse class instance to Assert the values of 
		 // Response.
		 Assert.assertEquals("janet.weaver@reqres.in", responseBody.email);
		 
		 
		 Assert.assertEquals(2, responseBody.id);
		 
	}
	@Test
	void listUsers() {
	//Number6
	RestAssured.baseURI = "https://reqres.in/api/users?page=2";
	 RequestSpecification httpRequest = RestAssured.given();
	 Response response = httpRequest.get("");
	 
	 // First get the JsonPath object instance from the Response interface
	 JsonPath jsonPathEvaluator = response.jsonPath();
	 
	 
	List<String> allUsers = jsonPathEvaluator.getList("data.email");
	 
	 // Iterate over the list and print individual book item
	 for(String emails : allUsers)
	 {
	 System.out.println("Emails: " + emails);
	 }
	 
	 System.out.println("Now store all details in List class");
	 

	 List<ListUsers> allUsers2 = jsonPathEvaluator.getList("data", ListUsers.class);
     
		// Iterate over the list and print individual book item
		// Note that every book entry in the list will be complete Json object of book
		for(ListUsers userInfo : allUsers2)
		{
			System.out.println("Avatar: " + userInfo.avatar);
		}
	}
	 
	}


