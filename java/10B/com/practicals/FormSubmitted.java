package com.practicals;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class FormSubmitted extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String strCourse = request.getParameter("txtCourse");
            String strCollege = request.getParameter("txtCollege");
            String strUniversity = request.getParameter("txtUniversity");

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            Cookie p1 = new Cookie("Education", strCourse + "," + strCollege + "," + strUniversity);
            p1.setMaxAge(60 * 60 * 24 * 360);
            response.addCookie(p1);

            out.println(
                    "<body><center><h2><b><font color=green>Student Details are Submitted Successfully.</font></b></h2></center>");

            out.println(
                    "<h3><b><u>Note : </u><font color=blue>Cookie with name 'Education' is created(If donot exist) OR modified(If already exist).</font></b></h3><br>");
            out.println("<form action=practicals/servlet/FirstPage>");
            out.println("<input type=submit value='Back to Personal Details' />");
            out.println("</form>");

            out.println("<form action=practicals/servlet/SecondPage>");
            out.println("<input type=submit value='Back to Education Details' />");
            out.println("</form></body>");

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
