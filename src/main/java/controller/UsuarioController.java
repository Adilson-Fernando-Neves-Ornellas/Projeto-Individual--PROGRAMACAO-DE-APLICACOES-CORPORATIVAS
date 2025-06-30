package controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import dao.UsuarioDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import model.Usuario;

@WebServlet(urlPatterns = { "/usuarios", "/usuarios/cadastrar", "/usuarios/excluir" })
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();  

        if (path.equals("/usuarios/cadastrar")) {
            RequestDispatcher rd = request.getRequestDispatcher("/usuarios/cadastrar.jsp");
            rd.forward(request, response);

        } 

        String nome = request.getParameter("nomeUsuario");
        boolean jsonResponse = "application/json".equals(request.getHeader("Accept"));

        UsuarioDao usuarioDao = new UsuarioDao();
        List<Usuario> usuarios = new ArrayList<>();

        try {
            if (nome != null && !nome.isEmpty()) {
                usuarios = usuarioDao.listarPorNome(nome);
            } else {
                usuarios = usuarioDao.listarTodos();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (jsonResponse) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("[");
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                out.print("{");
                out.print("\"id\":" + u.getId() + ",");
                out.print("\"nome\":\"" + u.getNome() + "\",");
                out.print("\"email\":\"" + u.getEmail() + "\"");
                out.print("}");
                if (i < usuarios.size() - 1) out.print(",");
            }
            out.print("]");
            out.flush();
        } else {
            request.setAttribute("usuarios", usuarios);
            RequestDispatcher rd = request.getRequestDispatcher("/usuarios/listar.jsp");
            rd.forward(request, response);
        }
    }

        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}