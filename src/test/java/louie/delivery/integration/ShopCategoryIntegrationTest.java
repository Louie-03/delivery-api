package louie.delivery.integration;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith({RestDocumentationExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class ShopCategoryIntegrationTest {

	@LocalServerPort
	int port;

	RequestSpecification documentationSpec;

	@BeforeEach
	void setup(RestDocumentationContextProvider restDocumentation) {
		RestAssured.port = port;
		documentationSpec = new RequestSpecBuilder()
			.addFilter(documentationConfiguration(restDocumentation))
			.build();
	}

	@Test
	void 모든_카테고리_조회() {
		given(documentationSpec)
			.urlEncodingEnabled(false)
			.accept(JSON)
			.contentType(JSON)
			.filter(document("get-shopCategory-list",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

			.when()
			.get("/shop-categories")

			.then()
			.statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("", hasSize(13))
			.body("[0].id", equalTo(1))
			.body("[0].name", equalTo("1인분"))
			.body("[1].id", equalTo(2))
			.body("[1].name", equalTo("한식"))
			.body("[2].id", equalTo(3))
			.body("[2].name", equalTo("분식"));
	}
}
