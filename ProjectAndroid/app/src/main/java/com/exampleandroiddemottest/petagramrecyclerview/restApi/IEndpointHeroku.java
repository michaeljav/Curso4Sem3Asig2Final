package com.exampleandroiddemottest.petagramrecyclerview.restApi;


import com.exampleandroiddemottest.petagramrecyclerview.restApi.model.usuario_instagram;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IEndpointHeroku {

    //Encoded into url
    @FormUrlEncoded
    //what is method going to connect through?
    @POST(ContantesRestApi.HEROKUP_KEY_POST_ID_TOKEN)
    Call<usuario_instagram> registrarDatosDispositivo_Heroku(@Field("id_dispositivo") String id_dispositivo
            , @Field("id_usuario_instagram")  String id_usuario_instagram);
}
