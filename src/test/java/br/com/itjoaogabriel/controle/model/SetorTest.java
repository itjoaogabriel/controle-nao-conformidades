package br.com.itjoaogabriel.controle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SetorTest {

    @Test
    void deveCriarSetorNovoSemIdEAtivo() {
        Setor setor = new Setor("Montagem");

        assertNull(setor.getId());
        assertEquals("Montagem", setor.getNome());
        assertTrue(setor.isAtivo());
    }

    @Test
    void deveRemoverEspacosExternosDoNome() {
        Setor setor = new Setor("  Montagem  ");

        assertEquals("Montagem", setor.getNome());
    }

    @Test
    void deveDesativarSetor() {
        Setor setor = new Setor("Montagem");

        setor.desativar();

        assertFalse(setor.isAtivo());
    }

    @Test
    void deveRestaurarSetorExistente() {
        Setor setor = Setor.restaurar(
                1L,
                "Montagem",
                false
        );

        assertEquals(1L, setor.getId());
        assertEquals("Montagem", setor.getNome());
        assertFalse(setor.isAtivo());
    }

    @Test
    void deveRejeitarNomeEmBranco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Setor("   ")
        );
    }

    @Test
    void deveRejeitarIdInvalidoNaRestauracao() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Setor.restaurar(
                        0L,
                        "Montagem",
                        true
                )
        );
    }
}