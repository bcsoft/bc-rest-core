package cn.bc.rest;

import cn.bc.core.util.TemplateUtils;
import cn.bc.web.util.WebUtils;

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
	 * 将数据渲染为 Excel 文件下载
	 *
	 * @param data          数据
	 * @param excelTemplate Excel 模板
	 * @param fileName      下载的文件名称 (不要包含扩展名和路径)
	 * @return excel response
	 */
	public static Response responseExcel(Map<String, Object> data, InputStream excelTemplate, String fileName) throws IOException {
		try (ByteArrayInputStream excel = new ByteArrayInputStream(TemplateUtils.renderExcel(data, excelTemplate))) {
			return Response.ok(excel, "application/vnd.ms-excel")
					.header("Content-Disposition", "attachment;filename=\"" + WebUtils.encodeFileName(fileName) + ".xlsx\"")
					.header("Content-Length", excel.available())
					.build();
		}
	}

	/**
	 * 将数据渲染为 Excel 文件下载
	 *
	 * @param data                 数据
	 * @param templateResourcePath Excel 模板的类资源路径
	 * @param fileName             下载的文件名称 (不要包含扩展名和路径)
	 * @return excel response
	 */
	public static Response responseExcel(Map<String, Object> data, String templateResourcePath, String fileName) throws IOException {
		try (InputStream excelTemplate = RestUtils.class.getClassLoader().getResourceAsStream(templateResourcePath)) {
			return responseExcel(data, excelTemplate, fileName);
		}
	}
}
