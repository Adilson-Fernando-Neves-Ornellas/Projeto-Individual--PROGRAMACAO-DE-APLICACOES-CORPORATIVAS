package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.ClienteDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = new Cliente();
        ClienteDao clienteDao = new ClienteDao();

        try {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String telefone = request.getParameter("telefone");
            LocalDate dataNasc = LocalDate.parse(request.getParameter("dataNasc"), FORMATTER);
            String senha = request.getParameter("senha");

            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setTelefone(telefone);
            cliente.setDataNasc(dataNasc);
            cliente.setSenha(senha);

            clienteDao.inserir(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("clientes");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClienteDao clienteDao = new ClienteDao();
        List<Cliente> clientes = null;

        try {
            clientes = clienteDao.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("clientes", clientes);
        RequestDispatcher rd = request.getRequestDispatcher("/clientes/listar.jsp");
        rd.forward(request, response);
    }
}
