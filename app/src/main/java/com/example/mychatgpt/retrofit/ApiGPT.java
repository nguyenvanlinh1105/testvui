package com.example.mychatgpt.retrofit;

import com.example.mychatgpt.model.messParamPost;
import com.example.mychatgpt.model.messResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiGPT {
    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer key "
    })
    @POST("")
    Observable<messResponse> postQues(
            @Body messParamPost paramPost
    );
}
