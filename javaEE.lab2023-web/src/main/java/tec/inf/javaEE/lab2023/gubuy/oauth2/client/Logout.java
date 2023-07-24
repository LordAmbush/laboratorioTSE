package tec.inf.javaEE.lab2023.gubuy.oauth2.client;

import org.eclipse.microprofile.config.Config;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Home
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private Config config;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String code = Credenciales.getCallBackGubUyInfo();
        if(code != null){
            String redirect_uri = config.getValue("client.logoutUri", String.class);
            String token = Credenciales.getAccesToken();
            String logoutURL = "https://auth-testing.iduruguay.gub.uy/oidc/v1/logout?id_token_hint=" + token + "&post_logout_redirect_uri=" + redirect_uri;

            //Hay que hacerlo asi porque servlet no probe una manera directa de eliminar cookies
            //Cookie newCookie = new Cookie("JWT", "");
            //newCookie.setMaxAge(0);
            //newCookie.setValue(null);
            //response.addCookie(newCookie);
            
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");

            Credenciales.setAccesToken(null);
            Credenciales.setCallBackGubUyInfo(null);
            response.sendRedirect(logoutURL);
            
        }else {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/dashboard.xhtml");
            dispatcher.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    public Optional<String> readCookie(String key,HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    String getAtributeFromCookie(Optional<String>  cookie, String param){
        String[] user = cookie.get().split(param+"\":\"");
        user = user[1].split("\"");
        return user[0];
    }

}
