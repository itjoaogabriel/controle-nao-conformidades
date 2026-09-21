package br.com.itjoaogabriel.controle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InspetorTest {

    @Test
    void deveCriarInspetorNovoSemIdEAtivo() {
        Inspetor inspetor = new Inspetor("João");

        assertNull(inspetor.getId());
        assertEquals("João", inspetor.getNome());
        assertTrue(inspetor.isAtivo());
    }

    @Test
    void deveDesativarInspetor() {
        Inspetor inspetor = new Inspetor("João");

        inspetor.desativar();

        assertFalse(inspetor.isAtivo());
    }

    @Test
    void deveRestaurarInspetorExistente() {
        Inspetor inspetor = Inspetor.restaurar(
                1L,
                "João",
                false
        );

        assertEquals(1L, inspetor.getId());
        assertEquals("João", inspetor.getNome());
        assertFalse(inspetor.isAtivo());
    }

    @Test
    void deveRejeitarNomeEmBranco() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Inspetor("   ")
        );
    }
}