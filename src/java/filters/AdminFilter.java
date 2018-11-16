/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

/**
 *
 * @author awarsyle
 */
public class AdminFilter implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
            // this code will execute before HomeServlet and UsersServlet
            HttpServletRequest r = (HttpServletRequest)request;
            HttpSession session = r.getSession();
            String username = (String) session.getAttribute("username");
            UserService us = new UserService();
            User user = null;
            
        try {
            user = us.get(username);
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
            Role role = user.getRole();
            
            if (role.getRoleid() == 1) {
                chain.doFilter(request, response);
            } else {
                HttpServletResponse resp = (HttpServletResponse)response;
                resp.sendRedirect("home");
            }
            
            // this code will execute after HomeServlet and UsersServlet
            
            
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
        
    }

    
}
