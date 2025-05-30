package br.com.tasklist;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        criarTarefa(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Primeira Servlet</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Primeira Servlet</h1>");
        writer.println("</body>");
        writer.println("</html>");
    }

    public void criarTarefa(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        BufferedReader re = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = re.readLine()) != null) { sb.append(line); }
        Gson gson = new Gson();
        if(!sb.toString().isEmpty()) {
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);

            // Acessando os campos individualmente. // bug: chamando o list e depois add
            String operacao = jsonObject.get("operacao").getAsString();
            System.out.println("Operação: " + operacao);
        }
//        if (operacao.equals("add")) {
//
//            //operacao para criar a tarefa
//
//            BufferedReader reader = request.getReader();
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//                System.out.println(line);
//            }
//            // Converte JSON para objeto Java
//            Gson gson = new Gson();
//            System.out.println("Recebido: " + sb.toString());
//            System.out.println("Convertido a objeto: " + gson.toJson(sb));
//            Tarefa tarefa = gson.fromJson(sb.toString(), Tarefa.class);
//            TarefasController tarefas = new TarefasController();
//            tarefas.criarTarefa(tarefa.getNome(), tarefa.getDescricao());
//
//
//        } else if (operacao.equals("edit")) {
//
//            //operacao para editar/atualizar a tarefa
//
//
//        } else if (operacao.equals("delete")) {
//
//            //operacao para deletar a tarefa
//
//
//        } else if (operacao.equals("list")) {
//
//            //operacao para exibir a tarefa
//            TarefasController tarefas = new TarefasController();
//            Gson gson = new Gson();
//            String json = gson.toJson(tarefas.getTarefas());
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.setStatus(HttpServletResponse.SC_OK);
//            response.getWriter().write(json);
//
//        }
    }
}
