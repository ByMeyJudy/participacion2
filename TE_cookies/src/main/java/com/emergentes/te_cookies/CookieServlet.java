package com.emergentes.te_cookies;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CookieServlet", urlPatterns = {"/CookieServlet"})
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = null;
        boolean nuevaVisita = true;
        int cont = 0;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            //Cookie c:cookies manera de recorrer un arreglo
            //esto gracias al obj c
            for(Cookie c:cookies){
                if((c.getName().equals("visitante"))&& c.getValue().equals("SI")){
                    //false xq no sera nuevo
                    nuevaVisita =false;
                    cont=0;
                    cont++;
                    break;
                }
            }
        }
        if (nuevaVisita) {
            Cookie ck = new Cookie("visitante", "SI");
            ck.setMaxAge(120); //para tiempo de sesion
            ck.setComment("control de nuevos visitantes");
            response.addCookie(ck);
            cont++;
            mensaje = "gracias por visitar nuestra pagina"+"\n número de visitas: #"+cont;
            
        } 
        else {
            cont++;
            mensaje = "Estamos agradecidos por tenerlo NUEVAMENTE"+"\n número de visitas: #"+cont;
        }
         
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + mensaje + "</h1>");
        out.println("<a href='index.jsp'> Ir al inicio</a>");
    }
}

