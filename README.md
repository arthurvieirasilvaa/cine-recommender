# 🎬 Cine Recommender

## 📌 Descrição

O **Cine Recommender** é um projeto backend desenvolvido
em Java que simula a inteligência de uma plataforma de streaming.
O projeto se destaca por uma arquitetura em camadas, algoritmos de
recomendação personalizados e um sistema de persistência em arquivos
com sincronização de integridade referencial.

## 🛠️ Tecnologias Utilizadas

- Java;
- Intellij IDEA;
- Git;
- GitHub.

## 🧠 Diferenciais Técnicos

- **Algoritmos de Recomendação:** implementação de filtros por afinidade, 
gêneros favoritos e *Item-based Collaborative Filtering*
(quem viu este, também viu);
- **Persistência Genérica (`.txt`):** criação de um *FileRepository<T>*
abstrato genérico que gerencia a persistência de dados;
- **Sincronização de Memória:** sistema de sincronização para garantir
que o *Rating* aponte para a mesma referência de memória do *User* e do
*Content*;
- **Programação Funcional:** uso intensivo de Streams, Lambdas e *Method Reference*
para filtragem e processamento de dados complexos.

## ⚙️ Funcionalidades

### 🎥 Filmes e Séries

O sistema permite a administração completa do catálogo de mídia:

- **Cadastro de Filmes:** registro com título, ano de lançamento, gênero, classificação
indicativa e duração em minutos;
- **Cadastro de Séries:** registro com título, ano de lançamento, gênero, classificação
indicativa e detalhes específicos (número de temporadas e total de episódios);
- **Busca Especializada:** localização de conteúdos por ID único;
- **Filtros de Catálogo:** listagem dinâmica filtrando por gênero, tipo ou classificação
indicativa;
- **Remoção com Integridade:** exclusão de conteúdos do sistema com tratamento de erros
para IDs inexistentes.

### 👤 Usuários

Controle de perfis que interagem com o sistema:

- **Cadastro do Usuário:** registro de nome e e-mail (com validação de formato via Regex);
- **Gestão de Perfis:** listagem de todos os usuários cadastrados e busca individual por ID;
- **Histórico de Interação:** consulta imediata de todas as avaliações feitas por um usuário específico.

### ⭐ Sistema de Avaliações

O núcleo dos dados para o algoritmo de recomendação:

- **Avaliar Conteúdo:** atribuição de notas de 1 a 5 estrelas para qualquer conteúdo do catálogo;
- **Atualização Inteligente:** caso um usuário avalie um conteúdo que já havia avaliado antes, o
sistema identifica e **atualiza** a nota antiga em vez de criar uma duplicata;
- **Cálculo de Média Global:** o sistema recalcula automaticamente a média de cada conteúdo com base
em todas as avaliações recebidas.

### 🤖 Algoritmo de Recomendação

O sistema oferece cinco abordagens de recomendação de conteúdo:

1. **Melhores avaliados:** os 10 conteúdos com melhor média de avaliação;
2. **Gênero favorito:** analisa o histórico do usuário para identificar o
   gênero mais assistido e recomenda conteúdos não vistos desse gênero;
3. **Histórico do usuário:** recomendação baseada na afinidade de gêneros
   e classificação indicativa de títulos avaliados com mais de 4 estrelas;
4. **Lançamentos e novidades:** prioriza os lançamentos mais recentes com
   melhor recepção do público;
5. **Quem viu este, também viu:** analisa quais outros conteúdos foram
   bem avaliados por usuários que gostaram do conteúdo selecionado.

## 🧱 Arquitetura do Projeto

O projeto está organizado em camadas, seguindo boas práticas de design:

- **app:** inicia a aplicação na classe `Main`;
- **controller:** gerenciamento de fluxo e ponte entre a interface e os serviços;
- **domain:** entidades principais do sistema;
- **exception:** exceções customizadas do sistema;
- **repository:** persistência dos dados em arquivos `.txt`;
- **service:** regras de negócio e validações;
- **ui:** interação com o usuário via console;
- **util:** utilitários de validação (Regex, Range, Null-checks).

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 17 (ou superior);
- Intellij IDEA (ou outra IDE Java);
- Git.

### Passos

1. Clonar o repositório:

```
git clone https://github.com/arthurvieirasilvaa/cine-recommender.git
```

2. Abra o projeto no Intellij IDEA;
3. Execute a classe: com.arthurvieira.cinerecommender.app.Main.
