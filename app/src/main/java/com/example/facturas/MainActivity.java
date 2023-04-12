package com.example.facturas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.widget.TextView;

import com.example.facturas.datos.Facturas;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Facturas>>{
    private static final String REQUEST_URL = "https://viewnextandroid.mocklab.io/facturas\n";
    private static final int FACTURAS_LOADER_ID=1;
    private TextView textViewEstado;
    private TextView textViewImporte;
    private TextView textViewFecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(FACTURAS_LOADER_ID, null, this);
    }


    @Override
    public Loader<List<Facturas>> onCreateLoader(int id, Bundle args) {
        return new FacturasLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished( Loader<List<Facturas>> loader, List<Facturas> facturas) {
        String fecha = "";
        String estado = "";
        String importe = "";

        for (int i = 0; i< facturas.size(); i++){
            fecha = fecha + facturas.get(i).fecha;
            estado = estado + facturas.get(i).estado;
            importe = importe +facturas.get(i).importe;
        }

        textViewEstado = (TextView) findViewById(R.id.textView1);
        textViewImporte = (TextView) findViewById(R.id.textView2);
        textViewFecha = (TextView) findViewById(R.id.textView3);

        textViewEstado.setText(estado);
        textViewImporte.setText(importe);
        textViewFecha.setText(fecha);
    }

    @Override
    public void onLoaderReset( Loader<List<Facturas>> loader) {

    }
}