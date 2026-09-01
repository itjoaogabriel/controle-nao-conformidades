package br.com.itjoaogabriel.controle.model;

import java.time.LocalDateTime;

public class NaoConformidade {

    private Long id;
    private String codigoPainel;
    private String componente;
    private String descricao;
    private String setorResponsavel;
    private LocalDateTime dataRegistro;
    private StatusNaoConformidade status;

    public NaoConformidade(String codigoPainel, String componente, String descricao, String setorResponsavel) {
        if (codigoPainel == null || codigoPainel.isBlank()) {
            throw new IllegalArgumentException("Informe o código do painel");
        }
        if (componente == null || componente.isBlank()) {
            throw new IllegalArgumentException("Informe qual é o componente");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Escreva a descrição");
        }
        if (setorResponsavel == null || setorResponsavel.isBlank()) {
            throw new IllegalArgumentException("Informe o setor responsável");
        }
        this.codigoPainel = codigoPainel;
        this.componente = componente;
        this.descricao = descricao;
        this.setorResponsavel = setorResponsavel;
        this.status = StatusNaoConformidade.ABERTA;
        this.dataRegistro = LocalDateTime.now();
    }

    public void iniciarTratamento() {
        if (this.status != StatusNaoConformidade.ABERTA) {
            throw new IllegalStateException(
                    "Apenas uma não conformidade aberta pode entrar em tratamento"
            );
        }
        this.status = StatusNaoConformidade.EM_TRATAMENTO;
    }

    public void enviarParaReinspecao() {
        if (this.status != StatusNaoConformidade.EM_TRATAMENTO) {
            throw new IllegalStateException(
                    "Apenas uma não conformidade em tratamento pode entrar em reinspeção"
            );
        }
        this.status = StatusNaoConformidade.AGUARDANDO_REINSPECAO;
    }

    public void encerrar() {
        if (this.status != StatusNaoConformidade.AGUARDANDO_REINSPECAO) {
            throw new IllegalStateException(
                    "Apenas uma não conformidade aguardando reinspeção pode ser encerrada"
            );
        }
        this.status = StatusNaoConformidade.ENCERRADA;
    }

    public void reprovarReinspecao() {
        if (this.status != StatusNaoConformidade.AGUARDANDO_REINSPECAO) {
            throw new IllegalStateException(
                    "A reinspeção só pode ser reprovada quando a não conformidade estiver aguardando reinspeção"
            );
        }
        this.status = StatusNaoConformidade.EM_TRATAMENTO;
    }

    public String getCodigoPainel() { return codigoPainel; }
    public String getComponente() { return componente; }
    public String getDescricao() { return descricao; }
    public String getSetorResponsavel() { return setorResponsavel; }
    public LocalDateTime getDataRegistro() { return this.dataRegistro; }
    public StatusNaoConformidade getStatus() { return this.status; }
}
