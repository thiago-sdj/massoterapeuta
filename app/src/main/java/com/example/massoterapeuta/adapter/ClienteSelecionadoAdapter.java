package com.example.massoterapeuta.adapter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;

public class ClienteSelecionadoAdapter extends ListAdapter<ClienteEntidade, ClienteSelecionadoHolder> {

    public ClienteSelecionadoAdapter(@io.reactivex.annotations.NonNull DiffUtil.ItemCallback<ClienteEntidade> diffCalback){
        super(diffCalback);
    }

    @Override
    public ClienteSelecionadoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ClienteSelecionadoHolder.create(parent);
    }

    @Override
    public void onBindViewHolder (ClienteSelecionadoHolder holder, int position){
        ClienteEntidade cliente = getItem(position);
        holder.bind(cliente.getNome().toString(), cliente.getCep().toString(), cliente.getLogradouro().toString(),
                cliente.getComplemento().toString(), cliente.getBairro().toString(), cliente.getOcupacao().toString(),
                cliente.getTelefone().toString(), cliente.getSexo().toString(), cliente.getDataNascimento().toString(),
                cliente.getEmail().toString(), cliente.getQueixas().toString(), cliente.getHistorico().toString(),
                cliente.getMedicamentos().toString(), cliente.getSono().toString(), cliente.getAlimentacao().toString(),
                cliente.getDigestao().toString(), cliente.getExcrecao().toString(), cliente.getDoresFisicas().toString(),
                cliente.getDoresEmocionais().toString(), cliente.getMenstruacao().toString(), cliente.getParto().toString(),
                cliente.getCirurgias().toString(), cliente.getMobilidade().toString(), cliente.getVicios().toString(),
                cliente.getHormonal().toString(), cliente.getObservacao().toString(), cliente.getNumero().toString());
    }


    public static class clienteDiff extends DiffUtil.ItemCallback<ClienteEntidade>{
        @Override
        public boolean areItemsTheSame(@io.reactivex.annotations.NonNull ClienteEntidade itemAntigo, @io.reactivex.annotations.NonNull ClienteEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@io.reactivex.annotations.NonNull ClienteEntidade itemAntigo, @io.reactivex.annotations.NonNull ClienteEntidade itemNovo){
            return itemAntigo.getNome().equals(itemNovo.getNome());
        }
    }
}



