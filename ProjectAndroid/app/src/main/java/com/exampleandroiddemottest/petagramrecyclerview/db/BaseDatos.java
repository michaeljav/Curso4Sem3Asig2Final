package com.exampleandroiddemottest.petagramrecyclerview.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context mContext;
    BaseDatos(Context context) {
        super(context,ConstantesBaseDatos.DATABASE_NAME,null,ConstantesBaseDatos.DATABASE_VERSION);
        this.mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaPets = "CREATE TABLE " + ConstantesBaseDatos.TABLE_Pet+"("+
                                        ConstantesBaseDatos.TABLE_Pet_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                        ConstantesBaseDatos.TABLE_Pet_NOMBRE +" TEXT,"+
                                        ConstantesBaseDatos.TABLE_Pet_FOTO+" INTEGER"+
                                        ")";

        String  queryCrearTableLikePets = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKESPET +" ("+
                                                ConstantesBaseDatos.TABLE_LIKESPET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                ConstantesBaseDatos.TABLE_LIKESPET_ID_Pet+ " INTEGER,"+
                                                ConstantesBaseDatos.TABLE_LIKESPET_NUMERO_LIKES+ " INTEGER,"+
                                                "FOREIGN KEY ("+ConstantesBaseDatos.TABLE_LIKESPET_ID_Pet +" ) "+
                                                "REFERENCES "+ ConstantesBaseDatos.TABLE_Pet +"("+ConstantesBaseDatos.TABLE_Pet_ID+" )"+
                                                ")";

        //no va atraer valor de respuesta
        sqLiteDatabase.execSQL(queryCrearTablaPets);
        sqLiteDatabase.execSQL(queryCrearTableLikePets);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_Pet);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKESPET);
        //call the method
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Mascota> obtenerTodosPets(){

        ArrayList<Mascota> mascotaArrayList = new ArrayList<>();

        String query = "SELECT * FROM " +ConstantesBaseDatos.TABLE_Pet ;
        SQLiteDatabase db = this.getWritableDatabase();
        //va a traer un valor de respuesta
        Cursor registros= db.rawQuery(query,null);

        while (registros.moveToNext()){
//            Mascota petActual  = new Mascota();
//            petActual.setId(registros.getInt(0));
//            petActual.setNamePets(registros.getString(1));
//            petActual.setImageMain(registros.getInt(2));
//
//
//            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKESPET_NUMERO_LIKES+") as likes "+
//                    " FROM "+ ConstantesBaseDatos.TABLE_LIKESPET+
//                    " WHERE " +  ConstantesBaseDatos.TABLE_LIKESPET_ID_Pet +"="+ petActual.getId();
//
//
//            Cursor registrosLikes= db.rawQuery(queryLikes,null);
//            if (registrosLikes.moveToNext()){
//                petActual.setLikes(registrosLikes.getInt(0));
//            }else {
//                petActual.setLikes(0);
//            }
//            mascotaArrayList.add(petActual);

        }
        db.close();
        return mascotaArrayList;

    }

    public void insertarPet(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_Pet,null,contentValues);
        db.close();

    }

    public void insertarLikePet(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKESPET,null,contentValues);
        db.close();


    }


    public int obtenerLikesPets(Mascota petObjs){

        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKESPET_NUMERO_LIKES+")"+
                " FROM "+ ConstantesBaseDatos.TABLE_LIKESPET +
                " WHERE " +ConstantesBaseDatos.TABLE_LIKESPET_ID_Pet + "="+petObjs.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query,null);
        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }
}
