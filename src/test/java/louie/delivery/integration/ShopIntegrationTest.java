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
class ShopIntegrationTest {

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
	void 특정_가게_상세보기() {
		given(documentationSpec)
			.urlEncodingEnabled(false)
			.accept(JSON)
			.contentType(JSON)
			.filter(document("get-shop-detail",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

			.when()
			.get("/shops/1")

			.then()
			.statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("id", equalTo(1))
			.body("name", equalTo("가게 1"))
			.body("itemCategories", hasSize(3))
			.body("itemCategories[0].name", equalTo("A 카테고리"))
			.body("itemCategories[0].items", hasSize(5))
			.body("itemCategories[0].items[0].id", equalTo(1))
			.body("itemCategories[0].items[0].name", equalTo("음식 1"))
			.body("itemCategories[0].items[0].price", equalTo(1000));
	}
}
