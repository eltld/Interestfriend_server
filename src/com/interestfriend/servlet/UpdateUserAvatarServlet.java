package com.interestfriend.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.interestfriend.Idao.MembersDao;
import com.interestfriend.Idao.UserDao;
import com.interestfriend.Utils.DateUtils;
import com.interestfriend.Utils.JsonUtil;
import com.interestfriend.bean.Members;
import com.interestfriend.bean.User;
import com.interestfriend.enums.ErrorEnum;
import com.interestfriend.factory.MembersDaoFactory;
import com.interestfriend.factory.UserDaoFactory;

public class UpdateUserAvatarServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public UpdateUserAvatarServlet() {
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

		request.setCharacterEncoding("utf-8");
		User user = new User();
		// ��ô����ļ���Ŀ������
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��ȡ�ļ��ϴ���Ҫ�����·����user-avatar�ļ�������ڡ�
		String path = request.getContextPath();
		String serverPath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/user-avatar/";
		String avatarSavePath = request.getSession().getServletContext()
				.getRealPath("/user-avatar")
				+ File.separator;
		// ������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�
		factory.setRepository(new File(avatarSavePath));
		// ���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�
		factory.setSizeThreshold(1024 * 1024);
		// �ϴ����������ࣨ��ˮƽAPI�ϴ���������
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// ���� parseRequest��request������ ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			for (FileItem item : list) {
				// �����ȡ�ı�����Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�������ʽ���������ַ�����
				if (item.isFormField()) {
					// ��ȡ�����������֡�
					String name = item.getFieldName();
					// ��ȡ�û�����������ַ�����
					String value = item.getString();
					// System.out.println(value);
					request.setAttribute(name, value);
					System.out.println(name + "  " + value);
				}
				// ���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���
				else {
					// ��ȡ·����
					String value = item.getName();
					// ȡ�����һ����б�ܡ�
					// int start = value.lastIndexOf("\\");
					// ��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�
					// String filename = value.substring(start + 1);
					String filename = DateUtils.getUpLoadFileName()
							+ value.substring(value.length() - 4,
									value.length());
					// request.setAttribute(name, filename);
					/*
					 * �������ṩ�ķ���ֱ��д���ļ��С� item.write(new File(path,filename));
					 */
					// �յ�д�����յ��ļ��С�
					OutputStream out = new FileOutputStream(new File(
							avatarSavePath, filename));
					InputStream in = item.getInputStream();
					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("��ȡ�ļ�����������:" + item.getSize());
					while ((length = in.read(buf)) != -1) {
						out.write(buf, 0, length);
					}
					in.close();
					out.close();
					item.delete();
					user.setUserAvatar(serverPath + filename);
				}
			}
			String user_id = request.getAttribute("user_id").toString();
			user.setUserLastUpdateTime(DateUtils.getLastUpdateTime());
			user.setUserID(Integer.valueOf(user_id));
			user.setUserState("UPDATE");
			UserDao dao = UserDaoFactory.getUserDaoInstance();
			boolean isSuccess = dao.updateUserAvatar(user);
			Map<String, Object> params = new HashMap<String, Object>();
			if (!isSuccess) {
				params.put("err", ErrorEnum.INVALID.name());
				params.put("rt", 0);
			} else {
				params.put("user_avatar", user.getUserAvatar());
				params.put("rt", 1);
				MembersDao mDao = MembersDaoFactory.getInstance();
				Members member = new Members();
				member.setUser_id(Integer.valueOf(user_id));
				member.setUser_state("UPDATE");
				mDao.updateMemberLastUpdateTimeAndState(member);
			}
			PrintWriter out = response.getWriter();
			out.print(JsonUtil.toJsonString(params));
			out.flush();
			out.close();
			System.out.println(JsonUtil.toJsonString(params));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}

	}
}