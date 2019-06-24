package com.wipro.bus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.bus.bean.ScheduleBean;
import com.wipro.bus.util.DBUtil;

public class ScheduleDAO {
	public String createSchedule(ScheduleBean scheduleBean)
	{
		String result=null;

		try 
		{
			int i=0;
			
			Connection conn=DBUtil.getDBConnection();
			PreparedStatement ps=conn.prepareStatement("INSERT INTO SCHEDULE_TBL VALUES(?,?,?,?,?)");
			ps.setString(1, scheduleBean.getScheduleId());
			ps.setString(2, scheduleBean.getSource());
			ps.setString(3, scheduleBean.getDestination());
			ps.setString(4, scheduleBean.getStartTime());
			ps.setString(5, scheduleBean.getArrivalTime());
			i=ps.executeUpdate();
			if(i==1)
				result="SUCCESS";
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

public String generateID(String source,String destination)
{
	Connection conn=DBUtil.getDBConnection();
	int x=0;
	String res=null;
	try
	{
		PreparedStatement ps=conn.prepareStatement("select schedule_seq.nextval from dual");
		ResultSet rst=ps.executeQuery();
		if(rst.next()) 
		{
			x=rst.getInt(1);
		}
		res=source.substring(0, 2)+destination.substring(0, 2)+x;
		
	} 
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return res;
			
}

public ArrayList<ScheduleBean> viewSchedule(String source,String destination)
{
	try
	{
		Connection conn=DBUtil.getDBConnection();
		ScheduleBean sb=null;
		ArrayList<ScheduleBean> al=new ArrayList<ScheduleBean>();
		PreparedStatement ps=conn.prepareStatement("select * from schedule_tbl where source=? and destination=?");
		ps.setString(1, source);
		ps.setString(2, destination);
		ResultSet rst=ps.executeQuery();
		int counter=0;
		while(rst.next())
		{
			sb=new ScheduleBean();
			sb.setScheduleId(rst.getString(1));
			sb.setSource(rst.getString(2));
			sb.setDestination(rst.getString(3));
			sb.setArrivalTime(rst.getString(4));
			sb.setArrivalTime(rst.getString(5));
			al.add(sb);
			counter++;
		}
		if(counter>0)
			return al;
		else
			return null;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return null;
}

}