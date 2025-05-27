package br.com.tasklist;

import java.util.Date;
import java.util.Map;

public class Tarefa {
    private Integer idDaTarefa;
    private boolean check;
    private String nome;
    private String descricao;
    private Map<Integer, String> status;
    private Map<String, Date> datas;

    // Construtor
    public Tarefa(int idDaTarefa, String nome, String descricao, boolean check,
                  Map<Integer, String> status, Map<String, Date> datas) {
        this.nome = nome;
        this.descricao = descricao;
        this.check = check;
        this.status = status;
        this.datas = datas;
        this.idDaTarefa = idDaTarefa;
    }

    // Getters e Setters
    public boolean isCheck() { return check; }
    public void setCheck(boolean check) { this.check = check; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdDaTarefa() { return idDaTarefa; }
    public void setIdDaTarefa(int idDaTarefa) { this.idDaTarefa = idDaTarefa; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Map<Integer, String> getStatus() { return status; }
    public void setStatus(Map<Integer, String> status) { this.status = status; }

    public Map<String, Date> getDatas() { return datas; }
    public void setDatas(Map<String, Date> datas) { this.datas = datas; }
}
