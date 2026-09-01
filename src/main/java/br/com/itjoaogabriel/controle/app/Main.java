package br.com.itjoaogabriel.controle.app;

import br.com.itjoaogabriel.controle.model.NaoConformidade;

public class Main {

    public static void main(String[] args) {

        NaoConformidade naoConformidade = new NaoConformidade(
                "PL-01",
                "Interruptor",
                "Liga/desliga danificado.",
                "Montagem"
        );

        System.out.println(naoConformidade.getCodigoPainel());
        System.out.println(naoConformidade.getComponente());
        System.out.println(naoConformidade.getSetorResponsavel());
        System.out.println(naoConformidade.getDescricao());
        System.out.println(naoConformidade.getDataRegistro());
        System.out.println(naoConformidade.getStatus());
        naoConformidade.iniciarTratamento();
        System.out.println(naoConformidade.getStatus());
        naoConformidade.enviarParaReinspecao();
        System.out.println(naoConformidade.getStatus());
        naoConformidade.reprovarReinspecao();
        System.out.println(naoConformidade.getStatus());
        naoConformidade.enviarParaReinspecao();
        System.out.println(naoConformidade.getStatus());
        naoConformidade.encerrar();
        System.out.println(naoConformidade.getStatus());
        //naoConformidade.reprovarReinspecao();
    }
}
