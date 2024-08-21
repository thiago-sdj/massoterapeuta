package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class ClienteSelecionadoHolder extends RecyclerView.ViewHolder{
    TextView nome, cep, logradouro, complemento, bairro, ocupacao, telefone, sexo;
    TextView email,  queixas, historico, medicamentos, sono, alimentacao, digestao, excrecao, doresFisicas, doresEmocionais;
    TextView menstruacao,  parto, cirurgias, mobilidade, vicios, hormonal, observacoes, numero, dataNascimento;

    public ClienteSelecionadoHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.itens_cliente_selecionado_nome);
        cep = itemView.findViewById(R.id.itens_cliente_selecionado_cep);
        logradouro = itemView.findViewById(R.id.itens_cliente_selecionado_logradouro);
        complemento = itemView.findViewById(R.id.itens_cliente_selecionado_complemento);
        bairro = itemView.findViewById(R.id.itens_cliente_selecionado_bairro);
        ocupacao = itemView.findViewById(R.id.itens_cliente_selecionado_ocupacao);
        telefone = itemView.findViewById(R.id.itens_cliente_selecionado_telefone);
        sexo = itemView.findViewById(R.id.itens_cliente_selecionado_sexo);
        dataNascimento = itemView.findViewById(R.id.itens_cliente_selecionado_dataNascimento);

        email = itemView.findViewById(R.id.itens_cliente_selecionado_email);
        queixas = itemView.findViewById(R.id.itens_cliente_selecionado_queixas);
        historico = itemView.findViewById(R.id.itens_cliente_selecionado_historico);
        medicamentos = itemView.findViewById(R.id.itens_cliente_selecionado_medicamentos);
        sono = itemView.findViewById(R.id.itens_cliente_selecionado_sono);
        alimentacao = itemView.findViewById(R.id.itens_cliente_selecionado_alimentacao);
        digestao = itemView.findViewById(R.id.itens_cliente_selecionado_digestao);
        excrecao = itemView.findViewById(R.id.itens_cliente_selecionado_excrecao);
        doresFisicas = itemView.findViewById(R.id.itens_cliente_selecionado_doresFisicas);
        doresEmocionais = itemView.findViewById(R.id.itens_cliente_selecionado_doresEmocionais);

        menstruacao = itemView.findViewById(R.id.itens_cliente_selecionado_menstruacao);
        parto = itemView.findViewById(R.id.itens_cliente_selecionado_parto);
        cirurgias = itemView.findViewById(R.id.itens_cliente_selecionado_cirurgias);
        mobilidade = itemView.findViewById(R.id.itens_cliente_selecionado_mobilidade);
        vicios = itemView.findViewById(R.id.itens_cliente_selecionado_vicios);
        hormonal = itemView.findViewById(R.id.itens_cliente_selecionado_hormonal);
        observacoes = itemView.findViewById(R.id.itens_cliente_selecionado_observacoes);
        numero = itemView.findViewById(R.id.itens_cliente_selecionado_numero);
    }

    public void bind(String nomeText, String cepText, String logradouroText, String complementoText, String bairroText,
                     String ocupacaoText, String telefoneText, String sexoText, String dataNascimentoText,
                     String emailText, String queixasText, String historicoText, String medicamentosText,
                     String sonoText, String alimentacaoText, String digestaoText, String excrecaoText,
                     String doresFisicasText, String doresEmocionaisText, String menstruacaoText,
                     String partoText, String cirurgiasText, String mobilidadeText, String viciosText,
                     String hormonalText, String observacoesText, String numeroText) {
        nome.setText(String.valueOf(nomeText));
        cep.setText(String.valueOf(cepText));
        logradouro.setText(String.valueOf(logradouroText));
        complemento.setText(String.valueOf(complementoText));
        bairro.setText(String.valueOf(bairroText));
        ocupacao.setText(String.valueOf(ocupacaoText));
        telefone.setText(String.valueOf(telefoneText));
        sexo.setText(String.valueOf(sexoText));
        dataNascimento.setText(String.valueOf(dataNascimentoText));
        email.setText(String.valueOf(emailText));
        queixas.setText(String.valueOf(queixasText));
        historico.setText(String.valueOf(historicoText));
        medicamentos.setText(String.valueOf(medicamentosText));
        sono.setText(String.valueOf(sonoText));
        alimentacao.setText(String.valueOf(alimentacaoText));
        digestao.setText(String.valueOf(digestaoText));
        excrecao.setText(String.valueOf(excrecaoText));
        doresFisicas.setText(String.valueOf(doresFisicasText));
        doresEmocionais.setText(String.valueOf(doresEmocionaisText));
        menstruacao.setText(String.valueOf(menstruacaoText));
        parto.setText(String.valueOf(partoText));
        cirurgias.setText(String.valueOf(cirurgiasText));
        mobilidade.setText(String.valueOf(mobilidadeText));
        vicios.setText(String.valueOf(viciosText));
        hormonal.setText(String.valueOf(hormonalText));
        observacoes.setText(String.valueOf(observacoesText));
        numero.setText(String.valueOf(numeroText));
    }


    static ClienteSelecionadoHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itens_cliente_selecionado, parent, false);
        return new ClienteSelecionadoHolder(view);

    }

}

