package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebFilter(filterName = "EncodingFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
       //使用装饰者模式来修改getParameter方法
        HttpServletRequest request=(HttpServletRequest)req;
        //request.setCharacterEncoding("GBK");
        MyRequest myRequest=new MyRequest(request);
        myRequest.setCharacterEncoding("utf-8");
        chain.doFilter(myRequest, resp);
    }
}
class MyRequest extends HttpServletRequestWrapper{
       HttpServletRequest request;
    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request=request;
    }

}
