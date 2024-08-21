package com.example.massoterapeuta.rede.model;

import java.math.BigDecimal;

public class ContratoModel {

    Long idContrato, idCliente, idServico;
    String dataFirmado, formaPagamento;
    Integer vezesSemana, numeroSessoes;
    BigDecimal valorPago;

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getDataFirmado() {
        return dataFirmado;
    }

    public void setDataFirmado(String dataFirmado) {
        this.dataFirmado = dataFirmado;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getVezesSemana() {
        return vezesSemana;
    }

    public void setVezesSemana(Integer vezesSemana) {
        this.vezesSemana = vezesSemana;
    }

    public Integer getNumeroSessoes() {
        return numeroSessoes;
    }

    public void setNumeroSessoes(Integer numeroSessoes) {
        this.numeroSessoes = numeroSessoes;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public ContratoModel(Long idContrato, Long idCliente, Long idServico, String dataFirmado, String formaPagamento, Integer vezesSemana, Integer numeroSessoes, BigDecimal valorPago) {
        this.idContrato = idContrato;
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.dataFirmado = dataFirmado;
        this.formaPagamento = formaPagamento;
        this.vezesSemana = vezesSemana;
        this.numeroSessoes = numeroSessoes;
        this.valorPago = valorPago;
    }

    public ContratoModel() {
    }
}
