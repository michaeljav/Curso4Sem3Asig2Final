package com.exampleandroiddemottest.petagramrecyclerview.restApi;

 public   final   class ContantesRestApi  {

//    https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    public final static String VERSION ="/v1/";
    public final static String ROOT_URL ="https://api.instagram.com" + VERSION;
    public final static String ACCESS_TOKEN ="8356404407.417ad30.8b820989e59a4463a45b7035e3e06bf2";
    public final static String KEY_ACCESS_TOKEN ="?access_token=";
    public final static String KEY_GET_INFORMATION_USER ="users/self/media/recent/";
    public final static String URL_GET_INFORMATION_USER =KEY_GET_INFORMATION_USER+KEY_ACCESS_TOKEN+ACCESS_TOKEN;


//     public static final String HEROKUP_ROOT_URL="https://secure-basin-36492.herokuapp.com/";
public static final String HEROKUP_ROOT_URL="https://guardarlikesfcm.herokuapp.com/";
     public static final String HEROKUP_KEY_POST_ID_TOKEN="registrar-usuario/";
}
