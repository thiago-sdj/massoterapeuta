package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class ServicoSelecionadoHolder extends RecyclerView.ViewHolder{

    private final TextView descricao, valorUnitario,  valorPacote, peso, tempo;

    private ServicoSelecionadoHolder(View itemView) {
        super(itemView);
        descricao = itemView.findViewById(R.id.itens_servico_selecionado_descricao);
        valorUnitario = itemView.findViewById(R.id.itens_servico_selecionado_valorunitario);
        valorPacote = itemView.findViewById(R.id.itens_servico_selecionado_valorpacote);
        peso = itemView.findViewById(R.id.itens_servico_selecionado_peso);
        tempo = itemView.findViewById(R.id.itens_servico_selecionado_tempo);
    }


    public void bind (String descricaoText, String valorUnitarioText, String valorPacoteText, String pesoText, String tempoText){
        descricao.setText(String.valueOf(descricaoText));
        valorUnitario.setText(String.valueOf(valorUnitarioText));
        valorPacote.setText(String.valueOf(valorPacoteText));
        peso.setText(String.valueOf(pesoText));
        tempo.setText(String.valueOf(tempoText));
    }

    static ServicoSelecionadoHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itens_servico_selecionados, parent, false);
        return new ServicoSelecionadoHolder(view);

    }

}
