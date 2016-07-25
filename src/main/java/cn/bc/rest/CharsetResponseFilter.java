package cn.bc.rest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * 默认 utf-8 的过滤器
 *
 * @author dragon 2016-07-14
 * @ref http://stackoverflow.com/questions/5514087/jersey-rest-default-character-encoding
 */
public class CharsetResponseFilter implements ContainerResponseFilter {
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		MediaType type = responseContext.getMediaType();
		if (type != null) {
			if (!type.getParameters().containsKey(MediaType.CHARSET_PARAMETER)) {
				MediaType typeWithCharset = type.withCharset("utf-8");
				responseContext.getHeaders().putSingle("Content-Type", typeWithCharset);
			}
		}
	}
}