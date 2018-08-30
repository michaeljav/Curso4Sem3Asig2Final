package com.exampleandroiddemottest.petagramrecyclerview.db;

public final class ConstantesBaseDatos {

    public static  final String DATABASE_NAME="mascota_db";
    public static  final int DATABASE_VERSION=1;

    public  static final String TABLE_Pet         ="mascota";
    public  static final String TABLE_Pet_ID       ="id";
    public  static final String TABLE_Pet_NOMBRE   ="nombre";
    public  static final String  TABLE_Pet_FOTO    ="foto";

    public static final String  TABLE_LIKESPET= "mascota_likes" ;
    public static final String TABLE_LIKESPET_ID = "id";
    public static final String TABLE_LIKESPET_ID_Pet = "id_mascota";
    public static final String TABLE_LIKESPET_NUMERO_LIKES = "numero_likes";


    public static String TABLE_Pet_CREADO_ESTRUCTURA="creada_estructura";
}
