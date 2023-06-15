package api.endpoints;
import org.testng.annotations.Test;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class UserEndpoints 
{
	public static Response createUser(UserPojo userData)
	{
		Response res=given()
			.contentType(ContentType.JSON)
			.accept("application/json")
			.body(userData)
		.when()
			.post(Routes.postUrl);
		return res;
	}
	public static Response getUser(String username)
	{
		Response res=given()
			.pathParam("username", username)
		.when()
			.get(Routes.getUrl);
		return res;
	}
	public static Response updateUser(UserPojo userData,String username)
	{
		Response res=given()
			.contentType(ContentType.JSON)
			.accept("application/json")
			.pathParam("username", username)
			.body(userData)
		.when()
			.put(Routes.putUrl);
		return res;
	}
	public static Response deleteUser(String username)
	{
		Response res=given()
			.pathParam("username", username)
		.when()
			.delete(Routes.deleteUrl);
		return res;
	}
	
}
