package cn.bc.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * 记录请求日志
 *
 * @author dragon 2016-07-27
 */
@Provider
@Priority(Priorities.AUTHORIZATION)
public class LogRequestFilter implements ContainerRequestFilter {
	private static Logger logger = LoggerFactory.getLogger(LogRequestFilter.class);

	@Override
	public void filter(ContainerRequestContext c) throws IOException {
		if (logger.isDebugEnabled())
			logger.debug("{} {} Accept={}", c.getMethod(), c.getUriInfo().getAbsolutePath(), c.getHeaderString("Accept"));
	}
}