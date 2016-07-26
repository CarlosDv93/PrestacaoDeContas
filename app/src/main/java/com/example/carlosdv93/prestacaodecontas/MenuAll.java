package com.example.carlosdv93.prestacaodecontas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CarlosDv93 on 25/07/2016.
 */
public class MenuAll extends AppCompatActivity {

    @BindView(R.id.etxtValorGastar)
    EditText etxtValorGastar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_configuracao:
                criaDialogMenu('c');
                break;
            case R.id.menu_sobre:
                criaDialogMenu('s');
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private void criaDialogMenu(char tipo) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        if (tipo == 'c') {
            alertDialog.setTitle(R.string.configuracao);
            alertDialog.setIcon(R.drawable.ic_action_action_settings);
            alertDialog.setView(R.layout.config_view);
            alertDialog.setMessage(R.string.configuracao);
            alertDialog.setPositiveButton(R.string.Salvar, null);
        } else if (tipo == 's') {
            alertDialog.setTitle(R.string.sobre);
            alertDialog.setMessage(R.string.sobre_descrito);
            alertDialog.setNeutralButton("OK", null);
        }

        alertDialog.create();
        alertDialog.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
