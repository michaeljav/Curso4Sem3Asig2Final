package com.exampleandroiddemottest.petagramrecyclerview.presentador;



import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.exampleandroiddemottest.petagramrecyclerview.db.ConstructorPets;
import com.exampleandroiddemottest.petagramrecyclerview.fragment.IRecyclerViewFragmentView;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.IEndpointsApi;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.adapter.RestApiAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresentadorPet implements  IRecyclerViewFragmentPresenter {
    private Context mContext;
    private IRecyclerViewFragmentView mIRecyclerViewFragmentView;
   private ConstructorPets mConstructorPets;
   private ArrayList<Mascota> Arraylist_Pets;


    public RecyclerViewFragmentPresentadorPet(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        mContext = context;
        mIRecyclerViewFragmentView = iRecyclerViewFragmentView;
      //  obtenerPetsBaseDatos();
        obtenerMediosRecientes();
    }


    @Override
    public void obtenerPetsBaseDatos() {
        mConstructorPets = new ConstructorPets(mContext);
        Arraylist_Pets = mConstructorPets.obtenerDatos();
        mostrarPetsRecyclerView();


    }

    @Override
    public void obtenerMediosRecientes() {
        //esta ejecutando la llamada al servidor api
        //ejecutando la conexion al servidor.
        RestApiAdapter   restApiAdapter = new RestApiAdapter();
        //construir gson
      Gson gsonMediaRecent =restApiAdapter.construyeGsonDeserializadorMediaRecent();


        IEndpointsApi endPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
//ejecutando la llamada
       Call<MascotaResponse> mascotaResponseCall = endPointsApi.getRecentMedia();

//evento de callback
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                //trae datos en json
                //body es la data array list de objetos
                MascotaResponse  mascotaResponse = response.body();
               Arraylist_Pets = mascotaResponse.getMascotas();
               //mostrar mascotas en el recycler view
                mostrarPetsRecyclerView();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

                Toast.makeText(mContext,"Al go paso",Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXCION",t.toString());

            }
        });

    }

    @Override
    public void mostrarPetsRecyclerView() {

        mIRecyclerViewFragmentView.inicializarOSetAdapterAlRecyclerView(mIRecyclerViewFragmentView.crearAdaptador(Arraylist_Pets));
        mIRecyclerViewFragmentView.generarGridLayout();
    }
}
