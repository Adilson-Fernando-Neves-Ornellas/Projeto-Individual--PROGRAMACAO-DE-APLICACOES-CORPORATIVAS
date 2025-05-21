package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.BilheteDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Bilhete;

@WebServlet("/bilhetes")
public class BilheteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BilheteDao bilheteDao = new BilheteDao();
        List<Bilhete> bilhetes = null;

        try {
            bilhetes = bilheteDao.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("bilhetes", bilhetes);
        RequestDispatcher rd = request.getRequestDispatcher("/bilhetes/listar.jsp");
        rd.forward(request, response);
    }
}
