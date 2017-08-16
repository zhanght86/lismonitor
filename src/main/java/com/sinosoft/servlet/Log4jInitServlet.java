package com.sinosoft.servlet;

//import org.apache.log4j.PropertyConfigurator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Log4jInitServlet")
public class Log4jInitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
//        String prefix = getServletContext().getRealPath("/");
//        String file = getInitParameter("log4j-config-file");
//        if (file != null) {
//            System.out.println("read log4j.properties:"+prefix + file);
//            PropertyConfigurator.configure(prefix + file);
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
}
