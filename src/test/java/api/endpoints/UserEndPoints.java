package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {

	static ResourceBundle geturl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");   //load properties file
		return routes;
	}
	
	// Create user
	public static Response createuser(User payload) {
		
		String posturl = geturl().getString("post_url");
		
		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				//.when().post(Routes.post_url);
				.when().post(posturl);

		return response;
	}

	// Read user
	public static Response readuser(String userName) {
		String readurl = geturl().getString("get_url");
		
		Response response = given().pathParam("username", userName)

				//.when().get(Routes.get_url);
				.when().get(readurl);

		return response;
	}

	// Update user
	public static Response updateuser(String userName, User payload) {
		String updateurl = geturl().getString("update_url");
		Response response = given().pathParam("username", userName).contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(payload)

				//.when().put(Routes.update_url);
				.when().put(updateurl);

		return response;
	}

	// Delete user
	public static Response deleteuser(String userName) {
		String deleteurl = geturl().getString("delete_url");
		Response response = given().pathParam("username", userName)

				//.when().delete(Routes.delete_url);
				.when().delete(deleteurl);

		return response;
	}
}
