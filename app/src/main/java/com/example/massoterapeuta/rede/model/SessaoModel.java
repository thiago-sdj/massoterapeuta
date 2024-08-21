package com.example.massoterapeuta.rede.model;

public class SessaoModel {

    private Long idSessao, idContrato, pesoSessao;
    private String dataInicio, horaInicio, horaFim, status;

    public SessaoModel() {
    }

    public SessaoModel(Long idSessao, Long idContrato, Long pesoSessao, String dataInicio, String horaInicio, String horaFim, String status) {
        this.idSessao = idSessao;
        this.idContrato = idContrato;
        this.pesoSessao = pesoSessao;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.status = status;
    }

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Long getPesoSessao() {
        return pesoSessao;
    }

    public void setPesoSessao(Long pesoSessao) {
        this.pesoSessao = pesoSessao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
