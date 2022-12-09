package Base;

import POJO.LoginRequest;
import POJO.LoginResponse;
import POJO.OrderDetails;
import POJO.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ECommerceAPITest {

    public static void main(String[] args) {

        RequestSpecification req =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        LoginRequest lr = new LoginRequest();
        lr.setUserEmail("nnamdiwill@gmail.com");
        lr.setUserPassword("rshettypw4tst!!");
       RequestSpecification reqLogin =  given().relaxedHTTPSValidation().log().all().spec(req).body(lr);

      LoginResponse response =  reqLogin.when().post("/api/ecom/auth/login")
                .then().log().all().extract().response().as(LoginResponse.class);

      String token = response.getToken();
      String user = response.getUserId();
        System.out.println(response.getToken());
        System.out.println(response.getUserId());

        //Add Product
        RequestSpecification addProductreq =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token)
                .build();

        RequestSpecification addProducts =  given().log().all().spec(addProductreq).param("productName","qwerty")
                .param("productAddedBy",user)
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Addias Originals")
                .param("productFor","women")
                .multiPart("productImage",new File("/Users/nnamdiwilliams/Downloads/zebra.png"));

        String addProductResponse = addProducts.when().log().all().post("/api/ecom/product/add-product")
                .then().extract().response().asString();

        JsonPath jp = new JsonPath(addProductResponse);
       String productId =   jp.get("productId");
        System.out.println("The product id is " + productId);

        //Create Order
        RequestSpecification createOrderReq =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).setContentType(ContentType.JSON)
                .build();

        OrderDetails orderdetail = new OrderDetails();
        orderdetail.setCountry("United States");
        orderdetail.setProductOrderedId(productId);
        Orders order = new Orders();
        List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
        orderDetailList.add(orderdetail);
        order.setOrders(orderDetailList);
     RequestSpecification orderSpecs =    given().log().all().spec(createOrderReq).body(order);
  String orderResponse =   orderSpecs.when().post("/api/ecom/order/create-order")
             .then().log().all().extract().response().asString();

  //Delete Call
        RequestSpecification deleteOrderReq =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).setContentType(ContentType.JSON)
                .build();
        RequestSpecification deleteReq=    given().log().all().spec(deleteOrderReq).pathParam("productId",productId);
        String deleteResponse = deleteReq.when().log().all().delete("/api/ecom/product/delete-product/{productId}")
                .then().log().all().extract().response().asString();

        JsonPath jp1 = new JsonPath(deleteResponse);
        String deleteMessage =   jp1.get("message");
        Assert.assertEquals("Product Deleted Successfully",deleteMessage);

    }
}
