package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class SessaoHolder extends RecyclerView.ViewHolder {
    public final TextView horaInicio, dataInicio, status, endereco;

    public SessaoHolder(@NonNull View itemView) {
        super(itemView);
        horaInicio = itemView.findViewById(R.id.item_lista_sessao_hora);
        dataInicio = itemView.findViewById(R.id.item_lista_sessao_data);
        status = itemView.findViewById(R.id.item_lista_sessao_status);
        endereco = itemView.findViewById(R.id.item_lista_sessao_endereco);
    }
    public void bind (String horaInicioString, String dataInicioString,
                      String statusString, String enderecoString){
        horaInicio.setText(horaInicioString);
        dataInicio.setText(dataInicioString);
        status.setText(statusString);
        endereco.setText(enderecoString);

    }

    static SessaoHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_sessoes, parent, false);
        return new SessaoHolder(view);

    }

}
