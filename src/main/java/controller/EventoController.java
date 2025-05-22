package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.EventoDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Evento;

@WebServlet("/eventos")
public class EventoController extends HttpServlet {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Evento evento = new Evento();
        EventoDao eventoDao = new EventoDao();

        try {
            // Extrair dados do formulário
            String nome = request.getParameter("nome");
            LocalDateTime dataHoraInicio = LocalDateTime.parse(request.getParameter("dataHoraInicio"), FORMATTER);
            LocalDateTime dataHoraFim = LocalDateTime.parse(request.getParameter("dataHoraFim"), FORMATTER);
            int capacidade = Integer.parseInt(request.getParameter("capacidade"));
            double valorIngresso = Double.parseDouble(request.getParameter("valorIngresso"));

            // Setar no objeto evento
            evento.setNome(nome);
            evento.setDataHoraInicio(dataHoraInicio);
            evento.setDataHoraFim(dataHoraFim);
            evento.setCapacidade(capacidade);
            evento.setValorIngresso(valorIngresso);

            eventoDao.inserir(evento);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("eventos"); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EventoDao eventoDao = new EventoDao();
        List<Evento> eventos = null;

        try {
            eventos = eventoDao.listarTodos();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("eventos", eventos);
        RequestDispatcher rd = request.getRequestDispatcher("/eventos/listar.jsp");
        rd.forward(request, response);
    }
}
