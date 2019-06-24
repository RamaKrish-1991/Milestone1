package com.wipro.bus.service;

import java.util.ArrayList;

import com.wipro.bus.bean.ScheduleBean;
import com.wipro.bus.dao.ScheduleDAO;
import com.wipro.bus.util.InvalidInputException;

public class Administrator {

	public String addSchedule(ScheduleBean scheduleBean)
	{
		String ret="";
		try
		{
			if(scheduleBean == null || scheduleBean.getSource()==null ||scheduleBean.getDestination()==null|| scheduleBean.getSource().equals("") || scheduleBean.getDestination().equals("")|| scheduleBean.getSource().length() < 2|| scheduleBean.getDestination().length() <2)
			{
				throw new InvalidInputException();
			}
		}
		catch(InvalidInputException e)
		{
			ret="INVALID INPUT";
			return ret;
		}
			if(scheduleBean.getSource().equals(scheduleBean.getDestination()))
				ret="Source and Destination Same";
			else
			{
				ScheduleDAO dao=new ScheduleDAO();
				String x=dao.generateID(scheduleBean.getSource(), scheduleBean.getDestination());
				scheduleBean.setScheduleId(x);
				ret=dao.createSchedule(scheduleBean);
			}
		
		return ret;
	}
	
	public ArrayList<ScheduleBean> viewSchedule(String source,String destination)
	{
		ScheduleDAO sDAO = new ScheduleDAO();
		ArrayList<ScheduleBean> sBean = sDAO.viewSchedule(source, destination);
		return sBean;
	}
}
