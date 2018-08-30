package com.exampleandroiddemottest.petagramrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;

import java.util.ArrayList;

public class Card_View_Peg extends AppCompatActivity {


    RecyclerView recyclerViewSegundaPantalla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card__view__peg);


        recyclerViewSegundaPantalla = findViewById(R.id.rvPetDetalles);

        //Forma en mostrar  el en el recycler;
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewSegundaPantalla.setLayoutManager(llm);



    }
}
