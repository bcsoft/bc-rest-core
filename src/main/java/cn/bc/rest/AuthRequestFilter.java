package cn.bc.rest;

import cn.bc.ContextHolder;
import cn.bc.core.exception.PermissionDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;

/**
 * 认证访问过滤器
 *
 * @author dragon 2016-07-27
 */
// @Provider
// @Priority(Priorities.AUTHENTICATION)
public class AuthRequestFilter implements ContainerRequestFilter {
	@Context
	private HttpServletRequest request;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		HttpSession session = request.getSession();
		cn.bc.Context context = (cn.bc.Context) session.getAttribute(cn.bc.Context.KEY);
		if (context == null) {
			throw new PermissionDeniedException("请先登陆系统！");
		} else {
			// 将系统上下文设置到线程变量
			cn.bc.Context holderContext = ContextHolder.get();
			if (holderContext == null || !context.equals(holderContext)) {// 如果还没有设置过才设置
				ContextHolder.set(context);
			}
		}
	}
}