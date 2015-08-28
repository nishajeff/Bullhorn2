

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bullhorn;
import model.DBUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String message="";
	String message1="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		message1="";
		String word=request.getParameter("word");
		if(!word.equalsIgnoreCase("")){
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		String q="select b from Bullhorn b  where upper(b.post) like upper('%"+word+"%')";
		TypedQuery<Bullhorn>bq =em.createQuery(q,Bullhorn.class);
		List<Bullhorn> list=bq.getResultList();
		message1+="  Search Results:<br> ";
		for(Bullhorn temp:list)
			message1+=temp.getPost()+"<br>";
		request.setAttribute("message1", message1);
		getServletContext().getRequestDispatcher("/GetPost.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);		
		HttpSession session1 = request.getSession(true);		
		String currentpage=request.getParameter("currentpage");
		message="";
		try{
			
			//message+="<h3>List Of Posts Entered:</h3>";
			//String name=request.getParameter("name");
			if(currentpage.equalsIgnoreCase("EnterPost")){
				int uid =Integer.parseInt((String) session.getAttribute("userid"));
				String name= (String) session1.getAttribute("name");
				String post=request.getParameter("post");			
				model.Bullhorn user=new model.Bullhorn();
				user.setName(name);
				user.setPost(post);			
				user.setPersonId((BigDecimal.valueOf((long)uid)));	
				//BigDecimal lgid=BigDecimal.valueOf((long)uid);
				if(!post.equalsIgnoreCase(""))	
				model.DBUtil.insert(user);
				message+="Successfully Posted !Thank You.";
			}
			else if(currentpage.equalsIgnoreCase("GetPost")){
				EntityManager em=DBUtil.getEmFactory().createEntityManager();
				String q="select b from Bullhorn b  order by b.postId desc";
				TypedQuery<Bullhorn>bq =em.createQuery(q,Bullhorn.class);
				List<Bullhorn> list=bq.getResultList();
				for(Bullhorn temp:list)
					message+="User Name: "+temp.getName()+"  Post: "+temp.getPost()+"<br>";
			}
			request.setAttribute("message", message);
			if(currentpage.equalsIgnoreCase("EnterPost"))				
    			getServletContext().getRequestDispatcher("/EnterPost.jsp").forward(request, response);
			else if(currentpage.equalsIgnoreCase("GetPost"))
				getServletContext().getRequestDispatcher("/GetPost.jsp").forward(request, response);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
