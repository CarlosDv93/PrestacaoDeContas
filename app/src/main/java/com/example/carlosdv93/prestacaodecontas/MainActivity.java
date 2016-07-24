package com.example.carlosdv93.prestacaodecontas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosdv93.prestacaodecontas.bancoDeDados.BancoDeDados;

import java.io.Serializable;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtItem)
    TextView txtItem;
    @BindView(R.id.etxtItem)
    EditText etxtItem;
    @BindView(R.id.PrimeiroLinear)
    LinearLayout PrimeiroLinear;
    @BindView(R.id.txtValor)
    TextView txtValor;
    @BindView(R.id.etxtValor)
    EditText etxtValor;
    @BindView(R.id.SegundoLinear)
    LinearLayout SegundoLinear;
    @BindView(R.id.btnSalvar)
    Button btnSalvar;
    @BindView(R.id.btnVerificar)
    Button btnVerificar;
    @BindView(R.id.TerceiroLinear)
    LinearLayout TerceiroLinear;

    String item;
    float valor;
    private BancoDeDados db = new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSalvar)
    public void onClickSalva(View view) {

        item = etxtItem.getText().toString();
        valor = Float.valueOf(etxtValor.getText().toString());

        db.inserir(item, valor);
        Toast.makeText(MainActivity.this, "Item: " + item + "\nValor: " + String.format(Locale.getDefault(), "%.2f", valor), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnVerificar)
    public void onClickVerifica(View view) {

        Intent intent = new Intent(this, ListaItens.class);
        startActivity(intent);
    }

}

