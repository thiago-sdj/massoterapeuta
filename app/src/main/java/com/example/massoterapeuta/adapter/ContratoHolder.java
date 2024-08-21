package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class ContratoHolder extends RecyclerView.ViewHolder {

    TextView idContrato, nomeCliente;
    public ContratoHolder(@NonNull View itemView) {
        super(itemView);
        idContrato = itemView.findViewById(R.id.item_lista_contratos_idcontrato);
        nomeCliente = itemView.findViewById(R.id.item_lista_contratos_nomeCliente);
    }

    public void bind (String idNumber, String nomeClienteText){
        idContrato.setText(idNumber);
        nomeCliente.setText(nomeClienteText);
    }

    static ContratoHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_contratos, parent, false);
        return new ContratoHolder(view);

    }
}
