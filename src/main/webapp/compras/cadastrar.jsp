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
                <a href="/projeto_adilson/usuarios">🔐 Usuários</a>
                <hr class="border-white">
                <a href="/projeto_adilson/logout">🔓 Sair</a>
            </div>
        </nav>

        <main class="col-md-9 ms-sm-auto col-lg-10 content">
            <h2>Cadastro de Compra</h2>

            <form method="post" action="/projeto_adilson/compras">
                <div class="mb-3">
                    <label for="clienteId" class="form-label">Cliente</label>
                    <select class="form-select" id="clienteId" name="clienteId" required>
                        <option value="">Selecione um cliente</option>
                        <c:forEach var="cliente" items="${clientes}">
                            <option value="${cliente.id}">${cliente.nome}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="formaPagamento" class="form-label">Forma de Pagamento</label>
                    <select class="form-select" id="formaPagamento" name="formaPagamento" required>
                        <option value="">Selecione</option>
                        <option value="DINHEIRO">Dinheiro</option>
                        <option value="CARTAO">Cartão</option>
                        <option value="PIX">PIX</option>
                    </select>
                </div>

                <input type="hidden" name="status" value="PAGO"/>

                <div class="mb-3">
                    <label for="eventoId" class="form-label">Evento</label>
                    <select class="form-select" id="eventoId" name="eventoId" required>
                        <option value="">Selecione um evento</option>
                        <c:forEach var="evento" items="${eventos}">
                            <option value="${evento.id}" data-preco="${evento.valorIngresso}" data-capacidade="${evento.capacidade}">
                                ${evento.nome} - R$ ${evento.valorIngresso}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="qtdBilhete" class="form-label">Quantidade de Bilhetes</label>
                    <input type="number" class="form-control" id="qtdBilhete" name="qtdBilhete" required />
                </div>

                <div class="mb-3">
                    <label for="valor" class="form-label">Valor (R$)</label>
                    <input type="number" step="0.01" class="form-control" id="valor" name="valor" required  readonly/>
                </div>

                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>
        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
document.addEventListener("DOMContentLoaded", () => {
    const eventoSelect = document.getElementById("eventoId");
    const qtdInput = document.getElementById("qtdBilhete");
    const valorInput = document.getElementById("valor");

    function atualizarValor() {
        const selectedOption = eventoSelect.options[eventoSelect.selectedIndex];
        const preco = parseFloat(selectedOption.dataset.preco || 0);
        const capacidade = parseInt(selectedOption.dataset.capacidade || 0);
        const qtd = parseInt(qtdInput.value || 0);

        if (qtd > capacidade) {
            alert(`Este evento só possui ${capacidade} de capacidade.`);
            qtdInput.value = capacidade;
            valorInput.value = (capacidade * preco).toFixed(2);
        } else {
            valorInput.value = (qtd * preco).toFixed(2);
        }
    }

    eventoSelect.addEventListener("change", atualizarValor);
    qtdInput.addEventListener("input", atualizarValor);
});
</script>
</body>
</html>
