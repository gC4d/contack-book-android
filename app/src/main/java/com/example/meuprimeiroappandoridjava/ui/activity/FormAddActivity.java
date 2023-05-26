package com.example.meuprimeiroappandoridjava.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.meuprimeiroappandoridjava.DAO.ContatoDAO;
import com.example.meuprimeiroappandoridjava.R;
import com.example.meuprimeiroappandoridjava.model.Contato;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class FormAddActivity extends AppCompatActivity {

    private TextInputEditText tfNome;
    private TextInputEditText tfEmail;
    private TextInputEditText tfPhone;

    private Button addButton;
    private final ContatoDAO contatoDAO = new ContatoDAO();

    private static Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add);
        inicializaData();
        configuraBotao();
        Log.i("intent", ""+ getIntent());
        Intent dados = getIntent();
        contato = ( Contato) dados.getExtras().getSerializable("aluno");
        tfNome.setText(contato.getName());
        tfEmail.setText(contato.getEmail());
        tfPhone.setText(contato.getPhone());

    }

    private void inicializaData(){
        tfNome = findViewById(R.id.act_form_tfName);
        tfEmail = findViewById(R.id.act_form_tfEmail);
        tfPhone = findViewById(R.id.act_form_tfPhone);
    }

    private void configuraBotao(){
        addButton = findViewById(R.id.act_form_add_buttom);
        addButton.setOnClickListener(v -> {
            preenche();
            contatoDAO.edita(contato);
            finish();
        });
    }
    /*
    private void salvar(){
            if(!contato.getName().isEmpty() && !contato.getEmail().isEmpty() && !contato.getPhone().isEmpty()){
                Toast.makeText(FormAddActivity.this, contato.getName() + " adicionado a agenda", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(FormAddActivity.this, "Cadastro incompleto", Toast.LENGTH_SHORT).show();
            }

    }
    */

    private void preenche() {
        String name = tfNome.getText().toString();
        String email = tfEmail.getText().toString();
        String phone = tfPhone.getText().toString();

        contato.setName(name);
        contato.setEmail(email);
        contato.setPhone(phone);
    }
}