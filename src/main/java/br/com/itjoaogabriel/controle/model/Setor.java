package br.com.itjoaogabriel.controle.model;

public class Setor {

    private final Long id;
    private final String nome;
    private boolean ativo;

    public Setor(String nome) {
        this(null, nome, true);
    }

    private Setor(Long id, String nome, boolean ativo) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("O ID do setor deve ser positivo");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Informe o nome do setor");
        }

        String nomeTratado = nome.trim();

        if (nomeTratado.length() > 100) {
            throw new IllegalArgumentException("O nome do setor deve ter no máximo 100 caracteres");
        }

        this.id = id;
        this.nome = nomeTratado;
        this.ativo = ativo;
    }

    public static Setor restaurar(Long id, String nome, boolean ativo) {
        if (id == null) {
            throw new IllegalArgumentException("Informe o ID do setor");
        }

        return new Setor(id, nome, ativo);
    }

    public void desativar() { this.ativo = false; }
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public boolean isAtivo() { return ativo; }
}