package com.example.massoterapeuta.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.ClienteSelecionadoActivity;
import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;

import io.reactivex.annotations.NonNull;

public class ClienteAdapter extends ListAdapter<ClienteEntidade, ClienteHolder> {

    public ClienteAdapter(@io.reactivex.annotations.NonNull DiffUtil.ItemCallback<ClienteEntidade> diffCalback){
        super(diffCalback);
    }

    @Override
    public ClienteHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ClienteHolder.create(parent);
    }

    @Override
    public void onBindViewHolder (ClienteHolder holder, int position){
        ClienteEntidade clienteEntidade = getItem(position);
        holder.bind(clienteEntidade.getIdCliente().toString(),clienteEntidade.getNome());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ClienteSelecionadoActivity.class);
                intent.putExtra("idCliente", clienteEntidade.getIdCliente());
                v.getContext().startActivity(intent);
                Log.w("teste", clienteEntidade.getIdCliente().toString());
            }
        });
    }

    public static class clienteDiff extends DiffUtil.ItemCallback<ClienteEntidade>{
        @Override
        public boolean areItemsTheSame(@io.reactivex.annotations.NonNull ClienteEntidade itemAntigo, @io.reactivex.annotations.NonNull ClienteEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@io.reactivex.annotations.NonNull ClienteEntidade itemAntigo, @NonNull ClienteEntidade itemNovo){
            return itemAntigo.getNome().equals(itemNovo.getNome());
        }
    }
}