package com.exampleandroiddemottest.petagramrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        Button btnEnviarContacto = findViewById(R.id.btnEnviarContacto);

        final EditText  editMensaje = findViewById(R.id.editMensaje);
        final EditText editNombre = findViewById(R.id.editNombre);
       final EditText editEmail   = findViewById(R.id.editEmail);
      //  final EditText editEmail   = null;

        btnEnviarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                "".equals(String.valueOf(editEmail.getText()));
                if (  "".equals(editEmail.getText().toString())
                        || "".equals(editMensaje.getText().toString())
                        || "".equals(editNombre.getText().toString())
                        ) {
                    Toast.makeText(getApplicationContext(), "Llene los campos por favor", Toast.LENGTH_SHORT).show();
                    return;
                }


                    SendEmail(editEmail.getText().toString(),editNombre.getText().toString(),editMensaje.getText().toString());
                    Toast.makeText(getApplicationContext(),"Envio Exitoso",Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void SendEmail(String to, String subject,String message){
        message = to +"\n "+message;
       to ="michaeljaviermota@gmail.com";
//        String subject = "Prueba";
//        String  message = "mensaje de pureba";
        //send email
        new SimpleMail().sendEmail(to, subject, message);

    }
}
