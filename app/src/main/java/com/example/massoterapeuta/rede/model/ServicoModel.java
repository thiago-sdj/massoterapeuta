package com.example.massoterapeuta.rede.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class ServicoModel {
    @SerializedName("idServico")
    Long idServico;
    String descricao;

    BigDecimal valorUnitario, valorPacote;

    Integer peso, tempo;

    public ServicoModel(Long idServico, String descricao, BigDecimal valorUnitario, BigDecimal valorPacote, Integer peso, Integer tempo) {
        this.idServico = idServico;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.valorPacote = valorPacote;
        this.peso = peso;
        this.tempo = tempo;
    }

    public ServicoModel() {
    }

    public Long getidServico() {
        return idServico;
    }

    public void setidServico(Long idServico) {
        idServico = idServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorPacote() {
        return valorPacote;
    }

    public void setValorPacote(BigDecimal valorPacote) {
        this.valorPacote = valorPacote;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return "ServicoModel{" +
                "idServico=" + idServico +
                ", descricao='" + descricao + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", valorPacote=" + valorPacote +
                ", peso=" + peso +
                ", tempo=" + tempo +
                '}';
    }
}
