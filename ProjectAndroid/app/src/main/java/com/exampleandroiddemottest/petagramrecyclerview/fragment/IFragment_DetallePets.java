package com.exampleandroiddemottest.petagramrecyclerview.fragment;

import com.exampleandroiddemottest.petagramrecyclerview.adapter.PetsAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public interface IFragment_DetallePets {

    public void generarLinearLayoutGrid();

    public void imagenCircular(CircularImageView civ);

    public PetsAdapter crearAdaptador(ArrayList<Mascota> pets);

    public void inicializarOSetAdapterAlRecyclerView(PetsAdapter petsAdapter);
}
