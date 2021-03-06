package com.interestfriend.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.interestfriend.Idao.CircleDao;
import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Circle;
import com.interestfriend.bean.Members;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.CircleDaoFactory;
import com.interestfriend.factory.MembersDaoFactory;
import com.interestfriend.huanxinImpl.EasemobChatGroups;

public class CreateCircleServlet extends HttpServlet {
	String avatarSavePath = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		avatarSavePath = this.getServletConfig().getServletContext()
				.getRealPath("/circle_images")
				+ File.separator;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public CreateCircleServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		Circle circle = new Circle();
		String path = request.getContextPath();

		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/circle_images/";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(avatarSavePath));
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// 调用 parseRequest（request）方法 获得上传文件 FileItem 的集合list 可实现多文件上传。
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// 如果获取的表单信息是普通的文本信息。即通过页面表单形式传递来的字符串。
				if (item.isFormField()) {
					// 获取表单属性名字。
					String name = item.getFieldName();
					// 获取用户具体输入的字符串，
					String value = item.getString();
					// System.out.println(value);
					request.setAttribute(name, value);
				}
				// 如果传入的是非简单字符串，而是图片，音频，视频等二进制文件。
				else {
					// 获取路径名
					String value = item.getName();
					// 取到最后一个反斜杠。
					int start = value.lastIndexOf("\\");
					// 截取上传文件的 字符串名字。+1是去掉反斜杠。
					// String filename = value.substring(start + 1);
					String fileName = DateUtils.getUpLoadFileName()
							+ value.substring(value.length() - 4,
									value.length());
					serverPath += fileName;
					File file = new File(avatarSavePath, fileName);
					item.write(file);
					item.delete();
					circle.setCircle_avatar(serverPath);
				}
			}
			String circle_name = request.getAttribute("circle_name").toString();
			String circle_description = request.getAttribute(
					"circle_description").toString();
			String huanxin_userName = request.getAttribute("huanxin_username")
					.toString();
			String category = request.getAttribute("category").toString();
			int user_id = Integer.valueOf(request.getAttribute("user_id")
					.toString());
			double longitude = Double.valueOf(request.getAttribute("longitude")
					.toString());
			double latitude = Double.valueOf(request.getAttribute("latitude")
					.toString());
			String group_id = EasemobChatGroups.createCircleGroup(circle_name,
					circle_description);
			circle.setCreator_id(user_id);
			circle.setCircle_description(circle_description);
			circle.setCircle_name(circle_name);
			circle.setGroup_id(group_id);
			circle.setCategory(Integer.valueOf(category));
			circle.setLatitude(latitude);
			circle.setLongitude(longitude);
			circle.setCircle_create_time(DateUtils.getRegisterTime());
			CircleDao dao = CircleDaoFactory.getCircleDaoInstance();
			int cid = dao.insertCircleToDB(circle);
			Members member = new Members();
			member.setCircle_id(cid);
			member.setUser_id(user_id);
			long time = DateUtils.getLastUpdateTime();
			member.setUser_update_time(time);
			member.setCircle_last_request_time(time);
			MembersDao daoM = MembersDaoFactory.getInstance();
			boolean rt = daoM.addMembers(member);
			Map<String, Object> params = new HashMap<String, Object>();
			if (!rt) {
				params.put("err", ErrorEnum.INVALID.name());
				params.put("rt", 0);
			} else {
				params.put("circle_logo", serverPath);
				params.put("group_id", group_id);
				params.put("circle_id", cid);
				params.put("rt", 1);
				params.put("circle_last_request_time", time);

			}
			PrintWriter out = response.getWriter();
			out.print(JsonUtil.toJsonString(params));
			System.out.println("路径：" + serverPath);
			System.out.println(params.toString());
			out.flush();
			out.close();
			if (rt) {
				EasemobChatGroups.addUserToGroup(group_id, huanxin_userName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
