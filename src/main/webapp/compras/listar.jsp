<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Compras - Projeto Individual - Adilson Fernando</title>
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
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 content">
            <h2>Listagem de Compras!</h2>

            <div class="mb-3 text-end">
                <a href="./compras/cadastrar" class="btn btn-primary">
                    + Incluir Nova Compra
                </a>
            </div>

            <div class="table-responsive">
                <table class="table table-bordered table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Cliente</th>
                            <th>Forma Pagamento</th>
                            <th>Valor</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="compra" items="${compras}">
                            <tr>
                                <td><c:out value="${compra.id}" /></td>
                                <td><c:out value="${compra.cliente != null ? compra.cliente.nome : 'Cliente não informado'}" /></td>
                                <td><c:out value="${compra.formaPagamento}" /></td>
                                <td><c:out value="${compra.valor}" /></td>
                                <td><c:out value="${compra.status}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
