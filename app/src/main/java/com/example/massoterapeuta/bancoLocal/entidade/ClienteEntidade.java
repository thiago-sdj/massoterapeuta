package com.example.massoterapeuta.bancoLocal.entidade;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TB_CLIENTE")
public class ClienteEntidade {

    @PrimaryKey(autoGenerate = true)
    private Long idCliente;

    @ColumnInfo(name = "cli_nome")
    private String nome;

    @ColumnInfo(name = "cli_cep")
    private String cep;

    @ColumnInfo(name = "cli_logradouro")
    private String logradouro;

    @ColumnInfo(name = "cli_numero")
    private String numero;

    @ColumnInfo(name = "cli_complemento")
    private String complemento;

    @ColumnInfo(name = "cli_bairro")
    private String bairro;

    @ColumnInfo(name = "cli_ocupacao")
    private String ocupacao;

    @ColumnInfo(name = "cli_telefone")
    private String telefone;

    @ColumnInfo(name = "cli_sexo")
    private String sexo;

    @ColumnInfo(name = "cli_dataNascimento")
    private String dataNascimento;

    @ColumnInfo(name = "cli_email")
    private String email;

    @ColumnInfo(name = "cli_queixas")
    private String queixas;

    @ColumnInfo(name = "cli_historico")
    private String historico;

    @ColumnInfo(name = "cli_medicamentos")
    private String medicamentos;

    @ColumnInfo(name = "cli_sono")
    private String sono;

    @ColumnInfo(name = "cli_alimentacao")
    private String alimentacao;

    @ColumnInfo(name = "cli_digestao")
    private String digestao;

    @ColumnInfo(name = "cli_excrecao")
    private String excrecao;

    @ColumnInfo(name = "cli_doresFisicas")
    private String doresFisicas;

    @ColumnInfo(name = "cli_doresEmocionais")
    private String doresEmocionais;

    @ColumnInfo(name = "cli_menstruacao")
    private String menstruacao;

    @ColumnInfo(name = "cli_parto")
    private String parto;

    @ColumnInfo(name = "cli_cirurgias")
    private String cirurgias;

    @ColumnInfo(name = "cli_mobilidade")
    private String mobilidade;

    @ColumnInfo(name = "cli_vicios")
    private String vicios;

    @ColumnInfo(name = "cli_hormonal")
    private String hormonal;

    @ColumnInfo(name = "cli_observacao")
    private String observacao;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQueixas() {
        return queixas;
    }

    public void setQueixas(String queixas) {
        this.queixas = queixas;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getSono() {
        return sono;
    }

    public void setSono(String sono) {
        this.sono = sono;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public String getDigestao() {
        return digestao;
    }

    public void setDigestao(String digestao) {
        this.digestao = digestao;
    }

    public String getExcrecao() {
        return excrecao;
    }

    public void setExcrecao(String excrecao) {
        this.excrecao = excrecao;
    }

    public String getDoresFisicas() {
        return doresFisicas;
    }

    public void setDoresFisicas(String doresFisicas) {
        this.doresFisicas = doresFisicas;
    }

    public String getDoresEmocionais() {
        return doresEmocionais;
    }

    public void setDoresEmocionais(String doresEmocionais) {
        this.doresEmocionais = doresEmocionais;
    }

    public String getMenstruacao() {
        return menstruacao;
    }

    public void setMenstruacao(String menstruacao) {
        this.menstruacao = menstruacao;
    }

    public String getParto() {
        return parto;
    }

    public void setParto(String parto) {
        this.parto = parto;
    }

    public String getCirurgias() {
        return cirurgias;
    }

    public void setCirurgias(String cirurgias) {
        this.cirurgias = cirurgias;
    }

    public String getMobilidade() {
        return mobilidade;
    }

    public void setMobilidade(String mobilidade) {
        this.mobilidade = mobilidade;
    }

    public String getVicios() {
        return vicios;
    }

    public void setVicios(String vicios) {
        this.vicios = vicios;
    }

    public String getHormonal() {
        return hormonal;
    }

    public void setHormonal(String hormonal) {
        this.hormonal = hormonal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /*public ClienteModelExterno converterModelExterno() {
        return new ClienteModelExterno(idCliente, nome,  cep,  logradouro, numero, complemento,
                 bairro,  ocupacao,  telefone,  sexo, dataNascimento, email, queixas,
                 historico,  medicamentos,  sono,  alimentacao,  digestao,
                 excrecao,  doresFisicas,  doresEmocionais,  menstruacao,  parto,
                 cirurgias,  mobilidade, vicios,  hormonal,  observacao);
    }*/
}
