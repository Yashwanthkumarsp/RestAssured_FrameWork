package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User UserPayload;
public Logger logger;
	@BeforeClass
	public void setupdata() {
		faker = new Faker();
		UserPayload = new User();
		UserPayload.setId(faker.idNumber().hashCode());
		UserPayload.setUsername(faker.name().username());
		UserPayload.setFirstName(faker.name().firstName());
		UserPayload.setLastName(faker.name().lastName());
		UserPayload.setEmail(faker.internet().safeEmailAddress());
		UserPayload.setPassword(faker.internet().password(5, 8));
		UserPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger=LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void postuser() {
		logger.info("**** Creating User ****");
		Response res = UserEndPoints.createuser(UserPayload);
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("**** User Created ****");
	}

	@Test(priority = 2)
	public void getuser() {
		logger.info("**** Reading User info ****");
		Response res = UserEndPoints.readuser(this.UserPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("**** User info is displayed ****");
	}

	@Test(priority = 3)
	public void updateuser() {
		logger.info("**** Updating User ****");
		UserPayload.setUsername(faker.name().username());
		UserPayload.setFirstName(faker.name().firstName());
		UserPayload.setLastName(faker.name().lastName());

		Response res = UserEndPoints.updateuser(this.UserPayload.getUsername(), UserPayload);
		Assert.assertEquals(res.getStatusCode(), 200);

		Response res1 = UserEndPoints.readuser(this.UserPayload.getUsername());
		res1.then().log().all();
		Assert.assertEquals(res1.getStatusCode(), 200);
		logger.info("**** User Updated ****");

	}

	@Test(priority = 4)
	public void deleteuser() {
		logger.info("**** Deleting User ****");
		Response res = UserEndPoints.deleteuser(this.UserPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("**** User Deleted ****");

	}

}
