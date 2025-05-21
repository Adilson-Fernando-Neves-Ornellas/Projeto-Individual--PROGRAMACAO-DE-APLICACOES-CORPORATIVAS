# 🎟️ Projeto Bilheteria Adilson Fernando Neves Ornellas

Sistema web simples de bilheteria desenvolvido em Java usando MVC com JSP, Servlets e DAO.  
Projeto realizado para a disciplina **Programação de Aplicações Corporativas**.


## 📁 Estrutura do Projeto

src/
└── main/
    ├── java/
    │   ├── controller/              # Servlets responsáveis pelo controle da lógica da aplicação
    │   │   └── Exemplo.java         # Exemplo de servlet controlador
    │   ├── dao/                     # Data Access Objects: acesso ao banco de dados
    │   │   └── DatabaseConnection.java # Classe de conexão com o banco de dados
    │   └── model/                   # Classes de modelo (entidades), representam as tabelas do banco
    │
    └── webapp/                      # Interface da aplicação (JSPs)
        ├── bilhetes/
        │   └── listar.jsp           # Página para listar bilhetes
        ├── clientes/
        │   ├── cadastrar.jsp        # Página para cadastrar clientes
        │   └── listar.jsp           # Página para listar clientes
        ├── compras/
        │   ├── cadastrar.jsp        # Página para registrar uma compra
        │   └── listar.jsp           # Página para listar compras
        ├── eventos/
        │   └── listar.jsp           # Página para listar eventos
        ├── WEB-INF/                 # Arquivos protegidos do acesso direto
        ├── home.jsp                 # Página inicial da aplicação
        └── index.jsp                # Redireciona ou encaminha para a home


## 📌 Funcionalidades

- ✅ Gerenciamento de Clientes (CRUD básico)  
- ✅ Registro, listagem e exclusão de Compras com filtros  
- 🎨 Interface responsiva com Bootstrap 5

## 🚀 Requisitos para rodar o projeto

- Windows 7 ou superior  
- JDK 21  
- Maven 3.9.9  
- Apache Tomcat 10.1.39  
- MySQL (via XAMPP recomendado)

## ⚙️ Como executar localmente

1. Crie o banco de dados MySQL chamado `bilheteria`.  
2. Edite o arquivo `deploy.bat`: na linha 11, substitua o caminho `C:\dev\apache-tomcat-10.1.39` pelo caminho da sua instalação do Tomcat.  
3. Abra o terminal na pasta do projeto.  
4. Execute: "./deploy.bat"
