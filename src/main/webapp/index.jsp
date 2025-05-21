<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Projeto Individual - Adilson Fernando</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .welcome-container {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            padding: 2rem;
        }
        .welcome-box {
            background-color: white;
            padding: 3rem;
            border-radius: 1rem;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <div class="welcome-box">
            <h1 class="mb-4">Bem-vindo!</h1>
            <p class="lead">Este é o projeto de bilheteria de <strong>Adilson Fernando Neves Ornellas</strong>.</p>
            <a class="btn btn-primary" href="./home.jsp">Clique aqui para acessar o Administrativo da Bilheteria</a>
        </div>
    </div>

    <!-- Bootstrap 5 JS (opcional, para funcionalidades JS como modais) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
