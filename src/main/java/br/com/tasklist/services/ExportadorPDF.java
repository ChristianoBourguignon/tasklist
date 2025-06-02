package br.com.tasklist.services;

import br.com.tasklist.Tarefa;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ExportadorPDF {

    public static void exportarParaPDF(Map<Integer, Tarefa> tarefas, OutputStream outputStream, String nomeUsuario) {
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, outputStream);
            documento.open();

            documento.add(new Paragraph("Lista de Tarefas de " + nomeUsuario, FontFactory.getFont(FontFactory.COURIER_BOLD, 18)));
            documento.add(new Paragraph(" ")); // espaço

            PdfPTable tabela = new PdfPTable(6);
            tabela.setWidthPercentage(100);
            tabela.setWidths(new int[]{2, 3, 3, 2, 3, 3});

            adicionarCabecalho(tabela);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            for (Tarefa tarefa : tarefas.values()) {
                tabela.addCell(String.valueOf(tarefa.getIdDaTarefa()));
                tabela.addCell(tarefa.getNome());
                tabela.addCell(tarefa.getDescricao());

                String status = tarefa.getStatus().values().stream().findFirst().orElse("");
                tabela.addCell(status);

                String criacao = tarefa.getDatas().get("dtCriacao") != null ? sdf.format(tarefa.getDatas().get("dtCriacao")) : "";
                String conclusao = tarefa.getDatas().get("dtConcluido") != null ? sdf.format(tarefa.getDatas().get("dtConcluido")) : "";
                tabela.addCell(criacao);
                tabela.addCell(conclusao);
            }

            documento.add(tabela);
            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void adicionarCabecalho(PdfPTable tabela) {
        Font fonte = FontFactory.getFont(FontFactory.COURIER_BOLD);
        tabela.addCell(new PdfPCell(new Phrase("ID", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Nome", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Descrição", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Status", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Criado em", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Concluído em", fonte)));
    }
}

