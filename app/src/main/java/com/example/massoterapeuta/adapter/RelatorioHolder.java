package com.example.massoterapeuta.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.R;

public class RelatorioHolder extends RecyclerView.ViewHolder {

    private final TextView nome, data, valor;

    private RelatorioHolder(View itemView) {
        super(itemView);
        data = itemView.findViewById(R.id.item_lista_relatorio_data);
        nome = itemView.findViewById(R.id.item_lista_relatorio_nome);
        valor = itemView.findViewById(R.id.item_lista_relatorio_valor);
    }

    public void bind ( String dataText, String nomeText, String valorText){
        data.setText(dataText);
        nome.setText(nomeText);
        valor.setText(valorText);
    }

    static RelatorioHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_relatorio, parent, false);
        return new RelatorioHolder(view);

    }


}
