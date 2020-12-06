package br.net.advisor;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Hw
 */
@WebServlet(name = "hw", urlPatterns = { "/hw" })
public class Hw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int counter;
	Map<Integer, Object> data = new HashMap<Integer, Object>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		this.data.clear();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map hm = new HashMap<Integer, Object>();
		Random rand = new Random();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		hm.put("now", new Date());
		hm.put("secret", rand.nextInt());
		hm.put("hash", new Date().hashCode());
		
		addCounter();
		this.data.put(this.counter, hm);

		
		// convert map to JSON string
	    String json = new Gson().toJson(this.data);
		
		response.getWriter().append(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Enumeration<String> params = request.getParameterNames();
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		
		while (params.hasMoreElements()) {
		   String p = params.nextElement();
		   //System.out.println(p + " " + request.getParameter(p));
		   hm.put(p, request.getParameter(p));
		}
		
		hm.put("now", new Date());
		
		this.addCounter();
		this.data.put(this.counter, hm);


		//System.out.println(request.getParameterValues());
        //String jsonMessage;
		
		//Object o = new Gson().fromJson(jsonMessage, Object.class);
		doGet(request, response);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private synchronized void addCounter() {
		this.counter += 1;
	}

}
