package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.UserPojo;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

public class Usermodel_datadriven 
{
	public UserPojo userData;
	Faker f;
	@Test(priority=1,dataProvider="Data",dataProviderClass=Dataprovider.class)
	void testCreateUser(String id,String user,String first,String last,String email,String password,String phone,String status)
	{
		int userid=Integer.parseInt(id);
		int user_status=Integer.parseInt(status);
		userData=new UserPojo();
		userData.setId(userid);
		userData.setUsername(user);
		userData.setFirstName(first);
		userData.setLastName(last);
		userData.setEmail(email);
		userData.setPassword(password);
		userData.setPhone(phone);
		userData.setUserStatus(user_status);
		Response res = UserEndpoints.createUser(userData);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=Dataprovider.class)
	void testGetUser(String username)
	{
		Response res = UserEndpoints.getUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
		res.then().log().all();
		
	}
//	@Test(priority=2,dataProvider="UserNames",dataProviderClass=Dataprovider.class)
	void testUpdatUser(String username)
	{
		f=new Faker();
		userData.setFirstName("Gotty");
		userData.setLastName("Gani");
		Response res = UserEndpoints.updateUser(userData,username);
		res = UserEndpoints.getUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
	//	System.out.println("Username: "+username+"first:"+userData.getFirstName()+"Last"+userData.getLastName());
		res.then().log().all();
	
	}
	@Test(priority=4,dataProvider="UserNames",dataProviderClass=Dataprovider.class)
	void testDeleteUser(String username)
	{
		
		Response res = UserEndpoints.deleteUser(username);
		Assert.assertEquals(res.getStatusCode(), 200);

	}
	

}
