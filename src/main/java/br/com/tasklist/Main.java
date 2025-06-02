package br.com.tasklist;

import br.com.tasklist.services.ExportadorExcel;
import br.com.tasklist.services.ExportadorPDF;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class Main extends HttpServlet {
    private static final TarefasController tarefas = new TarefasController();

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
        while ((line = re.readLine()) != null) {
            sb.append(line);
        }
        Gson gson = new Gson();
        String operacao = "";
        if(!sb.toString().isEmpty()) {
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);

            // Acessando os campos individualmente. // bug: chamando o list e depois add
            operacao = jsonObject.get("operacao").getAsString();
            System.out.println("Operação: " + operacao);
        }
        if (operacao.equals("add")) {

            //operacao para criar a tarefa

            // Converte JSON para objeto Java
            System.out.println("Recebido: " + sb.toString());
            System.out.println("Convertido a objeto: " + gson.toJson(sb));
            Tarefa tarefa = gson.fromJson(sb.toString(), Tarefa.class);
            tarefas.criarTarefa(tarefa.getNome(), tarefa.getDescricao());

        } else if (operacao.equals("edit")) {

            //operacao para editar/atualizar a tarefa
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            int idTarefa = jsonObject.get("idDaTarefa").getAsInt();
            String nomeTarefa = jsonObject.get("nome").getAsString();
            String descricaoTarefa = jsonObject.get("descricao").getAsString();
            int statusTarefa = jsonObject.get("status").getAsInt();
            System.out.println("Recebido: " + idTarefa);
            tarefas.atualizarTarefa(idTarefa, statusTarefa, nomeTarefa, descricaoTarefa);


        } else if (operacao.equals("delete")) {

            //operacao para deletar a tarefa
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            int idTarefa = jsonObject.get("idDaTarefa").getAsInt();
            System.out.println("Recebido: " + idTarefa);
            tarefas.removerTarefa(idTarefa);


        } else if (operacao.equals("list")) {

            //operacao para exibir a tarefa

            String json = gson.toJson(tarefas.getTarefas());
            System.out.println("Recebido: " + json);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(json);
            tarefas.exibirTarefas();

        } else if (operacao.equals("exportPDF")) {
            // Exportar PDF
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            String nomeUsuario = jsonObject.get("nomeUsuario").getAsString();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"tarefas_usuario.pdf\"");
            ExportadorPDF.exportarParaPDF(tarefas.getTarefas(), response.getOutputStream(), nomeUsuario);
        }  else if (operacao.equals("exportExcel")) {
            // Exportar EXCEL
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            String nomeUsuario = jsonObject.get("nomeUsuario").getAsString();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=\"tarefas_usuario.xlsx\"");
            ExportadorExcel.exportarParaExcel(tarefas.getTarefas(), response.getOutputStream(), nomeUsuario);
        }
    }
}
