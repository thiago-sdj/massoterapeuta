package com.example.massoterapeuta.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.ServicoSelecionadoActivity;
import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;


public class ServicoSelecionadoAdapter extends ListAdapter<ServicoEntidade, ServicoSelecionadoHolder> {

    public ServicoSelecionadoAdapter(@io.reactivex.annotations.NonNull DiffUtil.ItemCallback<ServicoEntidade> diffCalback){
        super(diffCalback);
    }

    @Override
    public ServicoSelecionadoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ServicoSelecionadoHolder.create(parent);
    }

    @Override
    public void onBindViewHolder (ServicoSelecionadoHolder holder, int position){
        ServicoEntidade servico = getItem(position);
        holder.bind(servico.getDescricao().toString(),servico.getValorUnitario().toString(), servico.getValorPacote().toString(),
                servico.getPeso().toString(), servico.getTempo().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ServicoSelecionadoActivity.class);
                intent.putExtra("idServico", servico.getIdServico());
                v.getContext().startActivity(intent);
                Log.w("teste", servico.getIdServico().toString());
            }
        });
    }

    public static class servicoDiff extends DiffUtil.ItemCallback<ServicoEntidade>{
        @Override
        public boolean areItemsTheSame(@io.reactivex.annotations.NonNull ServicoEntidade itemAntigo, @io.reactivex.annotations.NonNull ServicoEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@io.reactivex.annotations.NonNull ServicoEntidade itemAntigo, @io.reactivex.annotations.NonNull ServicoEntidade itemNovo){
            return itemAntigo.getDescricao().equals(itemNovo.getDescricao());
        }
    }
}

