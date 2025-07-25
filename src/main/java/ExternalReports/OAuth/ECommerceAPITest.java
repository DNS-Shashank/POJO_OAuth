package ExternalReports.OAuth;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.OrderDetail;
import POJO.orderRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ECommerceAPITest {

    public static void main(String[] args){

    //Login
    RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
    .setContentType(ContentType.JSON).build();

    LoginRequest loginRequest = new LoginRequest();

    loginRequest.setUserEmail("DNSS@gmail.com");
    loginRequest.setUserPassword("Password1!");

    RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);

    LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login")
    .then().log().all().extract().response().as(LoginResponse.class);

    String token = loginResponse.getToken();
    String userId = loginResponse.getUserId();


    //Add the product
    RequestSpecification addProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
    .addHeader("authorization", token).build();

    RequestSpecification reqAddProduct = given().spec(addProdBaseReq)
    .param("productName", "Smiley Sticker")
    .param("productAddedBy", userId)
    .param("productCategory", "fashion")
    .param("productSubCategory", "keychains")
    .param("productPrice", "150")
    .param("productDescription", "This is a keychain")
    .param("productFor", "women")
    .multiPart("productImage", new File("C://Users//ndurthu//Downloads//ima.png"));


    String resAddProduct = reqAddProduct.when().post("/api/ecom/product/add-product")
    .then().log().all().assertThat().statusCode(201).extract().response().asString();

    JsonPath js = new JsonPath(resAddProduct);
    String productID = js.get("productId");
    System.out.println();


    //Order the product

    RequestSpecification createOrderReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
    .setContentType(ContentType.JSON)
    .addHeader("authorization", token).build();

    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setCountry("India");
    orderDetail.setProductOrderedId(productID);

    List<OrderDetail> orderDetailList = new ArrayList<OrderDetail> ();
    orderDetailList.add(orderDetail);

    orderRequest orderRequest = new orderRequest();

    orderRequest.setOrders(orderDetailList);

    RequestSpecification reqOrder = given().spec(createOrderReq).body(orderRequest);

    String createOrderRes = reqOrder.when().post("/api/ecom/order/create-order")
    .then().assertThat().statusCode(201).extract().response().asString();

    System.out.println(createOrderRes);

    //Delete the product

    RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
    .setContentType(ContentType.JSON)
    .addHeader("authorization", token).build();
    
    RequestSpecification reqDeleteProduct = given().spec(deleteProdBaseReq).pathParam("productId", productID);

    String deleteProductResponse = reqDeleteProduct.when().delete("/api/ecom/product/delete-product/{productId}")
    .then().assertThat().statusCode(200).extract().response().asString();

    System.out.println(deleteProductResponse);
}
}
