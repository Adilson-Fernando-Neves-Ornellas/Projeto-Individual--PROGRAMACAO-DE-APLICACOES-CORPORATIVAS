package controller;

import dao.UsuarioDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String next = null;
        String msg = null;
        String tokenId = null;
        HttpSession session = request.getSession();
        Usuario usuario = null;

        String emailTxt = request.getParameter("email");
        String senhaTxt = request.getParameter("senha");

        UsuarioDao usuarioDao = new UsuarioDao();
        try {
            usuario = usuarioDao.buscarPorEmail(emailTxt);
        } catch (SQLException e) {
            usuario = null;
        }

        if (usuario == null) {
            next = "index.jsp";
            msg = "Email inválido!";
        } else {
            if (BCrypt.checkpw(senhaTxt, usuario.getSenha())) {
                tokenId = java.util.UUID.randomUUID().toString();
                next = "home.jsp";
                usuario.setSenha(null);
            } else {
                usuario = null;
                next = "index.jsp";
                msg = "Email ou Senha inválido, tente novamente!";
            }
        }

        session.setAttribute("usuario", usuario);
        session.setAttribute("tokenId", tokenId);
        request.setAttribute("msg", msg);
        RequestDispatcher rd = request.getRequestDispatcher(next);
        rd.forward(request, response);
    }

}
