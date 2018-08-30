package com.exampleandroiddemottest.petagramrecyclerview.restApi;

import com.exampleandroiddemottest.petagramrecyclerview.restApi.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IEndpointsApi {

    @GET(ContantesRestApi.URL_GET_INFORMATION_USER)
    Call<MascotaResponse> getRecentMedia();



}
