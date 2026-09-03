package br.com.itjoaogabriel.controle.model;

public class Inspetor {

    private Long id;
    private String nome;

    public Inspetor(String nome) {
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Informe o Nome do Inspetor");
        }
        this.nome = nome;
    }

    public Long getId() { return id; }
    public String getNome() { return this.nome; }
}
