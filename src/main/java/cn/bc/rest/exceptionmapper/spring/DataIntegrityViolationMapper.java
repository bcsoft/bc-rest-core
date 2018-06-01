package cn.bc.rest.exceptionmapper.spring;

import cn.bc.rest.exceptionmapper.ThrowableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author dragon 2016-07-21
 */
@Provider
public class DataIntegrityViolationMapper implements ExceptionMapper<DataIntegrityViolationException> {
  private final static Logger logger = LoggerFactory.getLogger(DataIntegrityViolationMapper.class);

  @Override
  public Response toResponse(DataIntegrityViolationException e) {
    Response.ResponseBuilder b = Response.status(Response.Status.FORBIDDEN).type(MediaType.TEXT_PLAIN);
    String msg = ThrowableMapper.getDeepMessage(e);
    if (msg != null) {
      b.entity(msg);
    } else {
      logger.warn(null, e);
      b.entity(e.toString());
    }
    return b.build();
  }
}
