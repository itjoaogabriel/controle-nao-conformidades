package br.com.itjoaogabriel.controle.app;

import br.com.itjoaogabriel.controle.model.Inspetor;
import br.com.itjoaogabriel.controle.model.NaoConformidade;
import br.com.itjoaogabriel.controle.model.Setor;

public class Main {

    public static void main(String[] args) {
        Inspetor inspetor = new Inspetor("João");
        Setor setor = new Setor("Montagem");

        NaoConformidade naoConformidade =
                new NaoConformidade(
                        "PL-01",
                        "Interruptor",
                        "Liga/desliga danificado",
                        inspetor,
                        setor
                );

        System.out.println(
                "Inspetor: " + naoConformidade.getInspetor().getNome()
        );
        System.out.println(
                "Setor de origem: " + naoConformidade.getSetorOrigem().getNome()
        );
        System.out.println(
                "Status: " + naoConformidade.getStatus()
        );

        naoConformidade.iniciarTratamento();

        System.out.println(
                "Status: " + naoConformidade.getStatus()
        );
    }
}