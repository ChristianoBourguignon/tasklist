<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gerenciador de Tarefas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%
    String contextPath = request.getContextPath();
%>

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

    <!-- Lista de Tarefas -->
    <div id="listaTarefas" class="row row-cols-1 gy-3">
        <!-- Tarefas inseridas dinamicamente aqui -->
    </div>
</div>

<script>
    const form = document.getElementById("tarefaForm");
    const contextPath = "<%= contextPath %>";
    const listaTarefas = document.getElementById("listaTarefas");
    const statusOpcoes = ["Pendente", "Em andamento", "Concluído"];

    form.addEventListener("submit", function (e) {
        e.preventDefault();
        const nome = document.getElementById("nome").value;
        const descricao = document.getElementById("descricao").value;
        const dataCriacao = new Date().toLocaleString("pt-BR");
        criarTarefaFront(nome, descricao, dataCriacao);
        criarTarefaBack(nome,descricao,dataCriacao);
        form.reset();
    });

    function criarTarefaFront(nome, descricao, dataCriacao) {
        const card = document.createElement("div");
        card.className = "col";
        card.innerHTML = `
            <div class="card shadow-sm">
                <div class="card-body">
                    <div class="d-flex align-items-center mb-2">
                        <input type="checkbox" class="form-check-input me-2 tarefa-check">
                        <small class="text-muted ms-auto">Criada em: ${dataCriacao}</small>
                    </div>
                    <input type="text" class="form-control mb-2 tarefa-nome" value="${nome}">
                    <textarea class="form-control mb-2 tarefa-desc">${descricao}</textarea>
                    <div class="d-flex align-items-center">
                        <select class="form-select tarefa-status me-2">
                            ${statusOpcoes.map(s => `<option value="${s}">${s}</option>`).join("")}
                        </select>
                        <button class="btn btn-danger btn-sm me-2 remover-btn">Remover</button>
                    </div>
                </div>
            </div>
        `;

        const check = card.querySelector(".tarefa-check");
        const nomeInput = card.querySelector(".tarefa-nome");
        const descInput = card.querySelector(".tarefa-desc");
        const statusSelect = card.querySelector(".tarefa-status");

        // Evento de checkbox
        check.addEventListener("change", () => {
            const checked = check.checked;
            nomeInput.readOnly = checked;
            descInput.readOnly = checked;
            statusSelect.disabled = checked;

            if (checked) {
                statusSelect.value = "Concluído";
            } else {
                statusSelect.value = "Pendente";
            }
        });

        // Evento de remover
        card.querySelector(".remover-btn").addEventListener("click", () => {
            card.remove();
        });

        listaTarefas.appendChild(card);
    }
    function criarTarefaBack(nome,descricao,dataCriacao){
        fetch(contextPath +"/tarefas", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                nome: nome,
                descricao: descricao,
                dtCriacao: dataCriacao
            })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erro ao enviar os dados.");
                }
                return response.json();
            })
            .then(data => {
                console.log("Tarefa recebidas:", data);
            })
            .catch(err => {
                console.error("Erro ao criar as tarefas:", err);
                alert(`Ocorreu um erro ao inserir a tarefa`);
            });
    }
</script>
</body>
</html>
