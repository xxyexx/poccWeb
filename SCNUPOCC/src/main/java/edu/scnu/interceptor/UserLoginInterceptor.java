package main.java.edu.scnu.interceptor;

import javax.servlet.http.HttpServletRequest;

import main.java.edu.scnu.entity.User;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Controller(value="userLoginInterceptor")
@Scope("prototype")
public class UserLoginInterceptor extends AbstractInterceptor {
	private String result;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) WebUtils.getSessionAttribute(request, "User");
		if(user!=null){//已登录
			result = invocation.invoke();
		}else{//未登录
			System.out.println("未登录,被拦截");
			result = Action.LOGIN;
		}
		return result;
	}
	
	
}
