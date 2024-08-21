package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class SessaoSelecionadaHolder extends RecyclerView.ViewHolder {
    public final TextView horaInicio, horaFim, dataInicio, status, peso, endereco, nomeCliente;
    public SessaoSelecionadaHolder(@NonNull View itemView) {
        super(itemView);
        dataInicio = itemView.findViewById(R.id.itens_sessao_selecionada_data);
        horaInicio = itemView.findViewById(R.id.itens_sessao_selecionada_horaInicio);
        horaFim = itemView.findViewById(R.id.itens_sessao_selecionada_horaFim);
        status = itemView.findViewById(R.id.itens_sessao_selecionada_status);
        peso = itemView.findViewById(R.id.itens_sessao_selecionada_peso);
        endereco = itemView.findViewById(R.id.itens_sessao_selecionada_endereco);
        nomeCliente = itemView.findViewById(R.id.itens_sessao_selecionada_cliente);

    }
    public void bind (String horaInicioString, String dataInicioString, String horaFimString,
                      String statusString, String pesoString){
        horaInicio.setText(horaInicioString);
        horaFim.setText(horaFimString);
        dataInicio.setText(dataInicioString);
        status.setText(statusString);
        peso.setText(pesoString);
    }

    static SessaoSelecionadaHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itens_sessao_selecionada, parent, false);
        return new SessaoSelecionadaHolder(view);

    }
}
