package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class ServicoHolder extends RecyclerView.ViewHolder {

    private final TextView nome, id;

    private ServicoHolder(View itemView) {
        super(itemView);
        id = itemView.findViewById(R.id.item_lista_servico_id);
        nome = itemView.findViewById(R.id.item_lista_servico_nome);
    }

    public void bind (String idNumber, String nomeServico){
        id.setText(idNumber);
        nome.setText(nomeServico);
    }

    static ServicoHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_servico, parent, false);
        return new ServicoHolder(view);

    }
}
