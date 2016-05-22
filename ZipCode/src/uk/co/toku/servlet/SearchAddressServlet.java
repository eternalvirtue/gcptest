package uk.co.toku.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.api.services.bigquery.model.TableCell;
import com.google.api.services.bigquery.model.TableRow;

import uk.co.toku.bigquery.BigqueryUtils;

@SuppressWarnings("serial")
public class SearchAddressServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession();
		String jsp = null;
		String queryKey = req.getParameter("searchAddress");
		if (queryKey == null || queryKey.length() == 0) {
			jsp = "/queryCondition.jsp";
		} else {
			List<TableRow> rows = BigqueryUtils.executeAddressQuery(queryKey);
			List<String> datas = new ArrayList<String>();
			if (rows != null) {
				Object fieldObj = null;
				String fieldValue = "";
				for (TableRow row : rows) {
					StringBuffer sbuf = new StringBuffer();
					for (TableCell field : row.getF()) {
						fieldObj = field.getV();
						fieldValue = "";
						if (fieldObj != null && fieldObj instanceof String) {
							fieldValue = ((String) fieldObj).trim();
						}
						sbuf.append(fieldValue);
						sbuf.append(" / ");
					}
					datas.add(sbuf.toString());
				}
			}

			session.setAttribute("ZIPDATA", datas);
			jsp = "/addressList.jsp";
		}

		ServletContext context = session.getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jsp);
		rd.forward(req, resp);
	}
}
