package com.example.massoterapeuta.bancoLocal.entidade;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TB_SESSAO")
public class SessaoEntidade {

    @PrimaryKey(autoGenerate = true)
    private Long idSessao;

    @ColumnInfo(name = "cont_idContrato")
    private Long idContrato;

    @ColumnInfo(name = "ses_datainicio")
    private String dataInicio;
    @ColumnInfo(name = "ses_horainicio")
    private String horaInicio;

    @ColumnInfo(name = "ses_horafim")
    private String horaFim;

    @ColumnInfo(name = "ses_pesosessao")
    private Float pesoSessao;

    @ColumnInfo(name = "ses_status")
    private String status;

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

    public Float getPesoSessao() {
        return pesoSessao;
    }

    public void setPesoSessao(Float pesoSessao) {
        this.pesoSessao = pesoSessao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*public SessaoModelExterno converterModelExterno() {
        return new SessaoModelExterno(idSessao, idContrato, dataInicio,horaInicio, horaFim, pesoSessao, status);
    }*/
}
