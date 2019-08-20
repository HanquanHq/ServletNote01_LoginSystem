package cn.hanquan.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 计算总登录次数
 * 
 * 在ServletContext和1.txt中，nums以String类型存储、读取。
 * 在读取至java内存之后，转换成int类型进行运算。
 * 
 * @author Buuug
 *
 */
public class NumServlet extends HttpServlet {

	@Override
	/**
	 * 读文件
	 */
	public void init() throws ServletException {
		String path = this.getServletContext().getRealPath("/nums/1.txt");
		FileReader fr = null;
		BufferedReader br = null;
		System.out.println("读 ：" + path);
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String nums = br.readLine();
			// 写入ServletContext
			this.getServletContext().setAttribute("nums", nums);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	/**
	 * 写文件
	 */
	public void destroy() {
		// 写入txt
		int nums = Integer.parseInt((String) this.getServletContext().getAttribute("nums"));
		String path = this.getServletContext().getRealPath("/nums/1.txt");
		System.out.println("写：" + path);
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(nums + "");
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
