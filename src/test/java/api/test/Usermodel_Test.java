package api.test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.UserPojo;
public class Usermodel_Test 
{
	Faker f;
	public Logger log;
	public UserPojo userData;
	@BeforeClass
	public void setDataFields()
	{
		log=LogManager.getLogger(this.getClass());
		 f=new Faker();
		userData=new UserPojo();
		
		userData.setId(f.idNumber().hashCode());
		userData.setUsername(f.name().username());
		userData.setFirstName(f.name().firstName());
		userData.setLastName(f.name().lastName());
		userData.setEmail(f.internet().emailAddress());
		userData.setPassword(f.internet().password());
		userData.setPhone(f.phoneNumber().cellPhone());
		userData.setUserStatus(1);
	}
	@Test(priority=1)
	void testCreateUser()
	{
		log.info("This is user creation class");
		Response res = UserEndpoints.createUser(userData);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("Succesfully creation of user");
	}
	
	@Test(priority=2)
	void testGetUser()
	{
		log.info("Getting created user details");
		Response res = UserEndpoints.getUser(this.userData.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().all();
		log.info("User details displayed success");
		
	}
	@Test(priority=3)
	void testUpdatUser()
	{
		log.info("User details updation");
		userData.setUsername(f.name().username());
		userData.setFirstName(f.name().firstName());
		userData.setLastName(f.name().lastName());
		Response res = UserEndpoints.updateUser(userData, this.userData.getUsername());
		res.then().log().all();
		log.info("Successfully updated");
		Assert.assertEquals(res.getStatusCode(), 200);
		testGetUser();
		log.info("Again getting User details after updation");
		
	}
	@Test(priority=4)
	void testDeleteUser()
	{
		log.info("User details deletion");
		Response res = UserEndpoints.deleteUser(this.userData.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("Successfully user detail deleted");
	}
	
}
