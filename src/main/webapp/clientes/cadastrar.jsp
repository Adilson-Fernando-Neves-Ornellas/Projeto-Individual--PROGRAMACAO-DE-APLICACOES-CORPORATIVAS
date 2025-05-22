<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastrar Cliente - Projeto Individual - Adilson Fernando</title>
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
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 content">
            <h2>Cadastro de Cliente</h2>

            <form action="/projeto_adilson/clientes" method="post" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome</label>
                    <input type="text" class="form-control" id="nome" name="nome" required>
                </div>

                <div class="mb-3">
                    <label for="cpf" class="form-label">CPF</label>
                    <input type="text" class="form-control" id="cpf" name="cpf" required>
                    <div class="invalid-feedback">Informe um CPF válido.</div>
                </div>

                <div class="mb-3">
                    <label for="telefone" class="form-label">Telefone (com DDD)</label>
                    <input type="text" class="form-control" id="telefone" name="telefone" required>
                    <div class="invalid-feedback">Informe um telefone válido.</div>
                </div>

                <div class="mb-3">
                    <label for="dataNasc" class="form-label">Data de Nascimento</label>
                    <input type="date" class="form-control" id="dataNasc" name="dataNasc" required max="">
                    <div class="invalid-feedback">A data de nascimento não pode ser futura.</div>
                </div>

                <div class="mb-3">
                    <label for="senha" class="form-label">Senha</label>
                    <input type="password" class="form-control" id="senha" name="senha" required minlength="6">
                    <div class="invalid-feedback">A senha deve ter pelo menos 6 caracteres.</div>
                </div>

                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>
        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://unpkg.com/imask"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {

        IMask(document.getElementById('cpf'), {
            mask: '00000000000'
        });

        IMask(document.getElementById('telefone'), {
            mask: '00000000000'
        });

        const dataInput = document.getElementById('dataNasc');
        const hoje = new Date().toISOString().split('T')[0];
        dataInput.max = hoje;

        const forms = document.querySelectorAll('.needs-validation');
        Array.prototype.slice.call(forms).forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    });
</script>
</body>
</html>
