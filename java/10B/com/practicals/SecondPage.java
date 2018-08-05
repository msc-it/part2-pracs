package com.practicals;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class SecondPage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        try {

            String strName = request.getParameter("txtName");
            String strState = request.getParameter("txtState");
            String strCity = request.getParameter("txtCity");

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            Cookie p1 = new Cookie("Personal", strName + "," + strState + "," + strCity);
            p1.setMaxAge(60 * 60 * 24 * 360);

            response.addCookie(p1);

            out.println(
                    "<body><center><u><h2><b>Student Education Details</b></h2></u></center><form action=/practicals/servlet/FormSubmitted>");
            out.println(
                    "<h3><b><u>Note : </u><font color=blue>Cookie with name 'Personal' is created(If donot exist) OR modified(If already exist).</font></b></h3><br>");

            String strCourse = "", strCollege = "", strUniversity = "";
            String[] strTokens;

            boolean found = false;
            try {

                Cookie[] cookies = request.getCookies();

                if ((cookies != null) && (cookies.length > 0)) {
                    for (int i = 0; i < cookies.length; i++) {
                        if ((cookies[i].getName()).equals("Education")) {

                            found = true;
                            strTokens = (cookies[i].getValue()).split(",");

                            strCourse = strTokens[0];
                            strCollege = strTokens[1];
                            strUniversity = strTokens[2];

                            out.println(
                                    "<h3><b><u>Note : </u><font color=green> Cookie with name 'Education' is found.<br>So all the textboxes below are already filled with the cookie values.</font></b></h3><br><br>");

                            break;
                        }

                    }

                }

                if (!found) {

                    out.println(
                            "<h3><b><u>Note : </u><font color=red>No cookie with name 'Education' is found on client machine.</font></b></h3><br><br>");
                }

                out.println("<b>Course : <input type=text name=txtCourse value='" + strCourse + "' /></b><br><br>");

                out.println("<b>College : <input type=text name=txtCollege value='" + strCollege + "' /></b><br><br>");

                out.println("<b>University : <input type=text name=txtUniversity value='" + strUniversity
                        + "' /></b><br><br>");

                out.print("<input type=submit value=Next />");
                out.print("</form>");

                out.print("<form action=/practicals/servlet/FirstPage>");
                out.print("<input type=submit value='Back' />");
                out.print("</form></body>");

            } catch (Exception ex) {
                ex.printStackTrace();

            }
            out.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
