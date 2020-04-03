/**
 * Package org.cinema for
 *
 * @author Maksim Tiunchik
 */
package org.cinema;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class PostServlet - received post requests
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 02.04.2020
 */
@WebServlet(urlPatterns = {"/post"})
public class PostServlet extends HttpServlet {

    /**
     * connection to inner logic
     */
    private static final LogicBlock LOGIC = LogicBlock.getLogic();

    /**
     * inner logger
     */
    private static final Logger LOG = LogManager.getLogger(PostServlet.class.getName());

    /**
     * process two type of post - to get all information about hall and make purchase operation with hall
     *
     * @param req -
     * @param resp -
     * @throws ServletException -
     * @throws IOException -
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder allLine = new StringBuilder();
        try (BufferedReader read = req.getReader();
             PrintWriter writer = resp.getWriter()) {
            String oneLine;
            while ((oneLine = read.readLine()) != null) {
                allLine.append(oneLine);
            }
            JSONObject jsonRequest = (JSONObject) new JSONParser().parse(allLine.toString());

            if (((String) jsonRequest.get("action")).equalsIgnoreCase("getHall")) {
                List<List<Place>> answer = LOGIC.getHall();
                String outJSON = new Gson().toJson(answer);
                writer.write(outJSON);
                writer.flush();
            }

            if (((String) jsonRequest.get("action")).equalsIgnoreCase("payment")) {
                JSONArray s = (JSONArray) jsonRequest.get("check");
                for (var e : s) {
                    String str = e.toString();
                    String[] pl = str.split("-");
                    LOGIC.purchaise(new Place(Integer.parseInt(pl[0]), Integer.parseInt(pl[1]), (String) jsonRequest.get("name")));
                }
            }
        } catch (ParseException e) {
            LOG.error("Parse POST exception", e);
        }
    }
}
