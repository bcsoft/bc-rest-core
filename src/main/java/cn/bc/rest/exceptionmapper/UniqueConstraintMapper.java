package cn.bc.rest.exceptionmapper;

import cn.bc.core.exception.UniqueConstraintException;
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
public class UniqueConstraintMapper implements ExceptionMapper<UniqueConstraintException> {
	private final static Logger logger = LoggerFactory.getLogger(UniqueConstraintMapper.class);

	@Override
	public Response toResponse(UniqueConstraintException e) {
		Response.ResponseBuilder b = Response.status(Response.Status.FORBIDDEN).type(MediaType.TEXT_PLAIN);
		if (e.getMessage() != null) {
			b.entity(e.getMessage());
		} else {
			logger.warn(null, e);
			b.entity(e.toString());
		}
		return b.build();
	}
}
