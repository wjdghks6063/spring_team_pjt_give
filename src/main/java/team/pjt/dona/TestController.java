package team.pjt.dona;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
/*
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
*/
import command.member.MemberSave;
import common.Command;
import dao.Member_dao;
import dto.Memberkakao_dto;
import login.OAuthToken;
import mail.MailContent;
import mail.NewPassword;

@Controller
public class TestController {
	@RequestMapping("/Join" )
	public String Index(HttpServletRequest request) {
		String tell = request.getParameter("t_tell");
		request.setAttribute("t_tell", tell);
		return "member/join";
	}
	
	@RequestMapping("/Login")
	public String Login(HttpServletRequest request) {
		String id = request.getParameter("t_id");
		String found_id = request.getParameter("t_found_id");
		String found_id_2 = request.getParameter("t_found_id_2");
		request.setAttribute("t_id",id);
		request.setAttribute("t_found_id",found_id);
		request.setAttribute("t_found_id_2",found_id_2);
		System.out.println(found_id);
		return "member/login";
	}
	
	@RequestMapping("SHA256")
	public void SHA256(HttpServletRequest request, HttpServletResponse response){
		String password=request.getParameter("t_password");
		System.out.println(password);
		String pw="";
		Member_dao dao = new Member_dao();
		PrintWriter out =null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			pw=dao.encryptSHA256(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(pw);
	}
	@RequestMapping(value="/MemberLogin", method= {RequestMethod.GET, RequestMethod.POST})
	public String MemberLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		String pw = request.getParameter("t_pw");

	
		try {
			pw=dao.encryptSHA256(pw);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String name = dao.getLoginName(id,pw);
		String msg="";
		String url="";
		
		if(!name.equals("")) {
			msg=name+"??? ???????????????";
			url="/";
			HttpSession session = request.getSession();   //????????? ?????????. ????????????
			session.setAttribute("session_name", name);
			session.setAttribute("session_id", id);
			if(id.equals("manager") || id.equals("manager2") || id.equals("manager3")){
				session.setAttribute("session_level", "top");
			}else{
				session.setAttribute("session_level", "");
			}
								
			session.setMaxInactiveInterval(60 * 60 * 10);
			int result = 1;
					request.setAttribute("t_result", result);
			msg = name+"??? ????????? ???????????????.";
			url = "/";
		}else {
			int result = 0;
			request.setAttribute("t_result", result);
			
			msg = "???????????? ??????????????? ?????? ??????????????????.";
			url = "/Login";
			
		}
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
		request.setAttribute("t_id", id);
		return "common_alert_page";
	}
	
	@RequestMapping("/Logout")
	public String Logout(HttpServletRequest request) {
		HttpSession session = request.getSession();		
		String sessionName = (String)session.getAttribute("session_name");	
		session.invalidate(); //???????????????
		//session??? ????????? ??? request??? ????????? ?????????????????? session id name ?????? ??? ???????????? ?????? 
		
		request.setAttribute("t_msg", sessionName+"??? ???????????? ???????????????");
		request.setAttribute("t_url", "/");
		request.setAttribute("t_result", 1);
		return "common_alert_page";
	}
	
	@RequestMapping("/Find_id")
	public String Find_pw(HttpServletRequest request) {
		return "member/find_id";
	}
	
	
	@RequestMapping("/Find_pw_1")
	public String Find_pw_1(HttpServletRequest request) {
		String id = request.getParameter("t_id");
		String found_id = request.getParameter("t_found_id");
		String found_id_2 = request.getParameter("t_found_id_2");
		String msg = request.getParameter("t_msg");
		request.setAttribute("t_id",id);
		request.setAttribute("t_found_id",found_id);
		request.setAttribute("t_found_id_2",found_id_2);
		request.setAttribute("t_msg",msg);
		return "member/find_pw_1";
	}
	
	@RequestMapping("MemberEmailFindpw")
	public void MemberEmailFindpw(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html;charset=utf-8");   //?????? ?????? ??? ????????? ????????? ?????????? HTML?????? utf8??? ????????? ????????? response???????????? setContenttype??? ????????? ??????????????? ???
		Member_dao dao = new Member_dao();
		String email = request.getParameter("t_email");
		String id = request.getParameter("t_id");
		String tell = request.getParameter("t_tell");
		String name = dao.getEmailFindPw(id,email,tell);
		PrintWriter out =null;
		String msg="";
		String url="";
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(name.equals("")) {
			msg ="????????? ????????? ????????????";
			url ="Find_pw_1";
		} else {
			boolean mail_ok_gubun = true;
			MailContent ms = new MailContent();
			String password = new NewPassword().getNewPassword(5);
			String mailSet_Server="smtp.naver.com";
			String mailSet_ID="chlchlrnsrns"; // ????????? ?????? ?????????
			String mailSet_PW="Q54MFWQE46RF"; // ????????? ?????? ????????????
			
			String mailFromName ="IseeU ???????????? ??????";
			String mailFromAddress ="chlchlrnsrns@naver.com";  // ????????? ?????? ???????????????

			String mailTo   = email;
			String mailTitle =" IseeU ???????????? ??????.";
			String content = 	
			" <table width='400' height='200' border='0' > "+
			" <tr> "+
			" 	<td colspan='2' height='100' align='center'>"+name+"??? ???????????? ??????</td> "+
			" </tr> "+
			" <tr>"+
			" 	<td width='120' align='center'>??? ??? ??? ???</td> "+
			" 	<td width='280' align='left'>"+
			"        <font size='5' color='red'>"+password+"</font>?????????</td> "+
			" </tr> "+
			" </table> ";
	
			ms.setMail(mailSet_Server, mailSet_ID, mailSet_PW);  
			try {
				ms.sendMail2(mailFromName, mailFromAddress,mailTo, mailTitle, content, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				mail_ok_gubun=false;
				e.printStackTrace();
			} 
			if(mail_ok_gubun) { 
				url ="Login";
				msg =" ???????????? ???????????????.";
				try {
					password = dao.encryptSHA256(password);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int result = dao.setPassword(id,password);
			} else {
				url ="Find_pw_1";
				msg =" ???????????? ?????????????????????.";
			}
		}
		
		
		request.setAttribute("t_msg", msg);
		request.setAttribute("t_url", url);
		out.print(msg);
	}
	
	
	@RequestMapping("MemberIdCheck")
	public void MemberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");   //?????? ?????? ??? ????????? ????????? ?????????? HTML?????? utf8??? ????????? ????????? response???????????? setContenttype??? ????????? ??????????????? ???
		Member_dao dao = new Member_dao();
		String id = request.getParameter("t_id");
		int count = dao.getIdCount(id);
		PrintWriter out =null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = count ==1 ? "<i class=\"fas fa-times\"></i>" : "<i class=\"fas fa-check\"></i>";
		
			out.print(msg);
	}
	
	@RequestMapping("MemberTellCheck")
	public void MemberTellCheck(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");   //?????? ?????? ??? ????????? ????????? ?????????? HTML?????? utf8??? ????????? ????????? response???????????? setContenttype??? ????????? ??????????????? ???
		Member_dao dao = new Member_dao();
		String tell = request.getParameter("t_tell");
		int count = dao.getTellCount(tell);
		PrintWriter out =null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = count ==1 ? "<i class=\"fas fa-times\"></i>" : "<i class=\"fas fa-check\"></i>";
		
			out.print(msg);
	}
	
	@RequestMapping("MemberEmailCheck")
	public void MemberEmailCheck(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");   //?????? ?????? ??? ????????? ????????? ?????????? HTML?????? utf8??? ????????? ????????? response???????????? setContenttype??? ????????? ??????????????? ???
		Member_dao dao = new Member_dao();
		String email = request.getParameter("t_email");
		int count = dao.getEmailCount(email);
		PrintWriter out =null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String msg = count ==1 ? "????????? ??????" : "????????????";
		
			out.print(msg);
	}
	
	
	@RequestMapping("/MemberSave")
	public String MemberSave(HttpServletRequest request) {
		Command member = (Command) new MemberSave();
		member.execute(request);
		return "common_alert_page";
	}
/*	
	@RequestMapping("Callback")
	public String Callback(HttpServletRequest request , String code) {
				
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); 
		
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>(); 
		params.add("grant_type","authorization_code");
		params.add("client_id","ec4801dd314be6e382293141807e4025");
		params.add("redirect_uri","https://fa19-115-93-111-2.ngrok.io/Callback");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params,headers); 
		
		ResponseEntity response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest, 
				String.class 
				);

		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue((String)response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
			
		
		RestTemplate rt2 = new RestTemplate();
		
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); 
		
	
	
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = 
				new HttpEntity<>(headers2); 
		
		
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,
				String.class 
				);
		
    	ObjectMapper objectMapper2 = new ObjectMapper();
		Memberkakao_dto kakao_dto = null;
		try {
			kakao_dto = objectMapper2.readValue((String)response2.getBody(), Memberkakao_dto.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("?????????"+kakao_dto.getId());
		System.out.println("?????????"+kakao_dto.getKakao_account().getEmail());
		System.out.println("??????"+kakao_dto.getKakao_account().getGender());
		System.out.println("?????????"+kakao_dto.properties.nickname);
		System.out.println("?????????"+kakao_dto.kakao_account.profile);
		System.out.println("??????"+kakao_dto.properties.getProfile_image());
		System.out.println("??????"+kakao_dto.getKakao_account().birthday);
		
		request.setAttribute("t_kakao_dto", kakao_dto);
		
		return "member/home";
		//return "acesstoken "+(String)response2.getBody();
		
	}
*/	
}
