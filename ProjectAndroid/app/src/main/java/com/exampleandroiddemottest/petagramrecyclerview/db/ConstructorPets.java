package com.exampleandroiddemottest.petagramrecyclerview.db;

import android.content.ContentValues;
import android.content.Context;

import com.exampleandroiddemottest.petagramrecyclerview.R;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collections;

public class ConstructorPets {


    private static final int LIKE =1;
    private Context context;

    public  ConstructorPets(Context context){
        this.context = context;
    }

    //list of pets
    public ArrayList<Mascota> obtenerDatos(){

    BaseDatos db = new BaseDatos(context);
    ArrayList<Mascota> mascotaArrayList = db.obtenerTodosPets();
     Mascota p = new Mascota();
    // Collections.sort(mascotaArrayList);
//primera vez creo en la base de datos los datos
    if (mascotaArrayList.size()== 0){
        insertarCincoPets(db);
        //y los extraigo extraigo los datos.
        mascotaArrayList = db.obtenerTodosPets();
    }




    return mascotaArrayList;
    }

    public void insertarCincoPets(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_NOMBRE,"Perro1");
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_FOTO,R.drawable.uno);

        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_NOMBRE,"Perro2");
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_FOTO,R.drawable.dos);

        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_NOMBRE,"Perro3");
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_FOTO,R.drawable.tres);

        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_NOMBRE,"Perro4");
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_FOTO, R.drawable.cuatro);

        db.insertarPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_NOMBRE,"Perro5");
        contentValues.put(ConstantesBaseDatos.TABLE_Pet_FOTO,R.drawable.cinco);

        db.insertarPet(contentValues);


    }


    public void darLikePets(Mascota petObj){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKESPET_ID_Pet,petObj.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKESPET_NUMERO_LIKES,LIKE);

        db.insertarLikePet(contentValues);



    }


    public  int obtenerLikesPet(Mascota petObj){
        BaseDatos db = new BaseDatos(context);
        return  db.obtenerLikesPets(petObj);
    }

}
