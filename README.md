# Controle de Não Conformidades

## Objetivo

Este projeto pretende apoiar o controle de não conformidades em contextos industriais que ainda dependem de registros manuais ou de informações distribuídas entre formulários e ferramentas genéricas.

A proposta é construir progressivamente um sistema que centralize o registro, o tratamento, a reinspeção e a rastreabilidade das não conformidades.

## Estado atual

A versão atual funciona somente em memória e permite:

- registrar o código do painel;
- identificar o componente;
- descrever a não conformidade;
- informar o setor responsável;
- registrar automaticamente a data e a hora;
- iniciar toda não conformidade com o status `ABERTA`;
- alterar o status seguindo regras de negócio;
- reprovar uma reinspeção e retornar a não conformidade para tratamento;
- impedir transições incompatíveis com o estado atual;
- rejeitar campos obrigatórios nulos ou em branco.

## Fluxo atual

```text
ABERTA
→ EM_TRATAMENTO
→ AGUARDANDO_REINSPECAO
→ ENCERRADA