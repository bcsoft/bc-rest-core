package cn.bc.rest.exceptionmapper;

import cn.bc.core.exception.CoreException;
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
public class CoreExceptionMapper implements ExceptionMapper<CoreException> {
  private final static Logger logger = LoggerFactory.getLogger(CoreExceptionMapper.class);

  @Override
  public Response toResponse(CoreException e) {
    Response.ResponseBuilder b = Response.serverError().type(MediaType.TEXT_PLAIN);
    if (e.getMessage() != null) {
      b.entity(e.getMessage());
    } else {
      logger.warn(null, e);
      b.entity(e.toString());
    }
    return b.build();
  }
}
