package br.com.tasklist.services;

import br.com.tasklist.Tarefa;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ExportadorExcel {

    public static void exportarParaExcel(Map<Integer, Tarefa> tarefas, OutputStream outputStream, String nomeUsuario) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Tarefas de " + nomeUsuario);

            String[] colunas = {"ID", "Nome", "Descrição", "Status", "Criado em", "Concluído em"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < colunas.length; i++) {
                header.createCell(i).setCellValue(colunas[i]);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            int rowNum = 1;
            for (Tarefa tarefa : tarefas.values()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(tarefa.getIdDaTarefa());
                row.createCell(1).setCellValue(tarefa.getNome());
                row.createCell(2).setCellValue(tarefa.getDescricao());
                String status = tarefa.getStatus().values().stream().findFirst().orElse("");
                row.createCell(3).setCellValue(status);

                String criacao = tarefa.getDatas().get("dtCriacao") != null ? sdf.format(tarefa.getDatas().get("dtCriacao")) : "";
                String conclusao = tarefa.getDatas().get("dtConcluido") != null ? sdf.format(tarefa.getDatas().get("dtConcluido")) : "";

                row.createCell(4).setCellValue(criacao);
                row.createCell(5).setCellValue(conclusao);
            }

            for (int i = 0; i < colunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
