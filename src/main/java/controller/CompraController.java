package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.BilheteDao;
import dao.ClienteDao;
import dao.CompraDao;
import dao.EventoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bilhete;
import model.Evento;
import model.Compra;
import model.Cliente;

@WebServlet(urlPatterns = { "/compras", "/compras/cadastrar" })
public class CompraController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Compra compra = new Compra();
        CompraDao compraDao = new CompraDao();
        BilheteDao bilheteDao = new BilheteDao();

        try {
            String formaPagamento = request.getParameter("formaPagamento");
            String status = request.getParameter("status");
            float valor = Float.parseFloat(request.getParameter("valor"));
            int clienteId = Integer.parseInt(request.getParameter("clienteId"));
            int qtdBilhete = Integer.parseInt(request.getParameter("qtdBilhete"));
            int eventoId = Integer.parseInt(request.getParameter("eventoId"));

            compra.setFormaPagamento(formaPagamento);
            compra.setStatus(status);
            compra.setValor(valor);
            compra.setClienteId(clienteId);
            compra.setQtdBilhete(qtdBilhete);

            int compraId = compraDao.inserir(compra);

            for (int i = 0; i < qtdBilhete; i++) {
                Bilhete bilhete = new Bilhete();
                bilhete.setEventoId(eventoId);
                bilhete.setCompraId(compraId);
                bilheteDao.inserir(bilhete);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("compras");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.equals("/compras/cadastrar")) {
            ClienteDao clienteDao = new ClienteDao();
            EventoDao eventoDao = new EventoDao();
            try {
                List<Cliente> clientes = clienteDao.listarTodos();
                List<Evento> eventos = eventoDao.listarTodos(); // crie esse método

                request.setAttribute("clientes", clientes);
                request.setAttribute("eventos", eventos);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            RequestDispatcher rd = request.getRequestDispatcher("/compras/cadastrar.jsp");
            rd.forward(request, response);
        } else {
            // Seu código atual de listar compras (copiar daqui para baixo)
            CompraDao compraDao = new CompraDao();
            List<Compra> compras = null;

            String filtroStatus = request.getParameter("status");
            String filtroClienteId = request.getParameter("clienteId");

            try {
                if (filtroStatus != null && !filtroStatus.isEmpty() && filtroClienteId != null
                        && !filtroClienteId.isEmpty()) {
                    compras = compraDao.listarPorStatusECliente(filtroStatus, Integer.parseInt(filtroClienteId));
                } else if (filtroStatus != null && !filtroStatus.isEmpty()) {
                    compras = compraDao.listarPorStatus(filtroStatus);
                } else if (filtroClienteId != null && !filtroClienteId.isEmpty()) {
                    compras = compraDao.listarPorCliente(Integer.parseInt(filtroClienteId));
                } else {
                    compras = compraDao.listarTodos();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("compras", compras);
            RequestDispatcher rd = request.getRequestDispatcher("/compras/listar.jsp");
            rd.forward(request, response);
        }
    }

    // Excluir compra pelo ID via parâmetro
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CompraDao compraDao = new CompraDao();
        String idStr = request.getParameter("id");

        try {
            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                compraDao.excluir(id);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Compra excluída com sucesso.");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("ID da compra não informado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao excluir compra.");
        }
    }
}
