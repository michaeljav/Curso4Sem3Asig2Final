package com.exampleandroiddemottest.petagramrecyclerview;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.exampleandroiddemottest.petagramrecyclerview.adapter.PetsAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;

import java.util.ArrayList;

public class   llenarRecycler {

    public void inicializarAdactador(ArrayList<Mascota> pets, Activity activity, RecyclerView recyclerViewlistaPets){
        PetsAdapter adaptador = new PetsAdapter(pets,activity);
        recyclerViewlistaPets.setAdapter(adaptador);
    }

    public ArrayList<Mascota> inicializarListadePets(){


        ArrayList<Mascota>  pets = new ArrayList<Mascota>();

//        pets.add(new Mascota(R.drawable.uno,"Perro1",0));
//        pets.add(new Mascota(R.drawable.dos,"Perro2",0));
//        pets.add(new Mascota(R.drawable.tres,"Perro3",0));
//        pets.add(new Mascota(R.drawable.cuatro,"Perro4",0));
//        pets.add(new Mascota(R.drawable.cinco,"Perro5",0));
        return pets;
    }
}
