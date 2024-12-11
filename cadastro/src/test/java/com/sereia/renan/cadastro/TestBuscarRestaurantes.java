package com.sereia.renan.cadastro;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.keycloak.client.KeycloakTestClient;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.keycloak.util.TokenUtil;

import static io.restassured.RestAssured.given;

@DBRider
@QuarkusTest
@QuarkusTestResource(CadastroLifeCicleManager.class)
public class TestBuscarRestaurantes {

    KeycloakTestClient keycloakClient = new KeycloakTestClient();

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testBuscarRestaurantes() {
        String resultado = given()
                .when().get("/restaurantes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().asString();
//        Approvals.verifyJson(resultado);
    }

    private RequestSpecification given(){
        return RestAssured.given().contentType(ContentType.JSON);
    }

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testAlerarRestaurantes() {
        Restaurante dto = new Restaurante();
        dto.nome = "Renan";
        Long parameterValue = 123L;

        String resultado = given()
                .pathParam("id", parameterValue)
                .body(dto)
                .when().put("/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode())
                .extract().asString();

        Restaurante byId = Restaurante.findById(parameterValue);

//        Approvals.verifyJson(resultado);

        Assertions.assertEquals(dto.id, byId.id);
    }


    @Test
    public void testAdminAccess() {
        RestAssured.given().auth()
                .oauth2(getAccessToken("proprietario1"))
                .when().get("/restaurantes")
                .then()
                .statusCode(200);

//        RestAssured.given().auth().oauth2(getAccessToken("bob"))
//                .when().get("/api/admin")
//                .then()
//                .statusCode(403);
    }

    protected String getAccessToken(String userName) {
        return keycloakClient.getAccessToken("proprietario1",
                "teste",
                "front-web-cadastro");
    }

}
