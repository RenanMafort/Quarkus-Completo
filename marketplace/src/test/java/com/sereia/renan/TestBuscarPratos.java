package com.sereia.renan;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TestBuscarPratos {

    @Test
    public void testBuscarPratos() {
        String resultado = given()
                .when().get("/pratos")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().asString();
        Approvals.verifyJson(resultado);
    }
}
