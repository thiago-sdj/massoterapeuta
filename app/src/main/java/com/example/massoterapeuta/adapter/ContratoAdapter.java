package com.example.massoterapeuta.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.ContratoSelecionadoActivity;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.viewModel.ClienteViewModel;

public class ContratoAdapter extends ListAdapter<ContratoEntidade, ContratoHolder> {

    private LifecycleOwner lifecycleOwner;

    private ClienteViewModel clienteViewModel;

    public ContratoAdapter(@io.reactivex.annotations.NonNull DiffUtil.ItemCallback<ContratoEntidade> diffCalback, LifecycleOwner lifecycleOwner){
        super(diffCalback);
        this.lifecycleOwner = lifecycleOwner;


    }

    @Override
    public ContratoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ContratoHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ContratoHolder holder, int position) {
        ContratoEntidade contrato = getItem(position);
        holder.idContrato.setText(String.valueOf(contrato.getIdContrato()));
        clienteViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(ClienteViewModel.class);
        clienteViewModel.retornarClienteSelecionadoUsandoIdContrato(contrato.getIdContrato()).observe(lifecycleOwner, cliente -> {
            if (cliente != null){
                holder.nomeCliente.setText(cliente.getNome());
            }
            else{
                Log.w("ERRO", "Não foi possível carregar");
            }

        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ContratoSelecionadoActivity.class);
                Log.d("ContratoAdapter", "onClick: idContrato = " + contrato.getIdContrato());
                intent.putExtra("idContrato", contrato.getIdContrato());
                v.getContext().startActivity(intent);
            }
        });
    }

    public static class contratoDiff extends DiffUtil.ItemCallback<ContratoEntidade>{
        @Override
        public boolean areItemsTheSame(@io.reactivex.annotations.NonNull ContratoEntidade itemAntigo, @io.reactivex.annotations.NonNull ContratoEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@io.reactivex.annotations.NonNull ContratoEntidade itemAntigo, @io.reactivex.annotations.NonNull ContratoEntidade itemNovo){
            return itemAntigo.getIdContrato().equals(itemNovo.getIdContrato());
        }
    }
}
