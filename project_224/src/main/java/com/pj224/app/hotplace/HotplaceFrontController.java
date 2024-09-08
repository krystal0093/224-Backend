package com.pj224.app.hotplace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pj224.app.Result;
import com.pj224.app.dao.HotplaceDAO;
import com.pj224.app.dto.HotplaceDTO;
import com.pj224.app.dto.LikeDTO;

/**
 * Servlet implementation class HotplaceFrontController
 */
//@WebServlet("/HotplaceFrontController")
public class HotplaceFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotplaceFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체 URI에서 ContextPath를 제외시킨 부분만 변수에 저장(분기처리할 때 비교할 변수로 사용)
		String target = request.getRequestURI().substring(request.getContextPath().length());
		
		System.out.println(target);
		
	    HotplaceDAO hotplaceDAO = new HotplaceDAO();

	    List<HotplaceDTO> hotplaceList = null;
	    List<LikeDTO> likeList = null;
	    
	    HttpSession session = request.getSession();
	    Integer memberNumber = (Integer) session.getAttribute("memberNumber");
	    if (memberNumber == null) {
	    	memberNumber = 0;
	        return;
	    }
	    
	    
	    int hotplaceNumber;
	    int page;
	    String returnUrl;

		switch (target) {
		case "/hotplace/hotplace-euljiro.hp":
            hotplaceList = hotplaceDAO.showInfo("을지로입구역");
            likeList = hotplaceDAO.checkLike(2, hotplaceList.get(0).getHotplaceNumber());

		    request.setAttribute("hotplaceList", hotplaceList);
		    request.setAttribute("likeList", likeList);
            request.getRequestDispatcher("/app/hotplace/hotplace-euljiro.jsp").forward(request, response);
            break;
		case "/hotplace/hotplace-gangnam.hp":
		    hotplaceList = hotplaceDAO.showInfo("강남역");
		    likeList = hotplaceDAO.checkLike(memberNumber, hotplaceList.get(0).getHotplaceNumber());

		    request.setAttribute("hotplaceList", hotplaceList);
		    request.setAttribute("likeList", likeList);
		    request.getRequestDispatcher("/app/hotplace/hotplace-gangnam.jsp").forward(request, response);
		    break;
		case "/hotplace/hotplace-hongdae.hp":
            hotplaceList = hotplaceDAO.showInfo("홍대입구역");
            likeList = hotplaceDAO.checkLike(2, hotplaceList.get(0).getHotplaceNumber());
            
		    request.setAttribute("hotplaceList", hotplaceList);
		    request.setAttribute("likeList", likeList);
            request.getRequestDispatcher("/app/hotplace/hotplace-hongdae.jsp").forward(request, response);
            break;
		case "/hotplace/hotplace-jamsil.hp":
            hotplaceList = hotplaceDAO.showInfo("잠실역");
            likeList = hotplaceDAO.checkLike(2, hotplaceList.get(0).getHotplaceNumber());
            
		    request.setAttribute("hotplaceList", hotplaceList);
		    request.setAttribute("likeList", likeList);
            request.getRequestDispatcher("/app/hotplace/hotplace-jamsil.jsp").forward(request, response);
            break;
		case "/hotplace/hotplace-moonrae.hp":
            hotplaceList = hotplaceDAO.showInfo("문래역");
            likeList = hotplaceDAO.checkLike(2, hotplaceList.get(0).getHotplaceNumber());
            
		    request.setAttribute("hotplaceList", hotplaceList);
		    request.setAttribute("likeList", likeList);
            request.getRequestDispatcher("/app/hotplace/hotplace-moonrae.jsp").forward(request, response);
            break;
		case "/hotplace/hotplace-seongsu.hp":
            hotplaceList = hotplaceDAO.showInfo("성수역");
            likeList = hotplaceDAO.checkLike(2, hotplaceList.get(0).getHotplaceNumber());
            
		    request.setAttribute("hotplaceList", hotplaceList);
		    request.setAttribute("likeList", likeList);
            request.getRequestDispatcher("/app/hotplace/hotplace-seongsu.jsp").forward(request, response);
            break;
		case "/hotplace/pick.hp":
	        hotplaceNumber = Integer.parseInt(request.getParameter("hotplaceNumber"));
	        page = Integer.parseInt(request.getParameter("page"));
	        String[] str1 = request.getParameter("returnUrl").split("/");
	        returnUrl = str1[str1.length - 1].replace("jsp", "hp") + "?page=" + page;
            hotplaceDAO.pickHotplace(new LikeDTO(hotplaceNumber, memberNumber));
            response.sendRedirect(returnUrl); 
			break;
		case "/hotplace/unpick.hp":
	        hotplaceNumber = Integer.parseInt(request.getParameter("hotplaceNumber"));
	        page = Integer.parseInt(request.getParameter("page"));
	        String[] str2 = request.getParameter("returnUrl").split("/");
	        returnUrl = str2[str2.length - 1].replace("jsp", "hp") + "?page=" + page;
            hotplaceDAO.unpickHotplace(new LikeDTO(hotplaceNumber, memberNumber));
            response.sendRedirect(returnUrl); 
			break;


		}

	}

}
 