<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Clientes - Projeto Individual - Adilson Fernando</title>
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
            <h2>Listagem de Clientes!</h2>

            <div class="mb-3 text-end">
                <a href="./clientes/cadastrar.jsp" class="btn btn-primary">
                    + Incluir Novo Cliente
                </a>
            </div>

            <div class="table-responsive">
                <table class="table table-striped table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>CPF</th>
                            <th>Data de Nascimento</th>
                            <th>Telefone</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${not empty clientes}">
                            <c:forEach var="cliente" items="${clientes}">
                                <tr>
                                    <td><c:out value="${cliente.id}" /></td>
                                    <td><c:out value="${cliente.nome}" /></td>
                                    <td><c:out value="${cliente.cpf}" /></td>
                                    <td><c:out value="${cliente.dataNasc}" /></td>
                                    <td><c:out value="${cliente.telefone}" /></td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        <c:if test="${empty clientes}">
                            <tr>
                                <td colspan="5" class="text-center">Nenhum cliente encontrado.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
