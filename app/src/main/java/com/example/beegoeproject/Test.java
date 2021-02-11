package com.example.beegoeproject;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Test extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{ \r\n    \"email\" : \"bossbaby947@gmail.com\", \r\n    \"password\" : \"254314\" \r\n    }");
        Request request = new Request.Builder()
                .url("http://lampang.cs.tu.ac.th:3030/signin")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            Response response = client.newCall(request).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
