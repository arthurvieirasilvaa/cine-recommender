package com.arthurvieira.cinerecommender.ui;

public class ConsoleMenu {
    public void showMainMenu() {
        System.out.println("\n########### Cine Recommender ###########");
        System.out.println("1 - Gerenciar Conteúdos");
        System.out.println("2 - Recomendações");
        System.out.println("3 - Usuários");
        System.out.println("0 - Sair");
        System.out.println("------------------------------");
        System.out.println("Digite uma opção: ");
    }

    public void showContentMenu() {
        System.out.println("\n============== Gerenciar Conteúdos ==============");
        System.out.println("1 - Cadastrar Filme");
        System.out.println("2 - Cadastrar Série");
        System.out.println("3 - Avaliar conteúdo");
        System.out.println("4 - Listar Todos");
        System.out.println("5 - Buscar por ID");
        System.out.println("6 - Filtrar por Gênero");
        System.out.println("7 - Filtrar por Tipo");
        System.out.println("-1 - Voltar");
        System.out.println("------------------------------");
        System.out.println("Digite uma opção: ");
    }

    public void showRecommendationsMenu() {
        System.out.println("\n============== Recomendações ==============");
        System.out.println("1 - Top 10 melhores avaliados");
        System.out.println("2 - Recomendar por gênero favorito");
        System.out.println("3 - Recomendar baseado em histórico do usuário");
        System.out.println("-1 - Voltar");
        System.out.println("------------------------------");
        System.out.println("Digite uma opção: ");
    }

    public void showUserMenu() {
        System.out.println("\n============== Usuários ==============");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Listar Usuários");
        System.out.println("3 - Buscar Usuário por ID");
        System.out.println("4 - Remover Usuário");
        System.out.println("5 - Ver Histórico de Avaliações");
        System.out.println("-1 - Voltar");
        System.out.println("------------------------------");
        System.out.println("Digite uma opção: ");
    }
}
