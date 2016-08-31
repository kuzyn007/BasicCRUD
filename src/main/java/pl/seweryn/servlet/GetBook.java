package pl.seweryn.servlet;
 
import java.io.IOException;
 
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.seweryn.dao.BookDao;
import pl.seweryn.model.Book;
 
@WebServlet("/get")
public class GetBook extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    @Inject
    private BookDao bookDao;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookId = request.getParameter("id");
        Long id = Long.valueOf(bookId);
        Book book = bookDao.get(id);
        response.getWriter().println(book);
    }
}