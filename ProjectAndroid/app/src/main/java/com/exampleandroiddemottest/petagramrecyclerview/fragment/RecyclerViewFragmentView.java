package com.exampleandroiddemottest.petagramrecyclerview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exampleandroiddemottest.petagramrecyclerview.R;
import com.exampleandroiddemottest.petagramrecyclerview.adapter.PetsAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;
import com.exampleandroiddemottest.petagramrecyclerview.presentador.IRecyclerViewFragmentPresenter;
import com.exampleandroiddemottest.petagramrecyclerview.presentador.RecyclerViewFragmentPresentadorPet;

import java.util.ArrayList;

public class RecyclerViewFragmentView extends Fragment implements IRecyclerViewFragmentView {

    ArrayList<Mascota> mPets;
    private RecyclerView rvPets;
    private IRecyclerViewFragmentPresenter mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //    return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

            rvPets = v.findViewById(R.id.rvPets);

//para que se muestre el listado de perros
          mPresenter = new RecyclerViewFragmentPresentadorPet(this,getContext());

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        //Forma en mostrar  el en el recycler;
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout() {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rvPets.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PetsAdapter crearAdaptador(ArrayList<Mascota> pets) {
        PetsAdapter adaptador = new PetsAdapter(pets,getActivity());
        rvPets.setAdapter(adaptador);
        return adaptador;
    }

    @Override
    public void inicializarOSetAdapterAlRecyclerView(PetsAdapter petsAdapter) {
        rvPets.setAdapter(petsAdapter);

    }
}
