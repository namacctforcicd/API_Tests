package Base;

import io.restassured.response.Response;

public class BDDStyleMethod {

 private static Response responding;
    int myage;
    public static Response getResponse(){

        Response response = responding;
        return response;
    }


}
