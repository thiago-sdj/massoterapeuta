package com.example.massoterapeuta.modelExterno;

public class SessaoModelExterno {
    private Long idSessao;

    public SessaoModelExterno(Long idSessao, Long idContrato, String dataInicio, String horaInicio, String horaFim, Long pesoSessao, String status) {
        this.idSessao = idSessao;
        this.idContrato = idContrato;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.pesoSessao = pesoSessao;
        this.status = status;
    }

    private Long idContrato;

    private String dataInicio;

    private String horaInicio;

    private String horaFim;

    private Long pesoSessao;

    private String status;
}
