package com.example.massoterapeuta.bancoLocal.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TB_CONTRATO")
public class ContratoEntidade {

    @PrimaryKey(autoGenerate = true)
    private Long idContrato;

    @ColumnInfo(name = "con_idCliente")
    private Long idCliente;

    @ColumnInfo(name = "con_idServico")
    private Long idServico;

    @ColumnInfo(name = "con_datafirmado")
    private String dataFirmado;

    @ColumnInfo(name = "con_vezessemana")
    private Integer vezesSemana;

    @ColumnInfo(name = "con_numsessoes")
    private Integer numeroSessoes;

    @ColumnInfo(name = "con_valorpago")
    private Double valorPago;

    @ColumnInfo(name = "con_formapagamento")
    private String formaPagamento;



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

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

   /* public ContratoModelExterno converterModelExterno() {
        return new ContratoModelExterno(idContrato,idCliente,idServico,dataFirmado,vezesSemana,
        numeroSessoes,valorPago,formaPagamento);
    }*/


}
