package com.example.meuprimeiroappandoridjava.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.meuprimeiroappandoridjava.DAO.ContatoDAO;
import com.example.meuprimeiroappandoridjava.R;
import com.example.meuprimeiroappandoridjava.model.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AgendaHomeActivity extends AppCompatActivity {
    static ContatoDAO contatoDAO = new ContatoDAO();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_agenda);
        contatoDAO.salva(new Contato("Guilherme", "g@gmail.com", "12323123"));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AgendaHomeActivity.this, FormAddActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista(){
        ListView listaDeAlunos = findViewById(R.id.lsv1);
        final List<Contato> alunos = contatoDAO.todos();
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos));

        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato alunoSelecionado = alunos.get(position);
                Intent vaiFormActivity = new Intent(AgendaHomeActivity.this, FormAddActivity.class);
                vaiFormActivity.putExtra("aluno", alunoSelecionado);
                startActivity(vaiFormActivity);

                Log.i("posiçãoAluno", ""+alunoSelecionado);
            }
        });
    }
}
