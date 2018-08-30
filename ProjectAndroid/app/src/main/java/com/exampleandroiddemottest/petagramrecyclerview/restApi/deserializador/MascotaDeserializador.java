package com.exampleandroiddemottest.petagramrecyclerview.restApi.deserializador;

import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.JsonKeys;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.model.MascotaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MascotaDeserializador  implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        //que asemeje  el jeson que trae con la calse
        //mascotaresponse
        MascotaResponse mascotaResponse=gson.fromJson(json,MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotas(deserializarMascotaDeJson(mascotaResponseData));

        return mascotaResponse;
    }


    //esta variable para poderla mandar al servicio web
    public static String idUsuarioInstagram="";

    private ArrayList<Mascota> deserializarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<Mascota>  mascotas = new ArrayList<>();

        for (int i = 0; i < mascotaResponseData.size(); i++){
            JsonObject  mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject  userJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String  id = userJson.get(JsonKeys.USER_ID).getAsString();
            idUsuarioInstagram = id;
            String  nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

           // JsonObject imageJSON =  mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonArray imageJSONObject  =  mascotaResponseDataObject.getAsJsonArray(JsonKeys.MEDIA_IMAGES_carousel);

                if (   imageJSONObject.size() > 1 ) {
                    for (int imag = 0; imag < imageJSONObject.size(); imag++) {

                        JsonObject  carousel_Object = imageJSONObject.get(imag).getAsJsonObject();

                        JsonObject imageJSON=  carousel_Object.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
                            JsonObject stdResolutionJson = imageJSON.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
                            String urlFoto = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

                            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
                            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

                            Mascota mascotaActual = new Mascota();
                            mascotaActual.setId(id);
                            mascotaActual.setNombreCompleto(nombreCompleto);
                            mascotaActual.setUrlFoto(urlFoto);
                            mascotas.add(mascotaActual);

                                    }
                                }else   {

                                        Mascota mascotaActual = new Mascota();
                                        mascotaActual.setId(id);
                                        mascotaActual.setNombreCompleto(nombreCompleto);
                                        mascotaActual.setUrlFoto("");
                                        mascotas.add(mascotaActual);
                                    }


        }
        return  mascotas;
    }
}
