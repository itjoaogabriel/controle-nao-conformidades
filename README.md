# Controle de Não Conformidades

## Objetivo

Este projeto pretende apoiar o controle de não conformidades em contextos
industriais que ainda dependem de registros manuais ou de informações
distribuídas entre formulários e ferramentas genéricas.

A proposta é construir progressivamente um sistema que centralize o registro,
o tratamento, a reinspeção e a rastreabilidade das não conformidades.

## Estado atual

A versão atual possui um modelo de domínio em Java que permite:

- cadastrar inspetores e setores;
- registrar o código do painel, o componente e a descrição da não conformidade;
- associar a não conformidade a um inspetor e a um setor de origem;
- registrar automaticamente a data e a hora;
- iniciar toda não conformidade com o status `ABERTA`;
- controlar as transições de status por meio de regras de negócio;
- reprovar uma reinspeção e retornar a não conformidade para tratamento;
- impedir transições incompatíveis com o estado atual;
- representar o histórico das mudanças de status;
- rejeitar valores obrigatórios nulos, vazios ou inválidos;
- reconstruir objetos que futuramente serão recuperados do banco;
- verificar as regras do modelo com testes automatizados em JUnit.

O esquema relacional já foi criado e executado no PostgreSQL. A aplicação Java
ainda não realiza a persistência dos objetos, pois a integração por JDBC será
implementada no próximo marco.

## Modelo atual

- `Inspetor`: representa o inspetor cadastrado e seu estado ativo.
- `Setor`: representa o setor de origem da não conformidade.
- `NaoConformidade`: concentra os dados e as regras de mudança de status.
- `HistoricoStatus`: representa uma transição de status já ocorrida.
- `StatusNaoConformidade`: define os status permitidos no processo.

## Fluxo de status

```text
ABERTA
   ↓
EM_TRATAMENTO
   ↓
AGUARDANDO_REINSPECAO
   ├── aprovada  → ENCERRADA
   └── reprovada → EM_TRATAMENTO