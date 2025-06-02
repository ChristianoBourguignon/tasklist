const form = document.getElementById("tarefaForm");
const listaTarefas = document.getElementById("listaTarefas");

form.addEventListener("submit", function (e) {
    e.preventDefault();
    const nome = document.getElementById("nome").value;
    const descricao = document.getElementById("descricao").value;
    const dataCriacao = new Date().toLocaleString("pt-BR");
    criarTarefa(nome, descricao, dataCriacao);
    form.reset();
});

function exibirTarefas() {
    $.ajax({
        url: "/tarefas",
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        data: JSON.stringify({ operacao: "list" }),
        success: data => {
            listaTarefas.innerHTML = ""; // Evita duplicação

            Object.values(data).forEach(tarefa => {
                const card = document.createElement("div");
                card.className = "col";
                card.id = tarefa['idDaTarefa'];

                const statusAtual = Object.keys(tarefa.status)[0]; // "1", "2" ou "3"
                const checked = statusAtual === "3"; // "Concluído"
                const dataCriacao = tarefa.datas?.dtCriacao ?? "Sem data";

                card.innerHTML = `
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <div class="d-flex align-items-center mb-2">
                                <input type="checkbox" class="form-check-input me-2 tarefa-check" ${checked ? "checked" : ""}>
                                <small class="text-muted ms-auto">Criada em: ${dataCriacao}</small>
                            </div>
                            <input type="text" class="form-control mb-2 tarefa-nome" value="${tarefa.nome}" ${checked ? "readonly" : ""}>
                            <textarea class="form-control mb-2 tarefa-desc" ${checked ? "readonly" : ""}>${tarefa.descricao}</textarea>
                            <div class="d-flex align-items-center">
                                <select class="form-select tarefa-status me-2" ${checked ? "disabled" : ""}>
                                    <option value="1" ${statusAtual === "1" ? "selected" : ""}>Pendente</option>
                                    <option value="2" ${statusAtual === "2" ? "selected" : ""}>Em andamento</option>
                                    <option value="3" ${statusAtual === "3" ? "selected" : ""}>Concluído</option>
                                </select>
                                <button class="btn btn-danger btn-sm me-2 remover-btn">Remover</button>
                            </div>
                        </div>
                    </div>
                `;

                listaTarefas.appendChild(card);

                // Captura elementos do card
                const check = card.querySelector(".tarefa-check");
                const nomeInput = card.querySelector(".tarefa-nome");
                const descInput = card.querySelector(".tarefa-desc");
                const statusSelect = card.querySelector(".tarefa-status");
                const removerBtn = card.querySelector(".remover-btn");

                // Atualiza check automaticamente se status mudar para "3"
                statusSelect.addEventListener("change", () => {
                    const novoStatus = statusSelect.value;
                    const isConcluido = novoStatus === "3";
                    if(isConcluido){
                        check.checked = isConcluido;
                        nomeInput.readOnly = isConcluido;
                        descInput.readOnly = isConcluido;
                        statusSelect.disabled = isConcluido;
                    }

                    // Envia para o backend
                    atualizarTarefa(tarefa.idDaTarefa, novoStatus,nomeInput.value,descInput.value);
                });

                // Se clicar no check, muda o status automaticamente
                check.addEventListener("change", () => {
                    const isChecked = check.checked;
                    const novoStatus = isChecked ? "3" : "1";

                    statusSelect.value = novoStatus;
                    nomeInput.readOnly = isChecked;
                    descInput.readOnly = isChecked;
                    statusSelect.disabled = isChecked;

                    atualizarTarefa(tarefa.idDaTarefa, novoStatus,nomeInput.value,descInput.value);
                });

                removerBtn.addEventListener("click", () => {
                    removerTarefa(tarefa.idDaTarefa);
                    card.remove();
                });
            });
        },
        error: (xhr, status, error) => {
            console.error("Erro ao carregar as tarefas:", error, status);
            alert("Ocorreu um erro ao buscar as tarefas");
        }
    });
}

function criarTarefa(nome, descricao, dataCriacao) {
    $.ajax({
        url:"/tarefas",
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        data: JSON.stringify({
            nome: nome,
            descricao: descricao,
            dtCriacao: dataCriacao,
            operacao: "add"
        }),
        success: () => {
            mostrarModal("Sucesso","Tarefa criada com sucesso!",200);
            exibirTarefas();
        },
        error: (xhr,status,error) => {
            console.error("Erro ao criar as tarefas: " + error + "/" + status + "/" + xhr);
            mostrarModal("Error","Erro ao criar uma tarefa!",404);
            alert(`Ocorreu um erro ao inserir a tarefa`);

        }
    });

}

function mostrarModal(title,message,statusCode){
    let modalClass="";
    if (statusCode === 200) {
        modalClass = 'bg-success';
    } else if (statusCode === 404) {
        modalClass = 'bg-danger';
    } else {
        modalClass = 'bg-warning';
    }
    $('#modalLabel').text(title);
    $('#modalBody').html(message).addClass("bg-white text-black");
    $('.modal-content').addClass(modalClass + "  text-white");
    const modal = new bootstrap.Modal(document.getElementById('modal'));
    modal.show();
}

function atualizarTarefa(idDaTarefa, novoStatus,novoNome,novaDescricao) {
    $.ajax({
        url: "/tarefas",
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        data: JSON.stringify({
            operacao: "edit",
            idDaTarefa,
            status: novoStatus,
            nome: novoNome,
            descricao: novaDescricao
        }),
        success: () => {
            // mostrarModal("Sucesso","Tarefa atualizada com sucesso!",200);
            console.log("Tarefa atualizada");
        },
        error: (xhr, status, error) => {
            console.error("Erro ao atualizar status:", error, status);
            alert("Erro ao atualizar status da tarefa");
        }
    });
}

function removerTarefa(idDaTarefa) {
    $.ajax({
        url: "/tarefas",
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        data: JSON.stringify({
            operacao: "delete",
            idDaTarefa: idDaTarefa
        }),
        success: () => {
            mostrarModal("Sucesso","Tarefa removida com sucesso!",200);
        },
        error: (xhr, status, error) => {
            console.error("Erro ao remover tarefa:", error, status);
            alert("Erro ao remover tarefa");
        }
    });
}

$("#exportPDF").off("click").on("click", () => {
    const nomeUsuario = localStorage.getItem("usuario");
    const blob = new Blob([JSON.stringify({
        operacao: "exportPDF",
        nomeUsuario: nomeUsuario
    })], { type: "application/json" });

    fetch("/tarefas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: blob
    })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = url;
            a.download = "tarefas_" + nomeUsuario +".pdf";
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(console.error);
});
$("#exportEXCEL").off("click").on("click", () => {
    const nomeUsuario = localStorage.getItem("usuario");

    fetch("/tarefas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            operacao: "exportExcel",
            nomeUsuario: nomeUsuario
        })
    })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = url;
            a.download = "tarefas_" + nomeUsuario +".xlsx";
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(console.error);
});
