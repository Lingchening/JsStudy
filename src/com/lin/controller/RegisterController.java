package com.lin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lin.domain.User;
import com.lin.domain.UserExample;
import com.lin.domain.UserExample.Criteria;
import com.lin.service.IRegisterService;

@Controller
public class RegisterController {
	private static Logger logger = Logger.getLogger(RegisterController.class);    
	@Resource
	private IRegisterService registerService;	
	
	@RequestMapping({"/register","/"})
	public String register(){  
		return "register";
	}
	@RequestMapping(value="/register/checkUserName",method = RequestMethod.POST)
	public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userName=(String)request.getParameter("userName");			
		//�����û����Ƿ����
		UserExample userExample=new UserExample();
		Criteria conditionCri = userExample.createCriteria();
		conditionCri.andUserNameEqualTo(userName);		
	    int num=registerService.countByExample(userExample);
	    //�û����Ƿ���ڵı�־
	    boolean flag=false;
	    if(num>0){
	    	flag=true;
	    }		
		//������ת����json
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("flag", flag);  		  
		String json = JSONObject.fromObject(map).toString(); 		
		//�����ݷ���
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
	}
	
	@RequestMapping(value="/register/checkEmail",method = RequestMethod.POST)
	public String checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String email=(String)request.getParameter("email");			
		//���������Ƿ����
		UserExample userExample=new UserExample();
		Criteria conditionCri = userExample.createCriteria();
		conditionCri.andUserEmailEqualTo(email);		
	    int num=registerService.countByExample(userExample);
	    //�û����Ƿ���ڵı�־
	    boolean flag=false;
	    if(num>0){
	    	flag=true;
	    }		
		//������ת����json
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("flag", flag);  		  
		String json = JSONObject.fromObject(map).toString(); 		
		//�����ݷ���
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();
		return null;
	}
	@RequestMapping(value="/register/successed")
	public ModelAndView  successed(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username=(String)request.getParameter("username");	
		String email=(String)request.getParameter("email");		
		String password=(String)request.getParameter("password");
		if(username==null||email==null||password==null){
			return new ModelAndView("redirect:/register"); 
		}
		//�����û��������ݿ�
		User user=new User();
		user.setUserName(username);
		user.setUserEmail(email);
		user.setUserPassword(password);
		registerService.insert(user);		
		//������ת����
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("username", username);  
		map.put("email", email);  
		map.put("password", password);  
/*		String json = JSONObject.fromObject(map).toString(); 		
		//�����ݷ���
		response.setCharacterEncoding("UTF-8");
		response.flushBuffer();
		response.getWriter().write(json);
		response.getWriter().flush();  
		response.getWriter().close();*/
		return new ModelAndView("successed",map);  
	}
	

}
