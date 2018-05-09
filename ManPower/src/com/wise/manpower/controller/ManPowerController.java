package com.wise.manpower.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wise.manpower.dao.AddressDAO;
import com.wise.manpower.dao.BidDAO;
import com.wise.manpower.dao.ForceDAO;
import com.wise.manpower.dao.ServiceTypeDAO;
import com.wise.manpower.dao.SubServiceTypeDAO;
import com.wise.manpower.dao.UserDAO;
import com.wise.manpower.dao.WorkDAO;
import com.wise.manpower.dto.Address;
import com.wise.manpower.dto.Bid;
import com.wise.manpower.dto.Force;
import com.wise.manpower.dto.ServiceType;
import com.wise.manpower.dto.User;
import com.wise.manpower.dto.Work;
/**
 * Servlet implementation class ManPowerController
 */
@WebServlet("/ManPowerController")
public class ManPowerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
    	public ManPowerController() {
    		super();
        // TODO Auto-generated constructor stub
    }
 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.getWriter().append("Served at : ").append(request.getContextPath());
		 String action = request.getParameter("action");
		 if(null == action) {
			 List<ServiceType> serviceTypeList = getServiceTypes();
			 request.setAttribute("serviceTypesList", serviceTypeList);
			 request.getRequestDispatcher("./home.jsp").forward(request, response);
			 //request.getRequestDispatcher("./userHome.jsp").forward(request, response);
		 } else if ( action.equals("logout") ){
			 System.out.println("LOGOUT");
			 request.getRequestDispatcher("./logout.jsp").forward(request, response);
		 }
	 }
	 /**
	  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	  */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // TODO Auto-generated method stub
		 //doGet(request, response);
		String action = request.getParameter("action");
		RequestDispatcher dispatcher = null;
		System.out.println(action);
		if(action.equals("login")){
		String role = request.getParameter("role");
		if(role.equals("user")){
			if(UserLogin(request,response)){
				List<ServiceType> serviceTypeList = getServiceTypes();
			    request.setAttribute("serviceTypesList", serviceTypeList);
			}
		}
		else if(role.equals("force")){
			if(ForceLogin(request , response)){
				//request.getRequestDispatcher("./forceHome.jsp").forward(request, response);	
				System.out.println("True");
			}
		}
		}
		 else if(action.equals("postWork")){
			System.out.println("hello");
			if( PostWork(request,response)){
				request.getRequestDispatcher("./userHome.jsp").forward(request, response);	
			}
			else{
				request.getRequestDispatcher("./userHome.jsp").forward(request, response);
			}	 
		 }
		 else if(action.equals("userregister")){
			 if(RegisterUser(request)){
				 User user = new UserDAO().get(request.getParameter("email"));
				 if(RegisterAddress(request,user.getUserId())){
					 request.getRequestDispatcher("./userHome.jsp").forward(request, response);
					 //System.out.println("Success");
				 }   
			 }
		 }else if(action.equals("forceregister")){
			 if(RegisterUser(request)){
				 User user = new UserDAO().get(request.getParameter("email"));
				 if(RegisterForce(request,user.getUserId())){
					 if(RegisterAddress(request,user.getUserId())){
						 request.getRequestDispatcher("./forceHome.jsp").forward(request, response);
					 }
				 } 
			 }
		//	 System.out.println(action);
		 }else if(action.equals("Bid")){
			 bidByForce(request,response);
		 }
		 else if(action.equals("acceptBid")){
			 Work work = new Work();
			 Bid bid = new Bid();
			 String id1 = request.getParameter("action2");
			 String id = request.getParameter("action1");
			 System.out.println(id);
			 work.setWorkId(Integer.parseInt(id));
			 if(new WorkDAO().workUpdate(Integer.parseInt(id)) != 0 && new BidDAO().bidUpdate(Integer.parseInt(id1)) != 0)
				 request.getRequestDispatcher("./userHome.jsp").forward(request, response);
		 }
	 }	 
	  private boolean bidByForce(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		  Object ob = request.getSession().getAttribute("logg");
		  Force force = (Force)ob;
		  Bid bid = new Bid();
		  bid.setBidAmount(Integer.parseInt(request.getParameter("amount")));
		 // System.out.println(request.getParameter("amount"));
		  //System.out.println(force.getForceId());
		  bid.setForceId(force.getForceId());
		  String action1 = request.getParameter("action1");
		  bid.setWorkId(Integer.parseInt(action1));
		  System.out.println(action1);
		  if(new BidDAO().insert(bid) != 0)
		    return true;
		  return false;
	}
	private List<ServiceType> getServiceTypes()  {
	       	List<ServiceType> serviceTypesList = new ServiceTypeDAO().getAll();
	        for (ServiceType serviceType : serviceTypesList) {
	            serviceType.setSubServiceType(new SubServiceTypeDAO().getAllSubServices(serviceType.getServiceTypeId()));
	        }
	        return serviceTypesList;
	    }
	private List<Work> getWorks(HttpServletRequest request , HttpServletResponse response, int userId) throws ServletException, IOException  {
       	List<Work> worksList = new WorkDAO().getWorkByUser(userId);
       	System.out.println(worksList);
       	if(worksList == null){
       		//System.out.println("YES");
       		request.getRequestDispatcher("./userHome.jsp").forward(request, response);				 
       	}else{
       		//System.out.println("NO");
       		for (Work work : worksList) {
       			work.setBid(new BidDAO().getBidsByWorkId(work.getWorkId()));
       			//workList.add(work);
        }
        System.out.println("Hello"+worksList);
       	}
        return worksList;
    }
	 private boolean UserLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 String email= request.getParameter("email");
		 String password = request.getParameter("password");
		 RequestDispatcher dispatcher = null;		 
		 UserDAO userDao = new UserDAO();
		 User user = new User();
		 user = userDao.get(email);
		 request.getSession().setAttribute("logged", user);	 
		 if(user.getPassword().equals(password)){
			 //System.out.println("Yes"); 
			 List<ServiceType> serviceTypeList = getServiceTypes();
			 request.setAttribute("serviceTypesList", serviceTypeList);
			 List<Work> workList = getWorks(request, response, user.getUserId());
			 if(workList != null){
				 request.setAttribute("workList", workList);
				 request.getRequestDispatcher("./userHome.jsp").forward(request, response);		
				 return true;
			 }	 
			 else{
			 request.setAttribute("error message", "No works posted by user");
			 return true;
			 }	 
		 }
		 return false;
	 }
	 private boolean ForceLogin(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		 String email= request.getParameter("email");
		  String password = request.getParameter("password");
		  RequestDispatcher dispatcher = null;
		  UserDAO userDao = new UserDAO();
		  User user = new User();
		  user = userDao.get(email);
		  ForceDAO forceDao = new ForceDAO();
		  Force force = new Force(); 
		  force = forceDao.get(user.getUserId());
		  request.getSession().setAttribute("logg", force);
		 if(user.getUserId()== force.getForceId()){
			   if(user.getPassword().equals(password)){
				// System.out.println("hello");
				  // System.out.print(force.getSubServiceId());
				 List<Work> forceWorkList = getWorksBySubServiceList(force.getSubServiceId());
				 //System.out.println(getWorksBySubServiceList(2));
				 request.setAttribute("forceWorkList",forceWorkList);
				 request.getRequestDispatcher("./forceHome.jsp").forward(request, response);				 
				 return true;
			 }else{
				// System.out.println("False");
				 request.getRequestDispatcher("./home.jsp").forward(request, response);	 
			 }
			  // request.getRequestDispatcher("./home.jsp").forward(request, response);	 
		 }
		 return false;
		 }
	 private boolean RegisterAddress(HttpServletRequest request, int userId){
		 Address address = new Address();
		 address.setUserId(userId);
		 address.setDoorNumber(request.getParameter("doorNumber"));
		 address.setStreet(request.getParameter("street"));
		 address.setCity(request.getParameter("city"));
		 address.setState(request.getParameter("state"));
		 address.setPostalCode(Integer.parseInt(request.getParameter("postalCode")));
		 address.setCountry(request.getParameter("country"));
		 request.getSession().setAttribute("address", address);
		 if(new AddressDAO().insert(address) != 0)
			 return true;
		 return false;
	 	} 
	 protected boolean RegisterUser(HttpServletRequest request){
		 User user = new User();
		 user.setFirstName(request.getParameter("firstName"));
		 user.setLastName(request.getParameter("lastName"));
		 user.setEmail(request.getParameter("email"));
		 user.setPassword(request.getParameter("password"));
		 user.setPhone(request.getParameter("phoneNumber"));
		 if(new UserDAO().insert(user) != 0)
			 return true;
		 return false;
	 }
	 protected boolean RegisterForce(HttpServletRequest request, int userId){
		 Force force = new Force();
		 ForceDAO forceDao = new ForceDAO();
		 force.setForceId(userId);
		 force.setExperience(Integer.parseInt(request.getParameter("experience")));
		 force.setCostPerHour(Double.parseDouble(request.getParameter("costPerHour")));
		 force.setSubServiceId(Integer.parseInt(request.getParameter("subserviceType")));	 
		 if(forceDao.insert(force) != 0){
			 return true;
		 }
		 return false;
	 }
	 protected boolean PostWork(HttpServletRequest request, HttpServletResponse response){
		  //String subServiceTypeId = request.getParameter("subServiceType");
		  //String description = request.getParameter("description");
		  Object ob = request.getSession().getAttribute("logged");
		  User user = (User)ob;
		  //UserDAO userDao = new UserDAO();
		  //User user = new User();
		  //user = userDao.get(email);
		  //System.out.println(user.getUserId());
		  //System.out.println(+user.getUserId()+user.getFirstName()+user.getEmail()+user.getPassword()); 
		  Work work = new Work();
		  request.getSession().setAttribute("work", work.getWorkId());	 
		  work.setUserId(user.getUserId());
		  work.setDescription(request.getParameter("description"));
		  work.setSubServiceTypeId(Integer.parseInt(request.getParameter("subserviceType")));
		  //System.out.println(Integer.parseInt(request.getParameter("subserviceType"));
		  work.setDate(new Date());
		  if(new WorkDAO().insert(work) != 0){
			  System.out.println("True");
			  return true;
		  }else
			  System.out.println("False");
		  		return false;
	}
	private List<Work> getWorksBySubServiceList(int subServiceId){
		List<Work> forceWorkList = new ArrayList<Work>();
		forceWorkList = new WorkDAO().getWorksForce(subServiceId);
		System.out.println(forceWorkList);
		return forceWorkList;
	}
}