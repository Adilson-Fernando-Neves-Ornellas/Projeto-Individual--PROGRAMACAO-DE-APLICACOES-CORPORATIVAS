<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Usuarios - Projeto Individual - Adilson Fernando</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
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
                <a href="./eventos">🎭 Eventos</a>
                <a href="./bilhetes">🎫 Bilhetes</a>
                <a href="./compras">🛒 Compras</a>
                <a href="./clientes">👤 Clientes</a>
                <a href="./usuarios">🔐 Usuários</a>
                <hr class="border-white">
                <a href="./logout">🔓 Sair</a>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 content">
            <h2>Listagem de Usuarios!</h2>

            <form method="get" id="form_get_usuarios" class="d-flex justify-content-between">
                <div class="col-md-6 d-flex justify-content-center gap-2">
                    <div class="col-md-10">
                        <input type="text" name="nomeUsuario" class="form-control" placeholder="Buscar nome do Usuario" value="${param.nomeUsuario}">
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-outline-primary">🔍</button>
                    </div>
                </div>
                <div class="col-md-4 text-end">
                    <a href="./usuarios/cadastrar" class="btn btn-primary">
                        + Incluir Novo Usuario
                    </a>
                </div>
            </form>


            <div class="table-responsive">
                <table class="table table-bordered table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="usuario" items="${usuarios}">
                            <tr>
                                <td><c:out value="${usuario.id}" /></td>
                                <td><c:out value="${usuario.nome}" /></td>
                                <td><c:out value="${usuario.email}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.getElementById('form_get_usuarios').addEventListener('submit', function (e) {
        e.preventDefault();
        const nome = this.nomeUsuario.value;

        fetch('usuarios?nomeUsuario=' + encodeURIComponent(nome), {
            headers: {
                'Accept': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector('tbody');
            tbody.innerHTML = '';
            data.forEach(usuario => {
                const row = `<tr><td>${usuario.id}</td><td>${usuario.nome}</td><td>${usuario.email}</td></tr>`;
                tbody.innerHTML += row;
            });
        });
    });
</script>
</body>
</html>
