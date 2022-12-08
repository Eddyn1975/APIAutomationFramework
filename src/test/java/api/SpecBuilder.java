package api;

import java.io.IOException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.RestUtils;

public class SpecBuilder {
	public static RequestSpecification getRequestSpec() throws IOException {
		return new RequestSpecBuilder()
				.setBaseUri(RestUtils.getGlobalValue("baseURL"))
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL)
				.build();

	}
	public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder()
        		.expectHeader("Server", "Cowboy")
        		.expectHeader("Connection", "keep-alive")
        		.expectHeader("X-Powered-By", "Express")
        		.log(LogDetail.ALL)
        		.build();
   }
}