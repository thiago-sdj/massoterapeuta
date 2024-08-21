package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class ClienteHolder extends RecyclerView.ViewHolder{
    TextView nome, id;
    public ClienteHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.item_lista_clientes_nome);
        id = itemView.findViewById(R.id.item_lista_clientes_id);
    }

    public void bind (String idNumber, String nomeCliente){
        id.setText(idNumber);
        nome.setText(nomeCliente);
    }

    static ClienteHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_clientes, parent, false);
        return new ClienteHolder(view);

    }

}
