package com.practicals;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class FirstPage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String strName = "", strState = "", strCity = "";
        String[] strTokens;
        boolean found = false;
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println(
                    "<body><center><u><h2><b>Student Personal Details</b></h2></u></center><form action=/practicals/servlet/SecondPage>");
            Cookie[] cookies = request.getCookies();
            if ((cookies != null) && (cookies.length > 0)) {
                for (int i = 0; i < cookies.length; i++) {
                    if ((cookies[i].getName()).equals("Personal")) {
                        found = true;
                        strTokens = (cookies[i].getValue()).split(",");
                        strName = strTokens[0];
                        strState = strTokens[1];
                        strCity = strTokens[2];
                        out.println(
                                "<h3><b><u>Note : </u><font color=green> Cookie with name 'Personal' is found.<br>So all the textboxes below are already filled with the cookie values.</font></b></h3><br><br>");
                        break;
                    }
                }
            }
            if (!found) {
                out.println(
                        "<h3><b><u>Note : </u><font color=red>No cookie with name 'Personal' is found on client machine.</font></b></h3><br><br>");
            }
            out.println("<b>Name : <input type=text name=txtName value='" + strName + "' /></b><br><br>");
            out.println("<b>State : <input type=text name=txtState value='" + strState + "' /></b><br><br>");
            out.println("<b>City : <input type=text name=txtCity value='" + strCity + "' /></b><br><br>");
            out.println("<input type=submit value=Next />");
            out.println("</form></body>");
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
