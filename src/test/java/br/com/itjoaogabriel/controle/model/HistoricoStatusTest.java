package br.com.itjoaogabriel.controle.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HistoricoStatusTest {

    @Test
    void deveCriarHistoricoNovoSemId() {
        HistoricoStatus historico = new HistoricoStatus(
                1L,
                StatusNaoConformidade.ABERTA,
                StatusNaoConformidade.EM_TRATAMENTO
        );

        assertNull(historico.getId());
        assertEquals(1L, historico.getNaoConformidadeId());
        assertEquals(
                StatusNaoConformidade.ABERTA,
                historico.getStatusAnterior()
        );
        assertEquals(
                StatusNaoConformidade.EM_TRATAMENTO,
                historico.getStatusNovo()
        );
        assertNotNull(historico.getDataAlteracao());
    }

    @Test
    void deveRejeitarStatusIguais() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new HistoricoStatus(
                        1L,
                        StatusNaoConformidade.ABERTA,
                        StatusNaoConformidade.ABERTA
                )
        );
    }

    @Test
    void deveRejeitarIdInvalidoDaNaoConformidade() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new HistoricoStatus(
                        0L,
                        StatusNaoConformidade.ABERTA,
                        StatusNaoConformidade.EM_TRATAMENTO
                )
        );
    }

    @Test
    void deveRestaurarHistoricoExistente() {
        LocalDateTime dataAlteracao =
                LocalDateTime.of(2026, 9, 23, 17, 0);

        HistoricoStatus historico = HistoricoStatus.restaurar(
                10L,
                1L,
                StatusNaoConformidade.ABERTA,
                StatusNaoConformidade.EM_TRATAMENTO,
                dataAlteracao
        );

        assertEquals(10L, historico.getId());
        assertEquals(1L, historico.getNaoConformidadeId());
        assertEquals(
                StatusNaoConformidade.ABERTA,
                historico.getStatusAnterior()
        );
        assertEquals(
                StatusNaoConformidade.EM_TRATAMENTO,
                historico.getStatusNovo()
        );
        assertEquals(
                dataAlteracao,
                historico.getDataAlteracao()
        );
    }
}