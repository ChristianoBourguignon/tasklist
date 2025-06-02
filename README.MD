# 📋 Lista de Tarefas Compartilhável

Projeto acadêmico desenvolvido para a disciplina de **Desenvolvimento de Sistemas**, com foco em colaboração entre usuários e exportação de tarefas em formatos **PDF** e **Excel**.

## ✨ Funcionalidades

- ✅ Criação, edição e remoção de tarefas
- 👥 Compartilhamento de listas entre usuários
- 📤 Exportação da lista de tarefas para:
  - PDF (utilizando biblioteca **iText**)
  - Excel (utilizando biblioteca **Apache POI**)
  
## 🔧 Tecnologias Utilizadas

- Java 14
- Maven
- Jetty (servidor web)
- Biblioteca [iText PDF](https://kb.itextpdf.com/home/it7kb/examples/itext-7-jump-start-tutorial-for-java)
- Biblioteca [Apache POI](https://poi.apache.org/) (para geração de arquivos `.xlsx`)
- Gson (para manipulação de JSON)
- IDE: IntelliJ IDEA / Eclipse / VS Code

## 📦 Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/ChristianoBourguignon/tasklist.git
   cd tasklist
   ```

2. Certifique-se de ter o Java 14 e Maven instalados

3. Execute os seguintes comandos:
   ```bash
   mvn clean install
   mvn jetty:run
   ```

4. Acesse `http://localhost:8080` no seu navegador

## 📄 Estrutura do Projeto

```
📦 src
 ┣ 📂 main
 ┃ ┣ 📂 java
 ┃ ┃ ┗ 📂 br.com.tasklist
 ┃ ┃   ┣ 📂 services
 ┃ ┃   ┣ 📜 Main.java
 ┃ ┃   ┣ 📜 Tarefa.java
 ┃ ┃   ┗ 📜 TarefasController.java
 ┃ ┣ 📂 resources
 ┃ ┗ 📂 webapp
```

## 📑 Créditos

Desenvolvido por:

- **Christiano Bourguignon**
- **Gabriel Póvoa**
- **Matheus Soares**

---

**Licença:** Uso educacional apenas.
