package main.java.edu.scnu.action.securitycode;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller(value="securityCodeImageAction")
@Scope("prototype")
public class SecurityCodeImageAction extends ActionSupport implements SessionAware{
	//图片流  
    private ByteArrayInputStream imageStream; 
    //session域  
    private Map<String, Object> session;  
    public ByteArrayInputStream getImageStream() {  
        return imageStream;  
    }  
    public void setImageStream(ByteArrayInputStream imageStream) {  
        this.imageStream = imageStream;  
    }  
    public void setSession(Map<String, Object> session) {  
        this.session = session;  
    } 
    
    public String setChallenge(){
    	//如果开启Hard模式，可以不区分大小写  
        //String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();  
        //获取默认难度和长度的验证码  
        String securityCode = SecurityCode.getSecurityCode();  
        imageStream = SecurityImage.getImageAsInputStream(securityCode);  
        //放入session中  
        session.put("securityCode", securityCode); 
        return SUCCESS;  
    }
    
}
