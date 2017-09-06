package cn.bc.rest;

import cn.bc.core.util.TemplateUtils;
import cn.bc.web.util.WebUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author RJ 2016-12-09
 */
public class RestUtils {
	/**
	 * 响应 json 数据
	 *
	 * @param data 返回的数据
	 * @return json response
	 */
	public static Response responseJson(Object data) {
		return Response.ok(data, MediaType.APPLICATION_JSON).build();
	}

	/**
	 * 响应文件下载或在线查看
	 *
	 * @param is        文件流
	 * @param mediaType 媒体类型
	 * @param fileName  文件名
	 * @param inline    是否内联，true-在线查看附件、false-下载文件
	 * @return file response
	 * @throws IOException 读取流大小失败时
	 */
	public static Response responseFile(InputStream is, String mediaType, String fileName, boolean inline) throws IOException {
		return Response.ok(is, mediaType)
				.header("Content-Disposition", (inline ? "inline" : "attachment") +
						"; filename =\"" + WebUtils.encodeFileName(fileName) + "\"")
				.header("Content-Length", is.available())
				.build();
	}

	/**
	 * 响应 Excel 文件下载或在线查看
	 *
	 * @param data          数据
	 * @param excelTemplate Excel 模板
	 * @param fileName      下载的文件名称  (包含扩展名但不要包含任何路径)
	 * @param inline        是否内联，true-在线查看附件、false-下载文件
	 * @return excel response
	 * @throws IOException 读取流大小失败时
	 */
	public static Response responseExcel(Map<String, Object> data, InputStream excelTemplate, String fileName,
	                                     boolean inline) throws IOException {
		try (ByteArrayInputStream excel = new ByteArrayInputStream(TemplateUtils.renderExcel(data, excelTemplate))) {
			return responseFile(excel, "application/vnd.ms-excel", fileName, inline);
		}
	}

	/**
	 * 将数据渲染为 Excel 文件下载
	 *
	 * @param data                 数据
	 * @param templateResourcePath Excel 模板的类资源路径，如 "cn/bc/..."
	 * @param fileName             下载的文件名称 (包含扩展名但不要包含任何路径)
	 * @param inline               是否内联，true-在线查看附件、false-下载文件
	 * @return excel response
	 */
	public static Response responseExcel(Map<String, Object> data, String templateResourcePath, String fileName,
	                                     boolean inline) throws IOException {
		try (InputStream excelTemplate = RestUtils.class.getClassLoader().getResourceAsStream(templateResourcePath)) {
			return responseExcel(data, excelTemplate, fileName, inline);
		}
	}
}
