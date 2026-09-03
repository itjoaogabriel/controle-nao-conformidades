package br.com.itjoaogabriel.controle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NaoConformidadeTest {

    @Test
    void deveIniciarComStatusAberta() {
        Inspetor inspetor = new Inspetor("João");

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado",
                "Montagem",
                inspetor
        );

        assertEquals(
                StatusNaoConformidade.ABERTA,
                naoConformidade.getStatus()
        );
    }

    @Test
    void deveIniciarTratamentoQuandoStatusEstiverAberta() {
        Inspetor inspetor = new Inspetor("João");

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado",
                "Montagem",
                inspetor
        );

        naoConformidade.iniciarTratamento();

        assertEquals(
                StatusNaoConformidade.EM_TRATAMENTO,
                naoConformidade.getStatus()
        );
    }

    @Test
    void deveImpedirIniciarTratamentoDuasVezes() {
        Inspetor inspetor = new Inspetor("João");

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado",
                "Montagem",
                inspetor
        );

        naoConformidade.iniciarTratamento();

        assertThrows(
                IllegalStateException.class,
                () -> naoConformidade.iniciarTratamento()
        );
    }

    @Test
    void deveEncerrarAposFluxoCompleto() {
        Inspetor inspetor = new Inspetor("João");

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado",
                "Montagem",
                inspetor
        );

        naoConformidade.iniciarTratamento();
        naoConformidade.enviarParaReinspecao();
        naoConformidade.encerrar();

        assertEquals(
                StatusNaoConformidade.ENCERRADA,
                naoConformidade.getStatus()
        );
    }

    @Test
    void deveVoltarParaEmTratamentoQuandoReinspecaoForReprovada() {
        Inspetor inspetor = new Inspetor("João");

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado",
                "Montagem",
                inspetor
        );

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
        Inspetor inspetor = new Inspetor("João");

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado",
                "Montagem",
                inspetor
        );

        assertThrows(
                IllegalStateException.class,
                () -> naoConformidade.encerrar()
        );
    }

    @Test
    void deveRejeitarCodigoPainelEmBranco() {
        Inspetor inspetor = new Inspetor("João");

        assertThrows(
                IllegalArgumentException.class,
                () -> new NaoConformidade(
                        "",
                        "Interruptor",
                        "Liga/desliga danificado",
                        "Montagem",
                        inspetor
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
                        "Montagem",
                        null
                )
        );
    }
}