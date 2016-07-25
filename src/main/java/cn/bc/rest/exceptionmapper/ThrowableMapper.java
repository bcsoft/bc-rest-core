package cn.bc.rest.exceptionmapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 所有没有处理的异常
 *
 * @author dragon 2016-07-21
 */
@Provider
public class ThrowableMapper implements ExceptionMapper<Throwable> {
	private final static Logger logger = LoggerFactory.getLogger(ThrowableMapper.class);

	@Override
	public Response toResponse(Throwable e) {
		// jav-rs 的异常略过
		if (e instanceof WebApplicationException) return ((WebApplicationException) e).getResponse();
		if (e instanceof ProcessingException) throw (ProcessingException) e;

		Response.ResponseBuilder b = Response.serverError().type(MediaType.TEXT_PLAIN);
		String msg = getDeepMessage(e);
		if (msg != null) {
			b.entity(msg);
		} else {
			logger.warn(null, e);
			b.entity(e.toString());
		}
		return b.build();
	}

	/**
	 * 递归获取最底层的异常信息
	 *
	 * @param e 异常
	 * @return 最底层的异常信息
	 */
	public static String getDeepMessage(Throwable e) {
		if (e == null) return null;
		if (e.getCause() == null) return e.getMessage();

		return getDeepMessage(e.getCause());
	}
}