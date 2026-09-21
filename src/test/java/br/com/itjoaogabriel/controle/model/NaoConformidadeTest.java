package br.com.itjoaogabriel.controle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NaoConformidadeTest {

    private NaoConformidade criarNaoConformidade() {
        return new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado",
                new Inspetor("João"),
                new Setor("Montagem")
        );
    }

    @Test
    void deveIniciarComStatusAberta() {
        NaoConformidade naoConformidade = criarNaoConformidade();

        assertEquals(
                StatusNaoConformidade.ABERTA,
                naoConformidade.getStatus()
        );
    }

    @Test
    void deveIniciarTratamentoQuandoEstiverAberta() {
        NaoConformidade naoConformidade = criarNaoConformidade();

        naoConformidade.iniciarTratamento();

        assertEquals(
                StatusNaoConformidade.EM_TRATAMENTO,
                naoConformidade.getStatus()
        );
    }

    @Test
    void deveImpedirIniciarTratamentoDuasVezes() {
        NaoConformidade naoConformidade = criarNaoConformidade();

        naoConformidade.iniciarTratamento();

        assertThrows(
                IllegalStateException.class,
                () -> naoConformidade.iniciarTratamento()
        );
    }

    @Test
    void deveEncerrarAposFluxoCompleto() {
        NaoConformidade naoConformidade = criarNaoConformidade();

        naoConformidade.iniciarTratamento();
        naoConformidade.enviarParaReinspecao();
        naoConformidade.encerrar();

        assertEquals(
                StatusNaoConformidade.ENCERRADA,
                naoConformidade.getStatus()
        );
    }

    @Test
    void deveVoltarQuandoReinspecaoForReprovada() {
        NaoConformidade naoConformidade = criarNaoConformidade();

        naoConformidade.iniciarTratamento();
        naoConformidade.enviarParaReinspecao();
        naoConformidade.reprovarReinspecao();

        assertEquals(
                StatusNaoConformidade.EM_TRATAMENTO,
                naoConformidade.getStatus()
        );
    }

    @Test
    void deveImpedirEncerramentoForaDaReinspecao() {
        NaoConformidade naoConformidade = criarNaoConformidade();

        assertThrows(
                IllegalStateException.class,
                () -> naoConformidade.encerrar()
        );
    }

    @Test
    void deveRejeitarCodigoPainelEmBranco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new NaoConformidade(
                        "",
                        "Interruptor",
                        "Liga/desliga danificado",
                        new Inspetor("João"),
                        new Setor("Montagem")
                )
        );
    }

    @Test
    void deveRejeitarInspetorNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new NaoConformidade(
                        "PL-01",
                        "Interruptor",
                        "Liga/desliga danificado",
                        null,
                        new Setor("Montagem")
                )
        );
    }

    @Test
    void deveRejeitarSetorNulo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new NaoConformidade(
                        "PL-01",
                        "Interruptor",
                        "Liga/desliga danificado",
                        new Inspetor("João"),
                        null
                )
        );
    }
}