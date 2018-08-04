package com.zuul.luo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class SignVaildFilter extends ZuulFilter{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	


	@Override
	public Object run() {
		Object object = null;
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestURI = request.getRequestURI();
		log.info((String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString())));
		log.info(request.getContentType());
		
		if (requestURI.contains("/v2/api-docs")) {
			ctx.setSendZuulResponse(true); 		// 对该请求进行路由
			ctx.setResponseStatusCode(200);
			ctx.set("isSuccess", true); 		// 设值，让下一个Filter看到上一个Filter的状态
			return null;
		}
		
		//object = validSign(ctx, request, requestURI);				//校验签名
		return object;

	}


	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
