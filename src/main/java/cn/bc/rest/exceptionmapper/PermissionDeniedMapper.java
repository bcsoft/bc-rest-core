package cn.bc.rest.exceptionmapper;

import cn.bc.core.exception.PermissionDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author dragon 2016-07-21
 */
@Provider
public class PermissionDeniedMapper implements ExceptionMapper<PermissionDeniedException> {
	private final static Logger logger = LoggerFactory.getLogger(PermissionDeniedMapper.class);

	@Override
	public Response toResponse(PermissionDeniedException e) {
		Response.ResponseBuilder b = Response.status(Response.Status.FORBIDDEN).type(MediaType.TEXT_PLAIN);
		if (e.getMessage() != null) {
			b.entity(e.getMessage());
		} else {
			logger.warn(null, e);
			b.entity("Permission Denied");
		}
		return b.build();
	}
}
