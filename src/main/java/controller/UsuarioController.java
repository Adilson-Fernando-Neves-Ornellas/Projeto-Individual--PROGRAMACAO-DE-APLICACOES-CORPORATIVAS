package controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.BufferedReader;
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
import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;

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

        if (!"application/json".equalsIgnoreCase(request.getContentType())) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Envio apenas via JSON.");
            return;
        }

        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }

        Gson gson = new Gson();
        Usuario novoUsuario = gson.fromJson(jsonBuilder.toString(), Usuario.class);

        UsuarioDao usuarioDao = new UsuarioDao();

        try {
            if (usuarioDao.emailExiste(novoUsuario.getEmail())) {
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                response.setContentType("application/json");
                response.getWriter().write("{\"erro\":\"Email já cadastrado.\"}");
                return;
            }

            novoUsuario.setSenha(BCrypt.hashpw(novoUsuario.getSenha(), BCrypt.gensalt()));
            usuarioDao.inserir(novoUsuario);

            response.setContentType("application/json");
            response.getWriter().write("{\"mensagem\":\"Usuário cadastrado com sucesso!\"}");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.getWriter().write("{\"erro\":\"Erro ao cadastrar: " + e.getMessage() + "\"}");
        }
    }


}