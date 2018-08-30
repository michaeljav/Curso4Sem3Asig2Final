package com.exampleandroiddemottest.petagramrecyclerview.fragment;

import com.exampleandroiddemottest.petagramrecyclerview.adapter.PetsAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public PetsAdapter crearAdaptador(ArrayList<Mascota> pets);

    public void inicializarOSetAdapterAlRecyclerView(PetsAdapter petsAdapter);
}
