package controllers;

public class main {

    public static void main(String[] args) {
        TarefasController tarefa1 = new TarefasController();
        tarefa1.criarTarefa("Christiano","Bourguignon");
        tarefa1.criarTarefa("Pedro","Burguignon");
        tarefa1.exibirTarefas();

    }

}
