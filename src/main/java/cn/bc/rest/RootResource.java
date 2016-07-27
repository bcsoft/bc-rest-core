package cn.bc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * rest 服务的根访问路径对应的资源
 *
 * @author dragon 2016-07-14
 */
@Path("/")
public class RootResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String root() {
		return "bc rest service";
	}
}