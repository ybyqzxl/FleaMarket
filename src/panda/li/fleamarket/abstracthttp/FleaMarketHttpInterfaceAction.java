package panda.li.fleamarket.abstracthttp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;

public abstract class FleaMarketHttpInterfaceAction extends AbstractHttpInterfaceAction {

	public static final SimpleDateFormat format_10 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * 
	 */
	private static final long serialVersionUID = -5044779508841974033L;

	public boolean isEmpity(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static Date str2Date(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		Date date = null;
		try {
			SimpleDateFormat sdf = format_10;
			date = sdf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

}
