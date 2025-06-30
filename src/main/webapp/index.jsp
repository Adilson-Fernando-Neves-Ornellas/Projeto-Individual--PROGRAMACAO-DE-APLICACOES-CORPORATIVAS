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
        .login-container {
            width: 100%;
            display: flex;
        }
        .login-box {
            background-color: white;
            padding: 2.5rem;
            border-radius: 1rem;
            width: 100%;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <div class="welcome-box">
            <h1 class="mb-4">Bem-vindo, Faça seu Login!</h1>
            <p class="lead">Este é o projeto de bilheteria de <strong>Adilson Fernando Neves Ornellas</strong>.</p>
                <div class="login-container">
                <div class="login-box">
                    <c:if test="${not empty msg}">
                        <div class="alert alert-danger text-center" role="alert">
                            ${msg}
                        </div>
                    </c:if>
                    <form action="./login" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label">E-mail</label>
                            <input type="email" name="email" id="email" class="form-control" required autofocus>
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" id="senha" class="form-control" required>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Entre e acesse o Administrativo </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap 5 JS (opcional, para funcionalidades JS como modais) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
