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

            <form method="get" action="compras" class="d-flex justify-content-between">
                <div class="col-md-6 d-flex justify-content-center gap-2">
                    <div class="col-md-10">
                        <input type="text" name="nomeCliente" class="form-control" placeholder="Buscar nome do cliente" value="${param.nomeCliente}">
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-outline-primary">🔍</button>
                    </div>
                </div>
                <div class="col-md-4 text-end">
                    <a href="./compras/cadastrar" class="btn btn-primary">
                        + Incluir Nova Compra
                    </a>
                </div>
            </form>


            <div class="table-responsive">
                <table class="table table-bordered table-striped mt-3">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Cliente</th>
                            <th>Forma Pagamento</th>
                            <th>Valor</th>
                            <th>Status</th>
                            <th>Ações</th>
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
                                <td>
                                    <form action="/projeto_adilson/compras/excluir" method="get" onsubmit="return confirm('Tem certeza que deseja excluir esta compra?');">
                                        <input type="hidden" name="id" value="${compra.id}" />
                                        <button type="submit" class="btn btn-danger btn-sm">Excluir</button>
                                    </form>
                                </td>
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
