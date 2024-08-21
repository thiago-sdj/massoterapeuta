package com.example.massoterapeuta.rede.model;

import java.math.BigDecimal;

public class ContratoModelConsulta {

    Long idContrato;

    ClienteModel clienteModel;
    ServicoModel servicoModel;
    String dataFirmado, formaPagamento;
    Integer vezesSemana, numeroSessoes;
    BigDecimal valorPago;

    public ContratoModelConsulta() {
    }

    public ContratoModelConsulta(Long idContrato, ClienteModel clienteModel, ServicoModel servicoModel, String dataFirmado, String formaPagamento, Integer vezesSemana, Integer numeroSessoes, BigDecimal valorPago) {
        this.idContrato = idContrato;
        this.clienteModel = clienteModel;
        this.servicoModel = servicoModel;
        this.dataFirmado = dataFirmado;
        this.formaPagamento = formaPagamento;
        this.vezesSemana = vezesSemana;
        this.numeroSessoes = numeroSessoes;
        this.valorPago = valorPago;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public ServicoModel getServicoModel() {
        return servicoModel;
    }

    public void setServicoModel(ServicoModel servicoModel) {
        this.servicoModel = servicoModel;
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
}
