package br.com.itjoaogabriel.controle.app;

import br.com.itjoaogabriel.controle.model.Inspetor;
import br.com.itjoaogabriel.controle.model.NaoConformidade;

public class Main {

    public static void main(String[] args) {

        Inspetor inspetor = new Inspetor("João");

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado.",
                "Montagem",
                inspetor
        );

        System.out.println("Inspetor: " + naoConformidade.getInspetor().getNome());
        System.out.println("Código do painel: " + naoConformidade.getCodigoPainel());
        System.out.println("Componente: " + naoConformidade.getComponente());
        System.out.println("Setor responsável: " + naoConformidade.getSetorResponsavel());
        System.out.println("Descrição: " + naoConformidade.getDescricao());
        System.out.println("Data: " + naoConformidade.getDataRegistro());
        System.out.println("Status: " + naoConformidade.getStatus() + "\n");

        naoConformidade.iniciarTratamento();
        System.out.println("Status: " + naoConformidade.getStatus());
        naoConformidade.enviarParaReinspecao();
        System.out.println("Status: " + naoConformidade.getStatus());
        naoConformidade.reprovarReinspecao();
        System.out.println("Status: " + naoConformidade.getStatus());
        naoConformidade.enviarParaReinspecao();
        System.out.println("Status: " + naoConformidade.getStatus());
        naoConformidade.encerrar();
        System.out.println("Status: " + naoConformidade.getStatus());
    }
}
