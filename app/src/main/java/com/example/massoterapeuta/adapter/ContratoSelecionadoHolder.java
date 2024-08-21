package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class ContratoSelecionadoHolder extends RecyclerView.ViewHolder{

    final TextView idContrato, idCliente, nomeCliente, descricaoServico, idServico, dataFirmado, formaPagamento, vezesSemana, numeroSessoes;

    public ContratoSelecionadoHolder(@NonNull View itemView){
        super(itemView);
        idContrato = itemView.findViewById(R.id.itens_contrato_selecionado_idcontrato);
        idCliente = itemView.findViewById(R.id.itens_contrato_selecionado_idcliente);
        idServico = itemView.findViewById(R.id.itens_contrato_selecionado_idservico);
        dataFirmado = itemView.findViewById(R.id.itens_contrato_selecionado_datafirmado);
        formaPagamento = itemView.findViewById(R.id.itens_contrato_selecionado_formapagamento);
        vezesSemana = itemView.findViewById(R.id.itens_contrato_selecionado_vezessemana);
        numeroSessoes = itemView.findViewById(R.id.itens_contrato_selecionado_numerosessoes);
        nomeCliente = itemView.findViewById(R.id.itens_contrato_selecionado_nomecliente);
        descricaoServico= itemView.findViewById(R.id.itens_contrato_selecionado_descricaoservico);

    }
    public void bind (String idContratoText, String idClienteText, String idServicoText, String dataFirmadoText,
                      String formaPagamentoText, String vezesSemanaText, String numeroSessoesText){
        idContrato.setText(String.valueOf(idContratoText));
        idCliente.setText(String.valueOf(idClienteText));
        idServico.setText(String.valueOf(idServicoText));
        dataFirmado.setText(String.valueOf(dataFirmadoText));
        formaPagamento.setText(String.valueOf(formaPagamentoText));
        vezesSemana.setText(String.valueOf(vezesSemanaText));
        numeroSessoes.setText(String.valueOf(numeroSessoesText));

    }

    static ContratoSelecionadoHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itens_contrato_selecionados, parent, false);
        return new ContratoSelecionadoHolder(view);

    }
}
