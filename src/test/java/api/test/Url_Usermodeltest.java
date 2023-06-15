package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Url_UserEndpoints;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class Url_Usermodeltest 
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
		Response res = Url_UserEndpoints.createUser(userData);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("Succesfully creation of user");
	}
	
	@Test(priority=2)
	void testGetUser()
	{
		log.info("Getting created user details");
		Response res = Url_UserEndpoints.getUser(this.userData.getUsername());
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
		Response res = Url_UserEndpoints.updateUser(userData, this.userData.getUsername());
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
		Response res = Url_UserEndpoints.deleteUser(this.userData.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		log.info("Successfully user detail deleted");
	}
	
}
