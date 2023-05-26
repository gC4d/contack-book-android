package com.example.meuprimeiroappandoridjava.DAO;

import android.util.Log;

import com.example.meuprimeiroappandoridjava.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private final static List<Contato> contatos = new ArrayList<>();
    private static int idCount = 1;

    public void salva(Contato contato) {
        contato.setId(idCount);
        contatos.add(contato);
        idCount++;
    }

    public void edita(Contato contato) {
        Contato contatoSelecionado = null;
        for (Contato c : contatos) {
            if (c.getId() == contato.getId()) {
                contatoSelecionado = c;
            }
        }
        if (contatoSelecionado != null) {
            Log.w("selecionado:", ""+contatoSelecionado.toString());
            int posicaoContato = contatos.indexOf(contatoSelecionado);
            contatos.set(posicaoContato, contato);
        }
    }

    public List<Contato> todos() {
        return new ArrayList<>(contatos);
    }
}
