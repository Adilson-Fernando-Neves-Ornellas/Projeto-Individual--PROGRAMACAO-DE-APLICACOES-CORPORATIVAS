<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastar Usuario - Projeto Individual - Adilson Fernando</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .sidebar {
            height: 100vh;
            background-color: #343a40;
            padding-top: 1rem;
        }
        .sidebar a {
            color: #fff;
            padding: 0.75rem 1rem;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .content {
            padding: 2rem;
        }
        @media (max-width: 768px) {
            .sidebar {
                height: auto;
            }
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">

        <nav class="col-md-3 col-lg-2 d-md-block bg-dark sidebar text-white">
            <div class="position-sticky">
                <h4 class="text-center text-white mb-4">Bilheteria</h4>
                <a href="/projeto_adilson/eventos">🎭 Eventos</a>
                <a href="/projeto_adilson/bilhetes">🎫 Bilhetes</a>
                <a href="/projeto_adilson/compras">🛒 Compras</a>
                <a href="/projeto_adilson/clientes">👤 Clientes</a>
                <a href="/projeto_adilson/usuarios">🔐 Usuários</a>
                <hr class="border-white">
                <a href="/projeto_adilson/logout">🔓 Sair</a>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 content">
            <h2>Cadastro de Usuario</h2>

            <form id="form_cadastro_usuario" >
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="nome" name="nome" required>
                    <div class="invalid-feedback">
                        Por favor, insira seu nome.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email" class="form-label">E-mail</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                    <div class="invalid-feedback">
                        Por favor, insira um e-mail válido.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha" required
                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,}$"
                        title="Senha deve ter no mínimo 8 caracteres, incluindo letras maiúsculas, minúsculas, números e símbolos.">
                    <div class="invalid-feedback">
                        A senha deve conter no mínimo 8 caracteres, com letras maiúsculas, minúsculas, números e símbolos.
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>

        </main>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.getElementById('form_cadastro_usuario').addEventListener('submit', async function (event) {
        event.preventDefault(); 

        const form = event.target;

        if (!form.checkValidity()) {
            form.classList.add('was-validated');
            return;
        }

        const nome = form.nome.value.trim();
        const email = form.email.value.trim();
        const senha = form.senha.value;

        try {
            const response = await fetch('/projeto_adilson/usuarios', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify({ nome, email, senha })
            });

            const data = await response.json();

            if (response.ok) {
                alert(data.mensagem || "Usuário cadastrado com sucesso!");
                form.reset();
                form.classList.remove('was-validated');
                window.location.href = "/projeto_adilson/usuarios";
            } else {
                alert(data.erro || "Erro ao cadastrar.");
            }
        } catch (error) {
            console.error("Erro:", error);
            alert("Erro inesperado.");
        }
    });
</script>


</body>
</html>
