package com.example.massoterapeuta.rede.model;

public class ClienteModel {

    private Long idCliente;
    private String nome, cep, logradouro, complemento, bairro, ocupacao, telefone, sexo;
    private String email,  queixas, historico, medicamentos, sono, alimentacao, digestao, excrecao, doresFisicas, doresEmocionais;
    private String menstruacao,  parto, cirurgias, mobilidade, vicios, hormonal, observacao;
    private Integer numero;
    private String dataNascimento;

    public ClienteModel() {
    }

    public ClienteModel(Long idCliente, String nome, String cep, String logradouro, String complemento, String bairro, String ocupacao, String telefone, String sexo, String email, String queixas, String historico, String medicamentos, String sono, String alimentacao, String digestao, String excrecao, String doresFisicas, String doresEmocionais, String menstruacao, String parto, String cirurgias, String mobilidade, String vicios, String hormonal, String observacao, String dataNascimento, Integer numero) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.ocupacao = ocupacao;
        this.telefone = telefone;
        this.sexo = sexo;
        this.email = email;
        this.queixas = queixas;
        this.historico = historico;
        this.medicamentos = medicamentos;
        this.sono = sono;
        this.alimentacao = alimentacao;
        this.digestao = digestao;
        this.excrecao = excrecao;
        this.doresFisicas = doresFisicas;
        this.doresEmocionais = doresEmocionais;
        this.menstruacao = menstruacao;
        this.parto = parto;
        this.cirurgias = cirurgias;
        this.mobilidade = mobilidade;
        this.vicios = vicios;
        this.hormonal = hormonal;
        this.observacao = observacao;
        this.dataNascimento = dataNascimento;
        this.numero = numero;
    }

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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "ClienteModel{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", ocupacao='" + ocupacao + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sexo='" + sexo + '\'' +
                ", email='" + email + '\'' +
                ", queixas='" + queixas + '\'' +
                ", historico='" + historico + '\'' +
                ", medicamentos='" + medicamentos + '\'' +
                ", sono='" + sono + '\'' +
                ", alimentacao='" + alimentacao + '\'' +
                ", digestao='" + digestao + '\'' +
                ", excrecao='" + excrecao + '\'' +
                ", doresFisicas='" + doresFisicas + '\'' +
                ", doresEmocionais='" + doresEmocionais + '\'' +
                ", menstruacao='" + menstruacao + '\'' +
                ", parto='" + parto + '\'' +
                ", cirurgias='" + cirurgias + '\'' +
                ", mobilidade='" + mobilidade + '\'' +
                ", vicios='" + vicios + '\'' +
                ", hormonal='" + hormonal + '\'' +
                ", observacao='" + observacao + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", numero=" + numero +
                '}';
    }
}
