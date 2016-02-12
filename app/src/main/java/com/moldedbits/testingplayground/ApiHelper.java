package com.moldedbits.testingplayground;

import com.github.kevinsawicki.http.HttpRequest;

public class ApiHelper {

    public String getJsonFromServer() {
        HttpRequest request = HttpRequest.get("https://api.myjson.com/bins/4a2en");
        return request.body();
    }
}
