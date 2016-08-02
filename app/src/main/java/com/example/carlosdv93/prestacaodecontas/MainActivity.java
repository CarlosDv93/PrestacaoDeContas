package com.example.carlosdv93.prestacaodecontas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosdv93.prestacaodecontas.bancoDeDados.BancoDeDados;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MenuAll{

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

    String item, valoraux;
    float valor;

    private BancoDeDados db = new BancoDeDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //etxtValor.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    public void criaAlerta(char tipo){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        if(tipo == 'i') {
            alertDialog.setTitle("Erro no Item");
            alertDialog.setMessage("Você deixou o campo ITEM em branco");
            alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    etxtItem.requestFocus();
                }
            });
        } else if (tipo == 'a') {
            alertDialog.setTitle("Erro ao Preencher");
            alertDialog.setMessage("Você deixou os 2 CAMPOS em branco");
            alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    etxtItem.requestFocus();
                }
            });
        } else if (tipo == 'v'){
            alertDialog.setTitle("Erro no Valor");
            alertDialog.setMessage("Você deixou o campo VALOR em branco");
            alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    etxtValor.requestFocus();
                }
            });
        }

        alertDialog.create();
        alertDialog.show();

    }

    @OnClick(R.id.btnSalvar)
    public void onClickSalva(View view) {
        item = etxtItem.getText().toString();
        valoraux = etxtValor.getText().toString();
        valoraux = valoraux.replace(",", ".");

        if (item.equals("")&& valoraux.equals("")) {
            criaAlerta('a');
        } else if (item.equals("") && !valoraux.equals("")){
            criaAlerta('i');
        } else if (!item.equals("")&& valoraux.equals("")) {
            criaAlerta('v');
        } else {
            valor = Float.valueOf(valoraux);
            db.inserir(item, valor);
            Toast.makeText(MainActivity.this, "Item: " + item + "\nValor: " + String.format(Locale.getDefault(), "%.2f", valor), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnVerificar)
    public void onClickVerifica(View view) {

        Intent intent = new Intent(this, ListaItens.class);
        startActivity(intent);
    }

}

