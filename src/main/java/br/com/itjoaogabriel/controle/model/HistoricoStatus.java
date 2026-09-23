package br.com.itjoaogabriel.controle.model;

import java.time.LocalDateTime;

public class HistoricoStatus {

    private final Long id;
    private final Long naoConformidadeId;
    private final StatusNaoConformidade statusAnterior;
    private final StatusNaoConformidade statusNovo;
    private final LocalDateTime dataAlteracao;

    public HistoricoStatus(
            Long naoConformidadeId,
            StatusNaoConformidade statusAnterior,
            StatusNaoConformidade statusNovo
    ) {
        this(
                null,
                naoConformidadeId,
                statusAnterior,
                statusNovo,
                LocalDateTime.now()
        );
    }

    private HistoricoStatus(
            Long id,
            Long naoConformidadeId,
            StatusNaoConformidade statusAnterior,
            StatusNaoConformidade statusNovo,
            LocalDateTime dataAlteracao
    ) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException(
                    "O ID do histórico deve ser positivo"
            );
        }

        if (naoConformidadeId == null || naoConformidadeId <= 0) {
            throw new IllegalArgumentException(
                    "Informe um ID válido para a não conformidade"
            );
        }

        if (statusAnterior == null) {
            throw new IllegalArgumentException(
                    "Informe o status anterior"
            );
        }

        if (statusNovo == null) {
            throw new IllegalArgumentException(
                    "Informe o novo status"
            );
        }

        if (statusAnterior == statusNovo) {
            throw new IllegalArgumentException(
                    "O novo status deve ser diferente do status anterior"
            );
        }

        if (dataAlteracao == null) {
            throw new IllegalArgumentException(
                    "Informe a data da alteração"
            );
        }

        this.id = id;
        this.naoConformidadeId = naoConformidadeId;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.dataAlteracao = dataAlteracao;
    }

    public static HistoricoStatus restaurar(
            Long id,
            Long naoConformidadeId,
            StatusNaoConformidade statusAnterior,
            StatusNaoConformidade statusNovo,
            LocalDateTime dataAlteracao
    ) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Informe o ID do histórico"
            );
        }

        return new HistoricoStatus(
                id,
                naoConformidadeId,
                statusAnterior,
                statusNovo,
                dataAlteracao
        );
    }

    public Long getId() { return id; }
    public Long getNaoConformidadeId() { return naoConformidadeId; }
    public StatusNaoConformidade getStatusAnterior() { return statusAnterior; }
    public StatusNaoConformidade getStatusNovo() { return statusNovo; }
    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
}