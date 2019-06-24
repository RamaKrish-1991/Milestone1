package com.wipro.bus.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wipro.bus.bean.ScheduleBean;
import com.wipro.bus.service.Administrator;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		if(operation.equals("newSchedule")){
			String s = addSchedule(request);
			RequestDispatcher rs; 
			if(s.equals("SUCCESS")){
				rs = request.getRequestDispatcher("Success.jsp");
				rs.forward(request,response);
			} else{
				rs = request.getRequestDispatcher("errorInserting.html");
				rs.forward(request,response);
			} 
		} else {
			ArrayList<ScheduleBean> aList = viewSchedule(request);
			RequestDispatcher rs; 
			if(aList == null){
				rs = request.getRequestDispatcher("displaySchedule.jsp");
				rs.forward(request,response);
			} else{
				rs = request.getRequestDispatcher("errorInserting.html");
				rs.forward(request,response);
			} 
		}
	}
	
	public String addSchedule(HttpServletRequest request){
		ScheduleBean sBean = new ScheduleBean();
		sBean.setSource(request.getParameter("source"));
		sBean.setDestination(request.getParameter("destination"));
		sBean.setStartTime(request.getParameter("startTime"));
		sBean.setArrivalTime(request.getParameter("endTime"));
		Administrator a = new Administrator();
		
		String status = a.addSchedule(sBean);
		return status;
	}
	
	public ArrayList<ScheduleBean> viewSchedule(HttpServletRequest request){
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		
		Administrator a = new Administrator();
		ArrayList<ScheduleBean> sList = a.viewSchedule(source, destination);
		return sList;
	}
	 


}
