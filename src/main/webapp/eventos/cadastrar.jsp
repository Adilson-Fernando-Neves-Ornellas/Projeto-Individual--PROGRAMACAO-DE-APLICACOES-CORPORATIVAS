<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastar Evento - Projeto Individual - Adilson Fernando</title>
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
                <a href="./usuarios">🔐 Usuários</a>
                <hr class="border-white">
                <a href="./logout">🔓 Sair</a>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 content">
           <h2>Cadastro de Evento!</h2>
            <form id="formEvento" action="/projeto_adilson/eventos" method="post" class="mt-4">
                <div class="mb-3">
                    <label for="nome" class="form-label">Nome do Evento</label>
                    <input type="text" id="nome" name="nome" class="form-control" required maxlength="100" />
                </div>

                <div class="mb-3">
                    <label for="dataHoraInicio" class="form-label">Data e Hora de Início</label>
                    <input type="datetime-local" id="dataHoraInicio" name="dataHoraInicio" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="dataHoraFim" class="form-label">Data e Hora de Fim</label>
                    <input type="datetime-local" id="dataHoraFim" name="dataHoraFim" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="capacidade" class="form-label">Capacidade</label>
                    <input type="number" id="capacidade" name="capacidade" class="form-control" min="1" required />
                </div>

                <div class="mb-3">
                    <label for="valorIngresso" class="form-label">Valor do Ingresso (R$)</label>
                    <input type="number" id="valorIngresso" name="valorIngresso" class="form-control" min="0" step="0.01" required />
                </div>

                <button type="submit" class="btn btn-primary">Cadastrar Evento</button>
                <a href="/projeto_adilson/eventos" class="btn btn-secondary ms-2">Voltar</a>
            </form>
        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.getElementById('formEvento').addEventListener('submit', function(event) {
        const inicio = document.getElementById('dataHoraInicio').value;
        const fim = document.getElementById('dataHoraFim').value;

        if (inicio && fim && fim < inicio) {
            alert('A data e hora de fim não pode ser menor que a data e hora de início.');
            event.preventDefault()
        }
    });
</script>
</body>
</html>
