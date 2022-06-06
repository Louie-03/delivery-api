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

	@Test
	void 모든_가게_조회() {
		given(documentationSpec)
			.urlEncodingEnabled(false)
			.accept(JSON)
			.contentType(JSON)
			.filter(document("get-shop-list",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

			.when()
			.get("/shops")

			.then()
			.statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("", hasSize(6))
			.body("[0].id", equalTo(1))
			.body("[0].name", equalTo("가게 1"))
			.body("[1].id", equalTo(2))
			.body("[1].name", equalTo("가게 2"))
			.body("[2].id", equalTo(3))
			.body("[2].name", equalTo("가게 3"))
			.body("[3].id", equalTo(4))
			.body("[3].name", equalTo("가게 4"))
			.body("[4].id", equalTo(5))
			.body("[4].name", equalTo("가게 5"))
			.body("[5].id", equalTo(6))
			.body("[5].name", equalTo("가게 11"));
	}

	@Test
	void 가게_이름_검색() {
		given(documentationSpec)
			.urlEncodingEnabled(false)
			.accept(JSON)
			.contentType(JSON)
			.filter(document("get-search-shopName",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

			.when()
			.get("/shops?shopName=1")

			.then()
			.statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("", hasSize(2))
			.body("[0].id", equalTo(1))
			.body("[0].name", equalTo("가게 1"))
			.body("[1].id", equalTo(6))
			.body("[1].name", equalTo("가게 11"));
	}

	@Test
	void 특정_카테고리에_포함된_가게_검색() {
		given(documentationSpec)
			.urlEncodingEnabled(false)
			.accept(JSON)
			.contentType(JSON)
			.filter(document("get-search-contains-category",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

			.when()
			.get("/shops?shopCategoryId=1")

			.then()
			.statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("", hasSize(1))
			.body("[0].id", equalTo(1))
			.body("[0].name", equalTo("가게 1"));
	}

	@Test
	void 특정_카테고리에_포함된_가게_이름_검색() {
		given(documentationSpec)
			.urlEncodingEnabled(false)
			.accept(JSON)
			.contentType(JSON)
			.filter(document("get-search-shopName-and-contains-category",
				preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

			.when()
			.get("/shops?shopName=1&shopCategoryId=1")

			.then()
			.statusCode(HttpStatus.OK.value())
			.assertThat()
			.body("", hasSize(1))
			.body("[0].id", equalTo(1))
			.body("[0].name", equalTo("가게 1"));
	}
}
