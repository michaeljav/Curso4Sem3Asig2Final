package com.exampleandroiddemottest.petagramrecyclerview.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exampleandroiddemottest.petagramrecyclerview.DetallePets;
import com.exampleandroiddemottest.petagramrecyclerview.R;
import com.exampleandroiddemottest.petagramrecyclerview.db.ConstructorPets;
import com.exampleandroiddemottest.petagramrecyclerview.pojo.Mascota;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PetsAdapter  extends  RecyclerView.Adapter<PetsAdapter.PetsViewHolder>{


    ArrayList<Mascota> mMascotaArrayLi;
    Activity activity;

    public PetsAdapter(ArrayList<Mascota> mascotaArrayLi, Activity activity) {
        this.mMascotaArrayLi = mascotaArrayLi;
        this.activity = activity;
    }






    //Inflar el layout y lo pasara al viewholder para el obtenga los views
    @NonNull
    @Override
    public PetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflar o dar vida a nuestro layour cardview;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_readcontactos,parent,false);

        PetsViewHolder petsViewHolder = new PetsViewHolder(v);

        return petsViewHolder;
    }

    ////Asocia cada elemnto de  la lista con cada view
    ////
    //Este metodo se va invocando cada vez que se va recorriendo la lista de contactos, uno a uno items.
    //y va obteniendo la position of each element
    //asi va extrayendo los elementos de cada lista

    @Override
    public void onBindViewHolder(@NonNull final PetsViewHolder petsViewHolder, int position) {

            final Mascota mascota = mMascotaArrayLi.get(position);

           //   petsViewHolder.imgPhotoMain.setImageResource(mascota.getImageMain());
//esto es para poder mostrar la foto desde una url y asignarla a un imagenview
                Picasso.get()
                        .load(mascota.getUrlFoto())
                        .placeholder(R.drawable.dos)
                        .into(petsViewHolder.imgPhotoMain);

//              petsViewHolder.textBoneWhite.setText(mascota.getNamePets());
//              petsViewHolder.textBoneYellow.setText(String.valueOf(mascota.getLikes()));
               petsViewHolder.tvlikes.setText(String.valueOf(mascota.getLikes()));

                petsViewHolder.imgPhotoMain.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Toast.makeText(activity,"Diste like a ",Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(activity, DetallePets.class);
                      intent.putExtra("url",mascota.getUrlFoto());
                      intent.putExtra("like",mascota.getLikes());

                      activity.startActivity(intent);

                  }
              });


//              petsViewHolder.imgBoneWhite.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View view) {
//
//                     mascota.setLikes(mascota.getLikes()+1);
//                     petsViewHolder.textBoneYellow.setText(String.valueOf(mascota.getLikes()));
//
//                      Toast.makeText(activity,"Diste like a "+ mascota.getNamePets(),Toast.LENGTH_SHORT).show();
//                      ConstructorPets    constructorPets = new ConstructorPets(activity);
//                      constructorPets.darLikePets(mascota);
//                      petsViewHolder.textBoneYellow.setText(String.valueOf(constructorPets.obtenerLikesPet(mascota)));
//                    //  Toast.makeText(activity,String.valueOf(mascota.getLikes()),Toast.LENGTH_SHORT).show();
//                  }
//              });



    }

    @Override
    public int getItemCount() {
        return mMascotaArrayLi.size();
    }




    public static  class PetsViewHolder extends RecyclerView.ViewHolder  {

        private ImageView imgPhotoMain;
       // private  ImageView imgBoneWhite;
      //  private TextView textBoneWhite;
      //  private TextView  textBoneYellow;
        private TextView  tvlikes;


        public PetsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgPhotoMain = itemView.findViewById(R.id.imgPhotoMain);
        //    this.imgBoneWhite = itemView.findViewById(R.id.imgBoneWhite);
        //    this.textBoneWhite = itemView.findViewById(R.id.textBoneWhite);
            this.tvlikes = itemView.findViewById(R.id.tvlikes);

        }



    }

}
