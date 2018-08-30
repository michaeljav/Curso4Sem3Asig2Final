package com.exampleandroiddemottest.petagramrecyclerview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.exampleandroiddemottest.petagramrecyclerview.restApi.IEndpointHeroku;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.adapter.RestApiAdapter;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.deserializador.MascotaDeserializador;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.model.MascotaResponse;
import com.exampleandroiddemottest.petagramrecyclerview.restApi.model.usuario_instagram;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateMenuOpctions extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.menu_opciones,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){

            case R.id.mAbout:
                 intent = new Intent(this,Aboutme.class);
                startActivity(intent);
//                Toast.makeText(getApplicationContext(),"abourt",Toast.LENGTH_SHORT).show();
                break;

            case  R.id.mContact:
                 intent = new Intent(this,Contact.class);
                startActivity(intent);
                //   Toast.makeText(getApplicationContext(),"contactos",Toast.LENGTH_SHORT).show();
                break;

            case R.id.mactionView:

                Toast.makeText(this,"Pulsaste un action view",Toast.LENGTH_SHORT).show();
                buscarToken();

               // Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_SHORT).show();
//                 intent = new Intent(getApplicationContext(),DetallePets.class);
//                startActivity(intent);

              //  Toast.makeText(getApplicationContext(),"Refresh",Toast.LENGTH_SHORT).show();
                break;

            case  R.id.mConfigurarCuenta:
                intent = new Intent(this,ConfigurarCuenta.class);
                startActivity(intent);
                //   Toast.makeText(getApplicationContext(),"contactos",Toast.LENGTH_SHORT).show();
                break;

            case  R.id.mRecibirNotificacion:
                buscarToken();
                   Toast.makeText(getApplicationContext(),"Recibir notificacion",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public final static String TAG = "PruebaFire";
    public void buscarToken(){
        // Get token
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        String idUsuarioIntagramFrom = MascotaDeserializador.idUsuarioInstagram;
                        enviarTokenRegistro(token, idUsuarioIntagramFrom);
                        //   enviarTokenRegistro(token);
                        // Log and toast
                        String msg = getResources().getString(R.string.msg_token_fmt)+": "+token;//  getString(R.string.msg_token_fmt, token);
                        // String msg =   getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private  void enviarTokenRegistro(String id_dispositivo,String id_usuario_instagram){
        Log.d("dispositivo: ",id_dispositivo);

        RestApiAdapter restApidAdapter = new RestApiAdapter();
        IEndpointHeroku endpoints = restApidAdapter.establecerConexionRestAPI_HEROKU();
        Call<usuario_instagram> usuarioResponseCall = endpoints.registrarDatosDispositivo_Heroku(id_dispositivo,id_usuario_instagram);

        usuarioResponseCall.enqueue(new Callback<usuario_instagram>() {
            @Override
            public void onResponse(Call<usuario_instagram> call, Response<usuario_instagram> response) {
                usuario_instagram usuarioResponse = response.body();
                Log.d("TOKEN_FIREBASE","getId: "+usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE","getId_dispositivo: "+usuarioResponse.getId_dispositivo());
                Log.d("TOKEN_FIREBASE","getId_usuario_instagram"+usuarioResponse.getId_usuario_instagram());

            }

            @Override
            public void onFailure(Call<usuario_instagram> call, Throwable t) {

            }
        });
    }


}
