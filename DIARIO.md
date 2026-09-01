# Diário de desenvolvimento

## 01/09/2026

- **O que fiz:** revisei o modelo de não conformidade, corrigi a reprovação da reinspeção, testei os caminhos válidos e inválidos, validei o projeto com Maven e iniciei a documentação.
- **Onde tive dificuldade:** precisei relembrar a diferença entre `Long` e `long` e identificar que `reprovarReinspecao()` ainda repetia parte do comportamento de `encerrar()`.
- **O que aprendi:** entendi melhor como o objeto protege as transições de status, como `null` representa um objeto ainda não persistido e como o stage pode conter uma versão diferente do arquivo atual.