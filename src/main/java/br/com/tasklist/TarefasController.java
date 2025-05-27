package br.com.tasklist;

import java.util.*;

public class TarefasController {
    private final Map<Integer, Tarefa> tarefas;
    private Integer id = 0;
    private Integer tamanho = 10;

    public TarefasController() {
        this.tarefas = new HashMap<>();
    }

    public void criarTarefa(String nome, String descricao) {
        id++;
        if(id>=tamanho) {
            tamanho++;
        }
        Map<Integer, String> status = new HashMap<>();
        status.put(1, "Pendente");
        Map<String, Date> datas = new HashMap<>();
        datas.put("dtCriacao", new Date());
        Tarefa task = new Tarefa(id,nome, descricao, false, status, datas);
        if (!tarefas.containsKey(id)) {
            tarefas.put(id, task);
            System.out.println("Tarefa criada com sucesso");
        }
    }

    public Map<Integer, Tarefa> getTarefas() {
        return tarefas;
    }
    public Tarefa getTarefaPorId(int idTarefa) {
        return tarefas.get(idTarefa);
    }

    public void exibirTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }

        for (Map.Entry<Integer, Tarefa> entry : tarefas.entrySet()) {
            Integer id = entry.getKey();
            Tarefa tarefa = entry.getValue();

            System.out.println("===== Tarefa ID: " + id + " =====");
            System.out.println("Nome: " + tarefa.getNome());
            System.out.println("Descrição: " + tarefa.getDescricao());
            System.out.println("Concluída: " + (tarefa.isCheck() ? "Sim" : "Não"));

            System.out.println("Status:");
            for (Map.Entry<Integer, String> statusEntry : tarefa.getStatus().entrySet()) {
                System.out.println("  [" + statusEntry.getKey() + "] " + statusEntry.getValue());
            }

            System.out.println("Datas:");
            for (Map.Entry<String, Date> dataEntry : tarefa.getDatas().entrySet()) {
                System.out.println("Data de Criação: " + dataEntry.getValue());
            }

            System.out.println();
        }
    }

}
