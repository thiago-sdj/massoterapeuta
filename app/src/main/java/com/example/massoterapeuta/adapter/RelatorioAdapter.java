package com.example.massoterapeuta.adapter;

import android.content.Intent;
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

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class RelatorioAdapter extends ListAdapter<ContratoEntidade, RelatorioHolder>  {

    LifecycleOwner lifecycleOwner;

    private List<ContratoEntidade> originalList;
    private List<ContratoEntidade> filteredList;
    public RelatorioAdapter(@NonNull DiffUtil.ItemCallback<ContratoEntidade> diffCalback, LifecycleOwner lifecycleOwner){
        super(diffCalback);
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public RelatorioHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return RelatorioHolder.create(parent);
    }

    @Override
    public void onBindViewHolder (RelatorioHolder holder, int position){
        ContratoEntidade contrato = getItem(position);
        ClienteViewModel clienteViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(ClienteViewModel.class);
        clienteViewModel.retornarClienteSelecionadoUsandoIdContrato(contrato.getIdContrato()).observe(lifecycleOwner, cliente ->{
            if (cliente != null){
                String dataNaoFormatada = contrato.getDataFirmado();

                DateTimeFormatter formatterOriginal = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.parse(dataNaoFormatada, formatterOriginal);

                DateTimeFormatter formatterNovo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = localDate.format(formatterNovo);

                DecimalFormat formatter = new DecimalFormat("#,###.00");
                String valorPago = formatter.format(contrato.getValorPago());

                holder.bind(dataFormatada, cliente.getNome(), valorPago);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ContratoSelecionadoActivity.class);
                intent.putExtra("idContrato", contrato.getIdContrato());
                v.getContext().startActivity(intent);
            }
        });
    }

    public static class relatorioDiff extends DiffUtil.ItemCallback<ContratoEntidade>{
        @Override
        public boolean areItemsTheSame(@NonNull ContratoEntidade itemAntigo, @NonNull ContratoEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@NonNull ContratoEntidade itemAntigo, @NonNull ContratoEntidade itemNovo){
            return itemAntigo.getIdContrato().equals(itemNovo.getIdContrato());
        }
    }
}
