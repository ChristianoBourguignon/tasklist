<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gerenciador de Tarefas</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <script>
        const usuario = localStorage.getItem("usuario");
        if (!usuario) {
            window.location.href = "login.html";
        } else {
            document.addEventListener("DOMContentLoaded", () => {
                document.getElementById("nomeUsuario").textContent = usuario;
            });
        }
    </script>
    <h2 class="mb-4">Bem-vindo, <strong id="nomeUsuario"></strong></h2>

    <!-- Formulário de Criação -->
    <div class="card mb-4">
        <div class="card-header">Criar Nova Tarefa</div>
        <div class="card-body">
            <form id="tarefaForm" method="POST">
                <div class="row g-3">
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="nome" placeholder="Nome da Tarefa" required>
                    </div>
                    <div class="col-md-5">
                        <input type="text" class="form-control" id="descricao" placeholder="Descrição" required>
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary w-100">Adicionar Tarefa</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="d-flex flex-wrap justify-content-center gap-2 mb-3">
        <button id="exportEXCEL" class="btn btn-success">
            Exportar para Excel
        </button>
        <button id="exportPDF" class="btn btn-danger">
            Exportar para PDF
        </button>
    </div>

    <!-- Lista de Tarefas -->
    <div id="listaTarefas" class="row row-cols-1 gy-3">
        <!-- Tarefas inseridas dinamicamente aqui -->
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Fechar"></button>
            </div>
            <div class="modal-body bg-white" id="modalBody">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>



<script src="script.js"></script>
</body>
</html>
