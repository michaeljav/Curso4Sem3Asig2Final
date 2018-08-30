package com.exampleandroiddemottest.petagramrecyclerview.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exampleandroiddemottest.petagramrecyclerview.R;
import com.exampleandroiddemottest.petagramrecyclerview.adapter.PetsAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;
import com.exampleandroiddemottest.petagramrecyclerview.presentador.IRecyclerViewFragmentPresenter;
import com.exampleandroiddemottest.petagramrecyclerview.presentador.RecyclerViewFragmentPresentadorPetDetalles;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_DetallePets extends Fragment implements  IFragment_DetallePets {

    ArrayList<Mascota> mPets;
    private RecyclerView rvPetsDetails;
    private IRecyclerViewFragmentPresenter mPresenter;

    public Fragment_DetallePets() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fragment__detalle_pets, container, false);

        /*Code to  show Circular imageview */
        CircularImageView circularImim = (CircularImageView)v.findViewById(R.id.circularImage);

        rvPetsDetails = v.findViewById(R.id.rvPetsDetailsFragment);

//para que se muestre el listado de perros
        mPresenter = new RecyclerViewFragmentPresentadorPetDetalles(this,getContext(),circularImim);
        return  v;
    }

    @Override
    public void generarLinearLayoutGrid() {
        //        //Forma en mostrar  el en el recycler;
        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);//
        rvPetsDetails.setLayoutManager(llm);
    }



    @Override
    public void imagenCircular(CircularImageView circularImageView)  {
//
//        /*Code to  show Circular imageview */
//        CircularImageView circularImageView = (CircularImageView)view.findViewById(R.id.circularImage);
// Set Border
        //circularImageView.setBorderColor(getResources().getColor(R.color.GrayLight));
        circularImageView.setBorderColor(getResources().getColor(R.color.colorAccent));
        circularImageView.setBorderWidth(10);
// Add Shadow with default param
        circularImageView.addShadow();
// or with custom param
        circularImageView.setShadowRadius(15);
        circularImageView.setShadowColor(Color.RED);
        circularImageView.setBackgroundColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);
        /*End code to show Circular image view*/
    }

    @Override
    public PetsAdapter crearAdaptador(ArrayList<Mascota> pets) {
        PetsAdapter adaptador = new PetsAdapter(pets,getActivity());
        rvPetsDetails.setAdapter(adaptador);
        return adaptador;
    }

    @Override
    public void inicializarOSetAdapterAlRecyclerView(PetsAdapter petsAdapter) {

        rvPetsDetails.setAdapter(petsAdapter);

    }
}
