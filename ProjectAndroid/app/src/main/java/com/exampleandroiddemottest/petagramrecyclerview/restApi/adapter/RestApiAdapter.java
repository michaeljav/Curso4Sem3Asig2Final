package com.exampleandroiddemottest.petagramrecyclerview.restApi.adapter;

import com.exampleandroiddemottest.petagramrecyclerview.restApi.ContantesRestApi;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.IEndpointHeroku;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.IEndpointsApi;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.deserializador.MascotaDeserializador;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public IEndpointHeroku establecerConexionRestAPI_HEROKU(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ContantesRestApi.HEROKUP_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return   retrofit.create(IEndpointHeroku.class);
    }


    public IEndpointsApi establecerConexionRestApiInstagram(Gson gson){

        //aqui se convertiria la respuesta del api json con la calse mascota response, pero no la tiene todavia la forma


        //Esto detecta una clase que sea exactamente igual al modelo
// .addConverterFactory(GsonConverterFactory.create())
        //
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(ContantesRestApi.ROOT_URL)
                //al ponerle el objeto gson , no deserializar de forma general sino de forma especifica
                //a partir del objeto que le estamos pasando
                //.addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return  retrofit.create(IEndpointsApi.class);
    }


  public Gson construyeGsonDeserializadorMediaRecent(){
      GsonBuilder gsonBuilder = new GsonBuilder();
      //asociar todo lo que se deserialize se asocia anuestro objeto.
      gsonBuilder.registerTypeAdapter(MascotaResponse.class,new MascotaDeserializador());
      return  gsonBuilder.create();
  }

}
