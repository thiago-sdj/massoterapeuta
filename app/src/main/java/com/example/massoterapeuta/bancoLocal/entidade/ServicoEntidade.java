package com.example.massoterapeuta.bancoLocal.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TB_SERVICO")
public class ServicoEntidade {
    @PrimaryKey(autoGenerate = true)
    private Long idServico;

    @ColumnInfo(name = "ser_valorunit")
    private Float valorUnitario;

    @ColumnInfo(name = "ser_valorpac")
    private Float valorPacote;

    @ColumnInfo(name = "ser_peso")
    private Float peso;

    @ColumnInfo(name = "ser_tempo")
    private Integer tempo;

    @ColumnInfo(name = "ser_descricao")
    private String descricao;

    public ServicoEntidade(Long idServico, Float valorUnitario, Float valorPacote, Float peso, Integer tempo, String descricao) {
        this.idServico = idServico;
        this.valorUnitario = valorUnitario;
        this.valorPacote = valorPacote;
        this.peso = peso;
        this.tempo = tempo;
        this.descricao = descricao;
    }

    public ServicoEntidade() {
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public Float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Float getValorPacote() {
        return valorPacote;
    }

    public void setValorPacote(Float valorPacote) {
        this.valorPacote = valorPacote;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   /* public ServicoModelExterno converterModelExterno() {
        return new ServicoModelExterno(idServico,valorUnitario,valorPacote,peso,tempo,descricao);
    }*/
}
