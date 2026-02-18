# 🎬 Cine Recommender

## 📌 Descrição

O **Cine Recommender** é um projeto backend desenvolvido
em Java, com foco em arquitetura limpa, organização em camadas,
uso avançado de coleções, manipulação de arquivos e implementação
de algoritmo de um recomendação.

O objetivo do projeto é simular a lógica real de recomendação
de um sistema de streaming.

## 🛠️ Tecnologias Utilizadas

- Java;
- Intellij IDEA;
- Git;
- GitHub.

## 🧠 Conceitos de Programação Utilizados

- Programação Orientada a Objetos (POO);
- Collections Framework;
- Enum;
- Manipulação de Arquivos (I/O e NIO);
- Exceções Customizadas;
- Expressões Regulares (Regex);
- Java Time;
- Algoritmo de Recomendação;
- Separação de responsabilidade (arquitetura em camadas);
- Validações de regras de negócios.

## ⚙️ Funcionalidades

### 🎥 Filmes e Séries
- Cadastro;
- Edição;
- Remoção;
- Listagem com filtros;
- Cálculo automático da média das notas.

### 👤 Usuários
- Cadastro;
- Edição;
- Remoção;
- Validação de email via Regex;
- Controle de histórico de avaliações.

### ⭐ Sistema de Avaliações
- Avaliação de 1 a 5;
- Atualização automática da média;
- Restrição de avaliação única por usuário.


### 🤖 Algoritmo de Recomendação
- Identificação dos gêneros preferidos;
- Cálculo de afinidade do usuário;
- Score ponderado.

## 🧱 Arquitetura do Projeto

O projeto está organizado em camadas, seguindo boas práticas de design:

- **app:** inicia a aplicação na classe `Main`;
- **domain:** entidades principais do sistema;
- **exception:** exceções customizadas do sistema;
- **repository:** persistência dos dados em arquivos `.txt`;
- **service:** regras de negócio e validações;
- **ui:** interação com o usuário via console;
- **util:** classes auxiliares reutilizáveis.

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 17 (ou superior);
- Intellij IDEA (ou outra IDE Java);
- Git.

### Passos

1. Clonar o repositório:

```
git clone https://github.com/arthurvieirasilvaa/sistema-bancario.git
```

2. Abra o projeto no Intellij IDEA
3. Execute a classe: br.com.arthurvieira.cinerecommender.app.Main