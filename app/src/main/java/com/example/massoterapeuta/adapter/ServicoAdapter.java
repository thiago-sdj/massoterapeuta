package com.example.massoterapeuta.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.ServicoSelecionadoActivity;
import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;

import io.reactivex.annotations.NonNull;

public class ServicoAdapter extends ListAdapter<ServicoEntidade, ServicoHolder> {

    public ServicoAdapter(@NonNull DiffUtil.ItemCallback<ServicoEntidade> diffCalback){
        super(diffCalback);
    }

    @Override
    public ServicoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ServicoHolder.create(parent);
    }

    @Override
    public void onBindViewHolder (ServicoHolder holder, int position){
        ServicoEntidade servico = getItem(position);
        holder.bind(servico.getIdServico().toString(),servico.getDescricao());
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
        public boolean areItemsTheSame(@NonNull ServicoEntidade itemAntigo, @NonNull ServicoEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@NonNull ServicoEntidade itemAntigo, @NonNull ServicoEntidade itemNovo){
            return itemAntigo.getDescricao().equals(itemNovo.getDescricao());
        }
    }
}
