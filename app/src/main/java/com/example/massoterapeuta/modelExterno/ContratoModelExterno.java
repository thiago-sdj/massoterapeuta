package com.example.massoterapeuta.modelExterno;

import androidx.room.PrimaryKey;

public class ContratoModelExterno {
    public ContratoModelExterno(Long idContrato, Long idCliente, Long idServico, String dataFirmado, Integer vezesSemana, Integer numeroSessoes, Integer valorPago, String formaPagamento) {
        this.idContrato = idContrato;
        this.idCliente = idCliente;
        this.idServico = idServico;
        this.dataFirmado = dataFirmado;
        this.vezesSemana = vezesSemana;
        this.numeroSessoes = numeroSessoes;
        this.valorPago = valorPago;
        this.formaPagamento = formaPagamento;
    }

    @PrimaryKey(autoGenerate = true)
    private Long idContrato;

    private Long idCliente;

    private Long idServico;

    private String dataFirmado;

    private Integer vezesSemana;

    private Integer numeroSessoes;

    private Integer valorPago;
    private String formaPagamento;
}
