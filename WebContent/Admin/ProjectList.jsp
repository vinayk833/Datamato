<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
 <%@ page import="com.login.util.DBConnection" %>
 <% 
 Connection con = null;
 con = DBConnection.createConnection();
	try{      
		 String s[]=null;
		
	        response.setContentType("text/html");
	   
	     //Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/customers","root","Datamato@123");
	     System.out.println("test:"+con);
	     Statement st=con.createStatement();
	     ResultSet rs = st.executeQuery("select ProjName from myproject;");
		
	    	List li = new ArrayList();
	    
			while(rs.next()) 
 			{ 			    
 			    li.add(rs.getString(1));
 			}  
			
			String[] str = new String[li.size()];			
			Iterator it = li.iterator();
			
			int i = 0;
			while(it.hasNext())
			{
				String p = (String)it.next();	
				str[i] = p;
				i++;
			}
		
			//jQuery related start		
				String query = (String)request.getParameter("q");
				
				int cnt=1;
				for(int j=0;j<str.length;j++)
				{
					if(str[j].toUpperCase().startsWith(query.toUpperCase()))
					{
						out.print(str[j]+"\n");
						if(cnt>=5)
							break;
						cnt++;
					}
				}
			//jQuery related end	
		 
			
 		rs.close(); 
 		st.close(); 
		con.close();

		    } 
		catch(Exception e){ 
 			e.printStackTrace(); 
 		}
	finally{
		con.close();
		System.out.println("Disconnected in UI");
	}

//www.java4s.com
 %>