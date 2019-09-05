package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String credentials = Credentials.basic("aConsumerKey", "aSecret");

    Button requestTokenButton;
    Button requestUserDetailsButton;
    EditText usernameEditText;
    TextView usernameTextView;

    TextView nameTextView;
    TextView locationTextView;
    TextView urlTextView;
    TextView descriptionTextView;

    TwitterApi twitterApi;
    OAuthToken token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestTokenButton = (Button) findViewById(R.id.request_token_button);
        requestUserDetailsButton = (Button) findViewById(R.id.request_user_details_button);
        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        usernameTextView = (TextView) findViewById(R.id.username_textview);

        nameTextView = (TextView) findViewById(R.id.name_textview);
        locationTextView = (TextView) findViewById(R.id.location_textview);
        urlTextView = (TextView) findViewById(R.id.url_textview);
        descriptionTextView = (TextView) findViewById(R.id.description_textview);

        //createTwitterApi();
    }

    private void createTwitterApi(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        token != null ? token.getAuthorization() : credentials);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();
    }
}
