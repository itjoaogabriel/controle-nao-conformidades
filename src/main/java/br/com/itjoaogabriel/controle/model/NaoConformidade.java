package br.com.itjoaogabriel.controle.model;

import java.time.LocalDateTime;

public class NaoConformidade {

    private final Long id;
    private final String codigoPainel;
    private final String componente;
    private final String descricao;
    private final LocalDateTime dataRegistro;
    private StatusNaoConformidade status;
    private final Inspetor inspetor;
    private final Setor setorOrigem;

    public NaoConformidade(
            String codigoPainel,
            String componente,
            String descricao,
            Inspetor inspetor,
            Setor setorOrigem
    ) {
        this(
                null,
                codigoPainel,
                componente,
                descricao,
                LocalDateTime.now(),
                StatusNaoConformidade.ABERTA,
                inspetor,
                setorOrigem
        );
    }

    private NaoConformidade(
            Long id,
            String codigoPainel,
            String componente,
            String descricao,
            LocalDateTime dataRegistro,
            StatusNaoConformidade status,
            Inspetor inspetor,
            Setor setorOrigem
    ) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("O ID da não conformidade deve ser positivo");
        }

        if (dataRegistro == null) {
            throw new IllegalArgumentException("Informe a data de registro");
        }

        if (status == null) {
            throw new IllegalArgumentException("Informe o status");
        }

        if (inspetor == null) {
            throw new IllegalArgumentException("Informe o inspetor");
        }

        if (setorOrigem == null) {
            throw new IllegalArgumentException("Informe o setor de origem");
        }

        this.id = id;
        this.codigoPainel = validarTexto(
                codigoPainel,
                "código do painel",
                50
        );
        this.componente = validarTexto(
                componente,
                "componente",
                100
        );
        this.descricao = validarTexto(
                descricao,
                "descrição",
                null
        );
        this.dataRegistro = dataRegistro;
        this.status = status;
        this.inspetor = inspetor;
        this.setorOrigem = setorOrigem;
    }

    public static NaoConformidade restaurar(
            Long id,
            String codigoPainel,
            String componente,
            String descricao,
            LocalDateTime dataRegistro,
            StatusNaoConformidade status,
            Inspetor inspetor,
            Setor setorOrigem
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Informe o ID da não conformidade");
        }

        return new NaoConformidade(
                id,
                codigoPainel,
                componente,
                descricao,
                dataRegistro,
                status,
                inspetor,
                setorOrigem
        );
    }

    private static String validarTexto(String valor, String campo, Integer tamanhoMaximo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("Informe " + campo);
        }

        String valorTratado = valor.trim();

        if (tamanhoMaximo != null && valorTratado.length() > tamanhoMaximo) {
            throw new IllegalArgumentException(
                    "O campo " + campo + " deve ter no máximo " + tamanhoMaximo + " caracteres"
            );
        }

        return valorTratado;
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

    public void reprovarReinspecao() {
        if (this.status != StatusNaoConformidade.AGUARDANDO_REINSPECAO) {
            throw new IllegalStateException(
                    "A reinspeção só pode ser reprovada quando a não conformidade estiver aguardando reinspeção"
            );
        }

        this.status = StatusNaoConformidade.EM_TRATAMENTO;
    }

    public void encerrar() {
        if (this.status != StatusNaoConformidade.AGUARDANDO_REINSPECAO) {
            throw new IllegalStateException(
                    "Apenas uma não conformidade aguardando reinspeção pode ser encerrada"
            );
        }

        this.status = StatusNaoConformidade.ENCERRADA;
    }

    public Long getId() { return id; }
    public String getCodigoPainel() { return codigoPainel; }
    public String getComponente() { return componente;}
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public StatusNaoConformidade getStatus() { return status; }
    public Inspetor getInspetor() { return inspetor; }
    public Setor getSetorOrigem() { return setorOrigem; }
}