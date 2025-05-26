# 📋 Lista de Tarefas Compartilhável

Projeto acadêmico desenvolvido para a disciplina de **Desenvolvimento de Sistemas**, com foco em colaboração entre usuários e exportação de tarefas em formatos **PDF** e **Excel**.

## ✨ Funcionalidades

- ✅ Criação, edição e remoção de tarefas
- 👥 Compartilhamento de listas entre usuários
- 📤 Exportação da lista de tarefas para:
  - PDF (utilizando biblioteca **iText**)
  - Excel (utilizando biblioteca **Apache POI**) ( Podendo ser alterado)
  
## 🔧 Tecnologias Utilizadas

- Java 11+
- Biblioteca [iText PDF](https://kb.itextpdf.com/home/it7kb/examples/itext-7-jump-start-tutorial-for-java)
- Biblioteca [Apache POI](https://poi.apache.org/) (para geração de arquivos `.xlsx`)
- IDE: IntelliJ IDEA / Eclipse / VS Code

## 📦 Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/ChristianoBourguignon/tasklist.git
   cd tasklist
   ```

2. Importe o projeto em sua IDE preferida como um projeto Maven ou adicione manualmente os JARs necessários.

3. Adicione as bibliotecas:

   ### Dependências Maven (Exemplo):
   ```xml
   <dependencies>
     <!-- iText PDF -->
     <dependency>
       <groupId>com.itextpdf</groupId>
       <artifactId>itext7-core</artifactId>
       <version>8.0.0</version>
       <type>pom</type>
     </dependency>

     <!-- Apache POI para Excel -->
     <dependency>
       <groupId>org.apache.poi</groupId>
       <artifactId>poi-ooxml</artifactId>
       <version>5.2.3</version>
     </dependency>
   </dependencies>
   ```
4. Rode o `MVN INSTALL` e `MVN JETTY:RUN`
5. Acesse `localhost:8080`.

## 📄 Estrutura do Projeto

```bash
📦 src
 ┣ 📂 controllers       # Controladores
 ┣ 📂 services          # Exportadores PDF/Excel
 ┣ 📂 views             # Interface com usuário
```

## 📑 Créditos

Desenvolvido por:

- **Christiano Bourguignon**
- **Gabriel Póvoa**
- **Matheus Soares**

---

**Licença:** Uso educacional apenas.
