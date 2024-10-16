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
            "Authorization: Bearer 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"
    })
    @POST("completions")
    Observable<messResponse> postQues(
            @Body messParamPost paramPost
    );
}
