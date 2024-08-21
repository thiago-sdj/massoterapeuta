package com.example.massoterapeuta.modelExterno;

public class ServicoModelExterno {
    public ServicoModelExterno(Long idServico, Integer valorUnitario, Integer valorPacote, Integer peso, Integer tempo, String descricao) {
        this.idServico = idServico;
        this.valorUnitario = valorUnitario;
        this.valorPacote = valorPacote;
        this.peso = peso;
        this.tempo = tempo;
        this.descricao = descricao;
    }

    private Long idServico;

    private Integer valorUnitario;

    private Integer valorPacote;
    private Integer peso;

    private Integer tempo;

    private String descricao;


}
