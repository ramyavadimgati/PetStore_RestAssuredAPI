package api.endpoints;
import org.testng.annotations.Test;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Url_UserEndpoints 
{
	public static Response createUser(UserPojo userData)
		{
			String postUrl=Urlclass.getUrl().getString("postUrl");
			Response res=given()
				.contentType(ContentType.JSON)
				.accept("application/json")
				.body(userData)
			.when()
				.post(postUrl);
			return res;
		}
		public static Response getUser(String username)
		{
			String getUrl=Urlclass.getUrl().getString("getUrl");
			Response res=given()
				.pathParam("username", username)
			.when()
				.get(getUrl);
			return res;
		}
		public static Response updateUser(UserPojo userData,String username)
		{
			String putUrl=Urlclass.getUrl().getString("putUrl");
			Response res=given()
				.contentType(ContentType.JSON)
				.accept("application/json")
				.pathParam("username", username)
				.body(userData)
			.when()
				.put(putUrl);
			return res;
		}
		public static Response deleteUser(String username)
		{
			String deleteUrl=Urlclass.getUrl().getString("deleteUrl");
			Response res=given()
				.pathParam("username", username)
			.when()
				.delete(deleteUrl);
			return res;
		}
		
	}



