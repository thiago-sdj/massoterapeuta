package com.example.massoterapeuta.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.ContratoSelecionadoActivity;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.viewModel.ClienteViewModel;
import com.example.massoterapeuta.viewModel.ServicoViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContratoSelecionadoAdapter extends ListAdapter<ContratoEntidade, ContratoSelecionadoHolder> {

    private LifecycleOwner lifecycleOwner;

    private ClienteViewModel clienteViewModel;

    private ServicoViewModel servicoViewModel;

    public ContratoSelecionadoAdapter(@io.reactivex.annotations.NonNull DiffUtil.ItemCallback<ContratoEntidade> diffCalback, LifecycleOwner lifecycleOwner){
        super(diffCalback);
        this.lifecycleOwner = lifecycleOwner;


    }

    @Override
    public ContratoSelecionadoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return ContratoSelecionadoHolder.create(parent);
    }

    @Override
    public void onBindViewHolder (ContratoSelecionadoHolder holder, int position){
        ContratoEntidade contrato = getItem(position);
        String dataNaoFormatada = contrato.getDataFirmado();

        DateTimeFormatter formatterOriginal = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(dataNaoFormatada, formatterOriginal);

        DateTimeFormatter formatterNovo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = localDate.format(formatterNovo);

        holder.bind(contrato.getIdContrato().toString(), contrato.getIdCliente().toString(), contrato.getIdServico().toString(),
                dataFormatada, contrato.getFormaPagamento().toString(), contrato.getVezesSemana().toString(),
                contrato.getNumeroSessoes().toString());
        clienteViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(ClienteViewModel.class);
        clienteViewModel.retornarClienteSelecionadoUsandoIdContrato(contrato.getIdCliente()).observe(lifecycleOwner, cliente -> {
            if (cliente != null){
                Log.w("Deu certo", "Carregou");
                holder.nomeCliente.setText(cliente.getNome());

            }
            else{
                Log.w("ERRO", "Não foi possível carregar");
            }

        });

        servicoViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(ServicoViewModel.class);
        servicoViewModel.retornarServicoSelecionado(contrato.getIdServico()).observe(lifecycleOwner, servico -> {
            if (servico != null){
                Log.w("Deu certo", "Carregou");
                holder.descricaoServico.setText(servico.getDescricao());

            }
            else{
                Log.w("ERRO", "Não foi possível carregar");
            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ContratoSelecionadoActivity.class);
                intent.putExtra("idContrato", contrato.getIdContrato()); // Pass data to the new activity if needed
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
