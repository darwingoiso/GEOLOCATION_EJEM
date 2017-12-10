package com.example.aulab.permisossistema;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    Button mibutton,boton2;
    RelativeLayout miLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mibutton=findViewById(R.id.button);
        boton2 =findViewById(R.id.button2);
        miLayout=findViewById(R.id.milayout);
        mibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //CheckSelfPermission--verfifica si tiene permiso
                // RequestPermission -- solicitar un permiso
                //ShouldShowRequestpermissionRartionale()  -- saber si ya acepto los permisos anteriormente
                if(verificarpermisos())
                {
                    IniciarAplicacion();
                }
                else
                {
                    SolicitarPermisos();
                }




            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openCoordenadas=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(openCoordenadas);

            }
        });
    }

    private void SolicitarPermisos() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,CAMERA) ||
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,ACCESS_COARSE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,READ_EXTERNAL_STORAGE) ||
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,WRITE_EXTERNAL_STORAGE) )
        {
            //SOLO SE LLEGA AQUI CUANDO ANTES FUE RECHAZADO
            //NACKBAR MENSAJE  MAS BONITO E INTERACTIVO PARA HACER  UN BOTON Y SE VUELVA A DISPARAR LA PREGUNTA mensaje que sale en la parte inferior
            Snackbar.make(miLayout,"La Aplicación Necesita Algunos Permisos",Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{CAMERA,
                            ACCESS_COARSE_LOCATION,
                            ACCESS_FINE_LOCATION,
                            READ_EXTERNAL_STORAGE,
                            WRITE_EXTERNAL_STORAGE},
                            1);
                }
            }).show();


        }
        else
        {
            //cuando se abra por primera vez que se carga la aplicación
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{CAMERA,
                            ACCESS_COARSE_LOCATION,
                            ACCESS_FINE_LOCATION,
                            READ_EXTERNAL_STORAGE,
                            WRITE_EXTERNAL_STORAGE},
                    1);

        }
    }

    private boolean verificarpermisos() {
        //version en numeros del celular
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M)
        {
            return true;
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, CAMERA)== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this, WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }
        return false;
    }

    public void IniciarAplicacion(){
        Toast.makeText(getApplicationContext(),"Ya puedes Tomar fotos",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int code,String[] permisos,int[] resultados){
        switch (code){
            case  1:
                if (resultados.length>0  && resultados[0]==PackageManager.PERMISSION_GRANTED &&
                         resultados[1]==PackageManager.PERMISSION_GRANTED &&
                         resultados[2]==PackageManager.PERMISSION_GRANTED &&
                        resultados[3]==PackageManager.PERMISSION_GRANTED &&
                        resultados[4]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Fin del ciclo permisos otorgados",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Fin del ciclo permisos otorgados",Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }
}
