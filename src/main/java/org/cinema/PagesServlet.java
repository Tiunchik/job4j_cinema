/**
 * Package org.cinema for
 *
 * @author Maksim Tiunchik
 */
package org.cinema;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class PagesServlet - servlet to connection different pages
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 02.04.2020
 */
@WebServlet(urlPatterns = {"/index", "/payment"})
public class PagesServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(PagesServlet.class.getName());

    /**
     * make connection to different pages
     *
     * @param req -
     * @param resp -
     * @throws ServletException -
     * @throws IOException -
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getServletPath();
        if (s.equals("/index")) {
            req.getRequestDispatcher("/index.html").forward(req, resp);
        }
        if (s.equals("/payment")) {
            req.getRequestDispatcher("/payment.html").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
