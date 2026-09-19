# Diário de desenvolvimento

## 01/09/2026

- **O que fiz:** revisei o modelo de não conformidade, corrigi a reprovação da reinspeção, testei os caminhos válidos e inválidos, validei o projeto com Maven e iniciei a documentação.
- **Onde tive dificuldade:** precisei relembrar a diferença entre `Long` e `long` e identificar que `reprovarReinspecao()` ainda repetia parte do comportamento de `encerrar()`.
- **O que aprendi:** entendi melhor como o objeto protege as transições de status, como `null` representa um objeto ainda não persistido e como o stage pode conter uma versão diferente do arquivo atual.

## 19/09/2026

- **O que fiz:** criei e executei o esquema do banco `controle_nao_conformidades` pelo Query Tool do pgAdmin, criando as tabelas `inspetor`, `setor`, `nao_conformidade` e `historico_status` com suas chaves, relacionamentos, validações e índices.
- **Onde tive dificuldade:** o roteiro utilizava comandos do `psql`, mas estou administrando o PostgreSQL pelo pgAdmin e precisei adaptar a execução para o Query Tool.
- **O que aprendi:** entendi que o pgAdmin e o `psql` são interfaces diferentes para trabalhar com o mesmo PostgreSQL e que executar o esquema sem erros confirma a criação das constraints, mas o comportamento delas ainda precisa ser comprovado com operações válidas e inválidas.