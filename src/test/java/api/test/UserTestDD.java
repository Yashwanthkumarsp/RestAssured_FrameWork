package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.Dataprovider;
import io.restassured.response.Response;

public class UserTestDD {
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = Dataprovider.class)
	public void testpostuserDD(String userID, String username, String firstName, String lastName, String email,
			String password, String phone) {
		User userpayload = new User();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(username);
		userpayload.setFirstName(firstName);
		userpayload.setLastName(lastName);
		userpayload.setEmail(email);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);

		Response res = UserEndPoints.createuser(userpayload);
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = Dataprovider.class)
	public void testgetuserDD(String username) {
		Response res = UserEndPoints.readuser(username);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 3, dataProvider = "UserNames", dataProviderClass = Dataprovider.class)
	public void testdeleteuserDD(String username) {
		Response res = UserEndPoints.deleteuser(username);
		Assert.assertEquals(res.getStatusCode(), 200);
	}
}
