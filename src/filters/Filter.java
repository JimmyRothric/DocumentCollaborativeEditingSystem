package filters;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class Filter
 */
@WebFilter(filterName = "Filter", urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {

    /**
     * Default constructor. 
     */
    public Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletRequest hrequest = (HttpServletRequest)request;
		HttpServletResponse hresponse = (HttpServletResponse)response;
		HttpSession session = hrequest.getSession();
		String requestPath = hrequest.getServletPath();
		String[] str = requestPath.split("/");
		if (str.length < 2) {
			hresponse.sendRedirect(hrequest.getHeader("referer"));
			return;
		}
		String keyword = str[str.length - 2];
		if (keyword.equals("css") || keyword.equals("img") || keyword.equals("js")) {
			chain.doFilter(request, response);
			return;
		}
	
		if (requestPath.contains("LoginServlet") || requestPath.contains("RegisterServlet") || requestPath.contains("home.jsp") || requestPath.contains("message.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		//Only for test, this can be removed
		if (requestPath.contains("login.jsp") || requestPath.contains("register.jsp")) {
			chain.doFilter(request, response);
			return;
		}
		//
		if (session.getAttribute("account") == null) {
			session.setAttribute("message", "请先登录");
			session.setAttribute("loc", "home.jsp");
			hresponse.sendRedirect("message.jsp");
		}else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
