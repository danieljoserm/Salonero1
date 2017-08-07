package main.salonero1.webservice;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import main.salonero1.R;

/**
 * Created by danie on 8/4/2017.
 */

public class DialogRegistrarse extends DialogFragment {
    private EditText Nombreusuarioregistro;





    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = getActivity().getLayoutInflater();


        builder.setView(inflater.inflate(R.layout.dialog_registrarse, null))



                .setPositiveButton(R.string.aceptarregistro, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
//               String NombreUsuario= Nombreusuarioregistro.getText().toString();
  //            Log.d("PRueba",NombreUsuario);
            }
        })


                .setNegativeButton(R.string.negarregistro, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int id) {
            // sign in the user ...
        }
    }).setTitle("Formulario de Registro");

      //  EditText  Nombreusuarioregistro =(EditText) getDialog().findViewById(R.id.edittextNombreusuarioregistro);



        return builder.create();
    }




}
