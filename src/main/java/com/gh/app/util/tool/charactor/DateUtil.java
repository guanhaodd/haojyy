package com.gh.app.util.tool.charactor;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;

/**
 * 时间日期工具类
 * @author Haitao Song
 *
 */
public class DateUtil {

	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATE = "yyyy-MM-dd";
	public static final String PATTERN_CN_DATE = "yyyy年M月d日";

	/**
	 * 
	 * 方法描述 : 日期转成大写日期 如：二〇一四年十二月二十六日
	 *
	 *  @param date
	 *  @return
	 *
	 */
	public static String upperDate(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;
		int day = ca.get(Calendar.DAY_OF_MONTH);
		return upperSingleDigit(year) + "年" + upperMonth(month) + "月"
				+ upperDay(day) + "日";
	}

	/**
	 * 
	 * 方法描述 :将个位数数字转成大写
	 *
	 *  @param singleDigit
	 *  @return
	 *
	 */
	private static String upperSingleDigit(int singleDigit) {
		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(singleDigit).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	/**
	 * 
	 * 方法描述 :年数——数字转成大写
	 *
	 *  @param year
	 *  @return
	 *
	 */
	public static String upperYear(int year) {
		return upperSingleDigit(year);
	}
	
	/**
	 * 
	 * 方法描述 :月数——数字转成大写
	 *
	 *  @param month
	 *  @return
	 *
	 */
	public static String upperMonth(int month) {
		if (month < 10) {
			return upperSingleDigit(month);
		} else if (month == 10) {
			return "十";
		} else {
			return "十" + upperSingleDigit(month - 10);
		}
	}

	/**
	 * 
	 * 方法描述 : 天数——数字转成大写
	 *
	 *  @param month
	 *  @return
	 *
	 */
	public static String upperDay(int day) {
		if (day < 20) {
			return upperMonth(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return upperSingleDigit(Integer.parseInt(str[0] + "")) + "十";
			} else {
				return upperSingleDigit(Integer.parseInt(str[0] + "")) + "十"
						+ upperSingleDigit(Integer.parseInt(str[1] + ""));
			}
		}
	}

	/**
	 * 生成时间戳+随机数组成的字符串
	 * 
	 * @param date 若为空，则为当前时间
	 * @return
	 * @author luyong
	 * @dataTime 2014-8-5 下午04:11:37
	 */
	public static String generateRandomString(Date date){
		if(date==null){
			date = new Date();
		}
		String result = "";
		Random random = new Random();
		result += String.valueOf(date.getTime())+random.nextInt(10000);
		return result;
	}
	
	/**
	 * 把Timestamp按照指定的样式 转化为字符串 
	 * @param timestamp   
	 * @param pattern  例: "yyyy年M月d日"
	 * @return
	 */
	public static String timestamp2String(Timestamp timestamp, String pattern) {
		if (timestamp == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date(timestamp.getTime()));
	}

	/**
	 * 把java.util.Date按照指定的样式 转化为字符串 
	 * @param date
	 * @param pattern  例: "yyyy年M月d日"
	 * @return 格式化后的日期字符串
	 */
	public static String date2String(java.util.Date date, String pattern) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
			;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 得到当前日期的Timestamp 
	 * @return
	 */
	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static int getCurrentDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	public static int getCurrentDayOfYear() {
		return Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
	}
	public static int getCurrentDayOfWeek() {
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH)+1;
	}
	/**
	 * 
	 * @param pattern
	 * @return
	 */
	public static String currentTimestamp2String(String pattern) {
		return timestamp2String(currentTimestamp(), pattern);
	}

	public static Timestamp string2Timestamp(String strDateTime, String pattern) {
		if (strDateTime == null || strDateTime.equals("")) {
			throw new java.lang.IllegalArgumentException("Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDateTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}

	public static Date string2Date(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * 
	 * 方法描述 : 将字符串的日期转成Calendar表现形式
	 *
	 *  @param strDate
	 *  @param pattern
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:24:50
	 *
	 */
	public static Calendar string2Calendar(String strDate, String pattern) {
		Date date = null;
		date = string2Date(strDate, pattern);
		Calendar calendar = null;
		if(date!=null){
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}
		return calendar;
	}
	
	public static String stringToYear(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return String.valueOf(c.get(Calendar.YEAR));
	}

	public static String stringToMonth(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.MONTH));
		int month = c.get(Calendar.MONTH);
		month = month + 1;
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	public static String stringToDay(String strDest) {
		if (strDest == null || strDest.equals("")) {
			throw new java.lang.IllegalArgumentException("str dest null");
		}

		Date date = string2Date(strDest, DateUtil.PATTERN_DATE);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// return String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		int day = c.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			return "0" + day;
		}
		return "" + day;
	}
	
//--begin--------------
	/**
	 * 
	 * 方法描述 : 获取年
	 *
	 *  @param calendar
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:27:53
	 *
	 */
	public static String calendarToYear(Calendar calendar) {
		if (calendar==null) {
			throw new java.lang.IllegalArgumentException("param calendar is null");
		}
		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	/**
	 * 
	 * 方法描述 :获取月份
	 *
	 *  @param calendar
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:27:49
	 *
	 */
	public static String calendarToMonth(Calendar calendar) {
		if (calendar==null) {
			throw new java.lang.IllegalArgumentException("param calendar is null");
		}
		int month = calendar.get(Calendar.MONTH);
		month = month + 1;
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	/**
	 * 
	 * 方法描述 :获取天数
	 *
	 *  @param calendar
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:28:07
	 *
	 */
	public static String calendarToDay(Calendar calendar) {
		if (calendar==null) {
			throw new java.lang.IllegalArgumentException("param calendar is null");
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			return "0" + day;
		}
		return "" + day;
	}
	
	/**
	 * 
	 * 方法描述 :获取小时（24小时制）
	 *
	 *  @param calendar
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:32:23
	 *
	 */
	public static String calendarToHour24(Calendar calendar) {
		if (calendar==null) {
			throw new java.lang.IllegalArgumentException("param calendar is null");
		}
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour < 10) {
			return "0" + hour;
		}
		return "" + hour;
	}
	
	/**
	 * 
	 * 方法描述 :获取小时（12小时制）
	 *
	 *  @param calendar
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:34:40
	 *
	 */
	public static String calendarToHour12(Calendar calendar) {
		if (calendar==null) {
			throw new java.lang.IllegalArgumentException("param calendar is null");
		}
		int hour = calendar.get(Calendar.HOUR);
		if (hour < 10) {
			return "0" + hour;
		}
		return "" + hour;
	}
	
	/**
	 * 
	 * 方法描述 : 获取分钟
	 *
	 *  @param calendar
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:33:02
	 *
	 */
	public static String calendarToMinute(Calendar calendar) {
		if (calendar==null) {
			throw new java.lang.IllegalArgumentException("param calendar is null");
		}
		int hour = calendar.get(Calendar.MINUTE);
		if (hour < 10) {
			return "0" + hour;
		}
		return "" + hour;
	}
	
	/**
	 * 
	 * 方法描述 : 获取秒数
	 *
	 *  @param calendar
	 *  @return
	 * 创建人 :  luyong
	 * 创建时间: 2014-12-26 下午02:33:34
	 *
	 */
	public static String calendarToSecond(Calendar calendar) {
		if (calendar==null) {
			throw new java.lang.IllegalArgumentException("param calendar is null");
		}
		int second = calendar.get(Calendar.SECOND);
		if (second < 10) {
			return "0" + second;
		}
		return "" + second;
	}
//	--end------------------
	
	public static Date getFirstDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}

	public static Date getLastDayOfMonth(Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = 1;
		if (month > 11) {
			month = 0;
			year = year + 1;
		}
		c.set(year, month, day - 1, 0, 0, 0);
		return c.getTime();
	}

	public static String date2GregorianCalendarString(Date date) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("Date is null");
		}
		long tmp = date.getTime();
		GregorianCalendar ca = new GregorianCalendar();
		ca.setTimeInMillis(tmp);
		try {
			XMLGregorianCalendar t_XMLGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(ca);
			return t_XMLGregorianCalendar.normalize().toString();
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new java.lang.IllegalArgumentException("Date is null");
		}

	}

	public static boolean compareDate(Date firstDate, Date secondDate) {
		if (firstDate == null || secondDate == null) {
			throw new java.lang.RuntimeException();
		}

		String strFirstDate = date2String(firstDate, "yyyy-MM-dd");
		String strSecondDate = date2String(secondDate, "yyyy-MM-dd");
		if (strFirstDate.equals(strSecondDate)) {
			return true;
		}
		return false;
	}
	
	public static Date getStartTimeOfDate(Date currentDate) {
		String strDateTime = date2String(currentDate,"yyyy-MM-dd") + " 00:00:00";
		return string2Date(strDateTime,"yyyy-MM-dd hh:mm:ss");
	}
	
	public static Date getEndTimeOfDate(Date currentDate) {
		String strDateTime = date2String(currentDate,"yyyy-MM-dd") + " 59:59:59";
		return string2Date(strDateTime,"yyyy-MM-dd hh:mm:ss");
	}
	
	/** 
	 * 取得当前日期是多少周 
	 * 
	 * @param date 
	 * @return 
	 */ 
	 public static int getWeekOfYear(Date date) { 
	 Calendar c = new GregorianCalendar(); 
	 c.setFirstDayOfWeek(Calendar.MONDAY); 
	 c.setMinimalDaysInFirstWeek(7); 
	 c.setTime (date);

	 return c.get(Calendar.WEEK_OF_YEAR); 
	 }

	 /** 
	 * 得到某一年周的总数 
	 * 
	 * @param year 
	 * @return 
	 */ 
	 public static int getMaxWeekNumOfYear(int year) { 
	 Calendar c = new GregorianCalendar(); 
	 c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

	 return getWeekOfYear(c.getTime()); 
	 }

	 /** 
	 * 得到某年某周的第一天 
	 * 
	 * @param year 
	 * @param week 
	 * @return 
	 */ 
	 public static Date getFirstDayOfWeek(int year, int week) { 
	 Calendar c = new GregorianCalendar(); 
	 c.set(Calendar.YEAR, year); 
	 c.set (Calendar.MONTH, Calendar.JANUARY); 
	 c.set(Calendar.DATE, 1);

	 Calendar cal = (GregorianCalendar) c.clone(); 
	 cal.add(Calendar.DATE, week * 7);

	 return getFirstDayOfWeek(cal.getTime ()); 
	 }

	 /** 
	 * 得到某年某周的最后一天 
	 * 
	 * @param year 
	 * @param week 
	 * @return 
	 */ 
	 public static Date getLastDayOfWeek(int year, int week) { 
	 Calendar c = new GregorianCalendar(); 
	 c.set(Calendar.YEAR, year); 
	 c.set(Calendar.MONTH, Calendar.JANUARY); 
	 c.set(Calendar.DATE, 1);

	 Calendar cal = (GregorianCalendar) c.clone(); 
	 cal.add(Calendar.DATE , week * 7);

	 return getLastDayOfWeek(cal.getTime()); 
	 }

	 /** 
	 * 取得指定日期所在周的第一天 
	 * 
	 * @param date 
	 * @return 
	 */ 
	 public static Date getFirstDayOfWeek(Date date) { 
	 Calendar c = new GregorianCalendar(); 
	 c.setFirstDayOfWeek(Calendar.MONDAY); 
	 c.setTime(date); 
	 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
	 return c.getTime (); 
	 }

	 /** 
	 * 取得指定日期所在周的最后一天 
	 * 
	 * @param date 
	 * @return 
	 */ 
	 public static Date getLastDayOfWeek(Date date) { 
	 Calendar c = new GregorianCalendar(); 
	 c.setFirstDayOfWeek(Calendar.MONDAY); 
	 c.setTime(date); 
	 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
	 return c.getTime(); 
	 } 
	 
	 /** 
	 * 取得当前日期所在周的第一天 
	 * 
	 * @param date 
	 * @return 
	 */ 
	 public static Date getFirstDayOfWeek() { 
	 Calendar c = new GregorianCalendar(); 
	 c.setFirstDayOfWeek(Calendar.MONDAY); 
	 c.setTime(new Date()); 
	 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
	 return c.getTime (); 
	 }

	 /** 
	 * 取得当前日期所在周的最后一天 
	 * 
	 * @param date 
	 * @return 
	 */ 
	 public static Date getLastDayOfWeek() { 
	 Calendar c = new GregorianCalendar(); 
	 c.setFirstDayOfWeek(Calendar.MONDAY); 
	 c.setTime(new Date()); 
	 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
	 return c.getTime(); 
	 }  
	  
	 public static void main(String[] args) { 
//		 //System.out.println(DateUtil.generateRandomString(null));
	  int year = 2009; 
	  int week = 1;

	  // 以2006-01-02位例 
	  Calendar c = new GregorianCalendar(); 
	  //c.set(2009, Calendar.DECEMBER, 7); 
	  Date d = c.getTime();

	  /*//System.out.println("current date = " + d); 
	  //System.out.println("getWeekOfYear = " + getWeekOfYear(d)); 
	  //System.out.println("getMaxWeekNumOfYear = " + getMaxWeekNumOfYear(year)); 
	  //System.out.println("getFirstDayOfWeek = " + getFirstDayOfWeek(year, week)); 
	  //System.out.println("getLastDayOfWeek = " + getLastDayOfWeek(year, week)); 
	  //System.out.println ("getFirstDayOfWeek = " + getFirstDayOfWeek(d)); 
	  //System.out.println("getLastDayOfWeek = " + getLastDayOfWeek(d)); 
	  //System.out.println ("getFirstDayOfWeek = " + getFirstDayOfWeek()); 
	  //System.out.println("getLastDayOfWeek = " + getLastDayOfWeek()); */
	  //System.out.println(date2String(d, "dd"));
	  }
	  
	 public static int getCurrentSeason() {
			int month = Calendar.getInstance().get(Calendar.MONTH)+1;
			int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			int season = 0;
			if(day<8){//如果当前日小于8号 
				if(month==1){//如果是一月
					season = 4;
				} else if(month >=2 && month <5) {
					season = 1;
				} else if(month >=5 && month <8) {
					season = 2;
				} else if(month >=8 && month <11) {
					season = 3;
				} 
			}else{
				if(month >= 1 && month <4) {
					season = 1;
				} else if(month >=4 && month <7) {
					season = 2;
				} else if(month >=7 && month <10) {
					season = 3;
				} else if(month >=10 && month <12) {
					season = 4;
				} 
			}
			
			return season;
		}
	
	 /**
		 * 得到当前日期的月首 格式为：2009-08-01
		 */
		public static String monthFist() {
			Calendar localTime = Calendar.getInstance();
			String strY = null;// 日期属性：日
			int x = localTime.get(Calendar.YEAR); // 日期属性：年
			int y = localTime.get(Calendar.MONTH) + 1; // 日期属性：月
			strY = y >= 10 ? String.valueOf(y) : ("0" + y); // 组合月份
			return x + "-" + strY + "-01"; // 最后组合成yyyy-mm-dd形式字符串
		}

		/**
		 * 得到上个月月首 格式为：2009-08-01
		 */
		public static String beforeMonth() {
			Calendar localTime = Calendar.getInstance();
			localTime.add(Calendar.MONTH, -1); // 通过提取这个月计算上个月号
			String strz = null;
			int x = localTime.get(Calendar.YEAR); // 得到年
			int y = localTime.get(Calendar.MONTH) + 1; // 得到月
			strz = y >= 10 ? String.valueOf(y) : ("0" + y);
			return x + "-" + strz + "-01";
		}

		/**
		 * 得到当前日期 格式为：2009-08-01
		 */
		public static String curDate() {

			// 分别根据日历时间提取当前年月日组合成字符串
			Calendar localTime = Calendar.getInstance();

			int x = localTime.get(Calendar.YEAR);
			int y = localTime.get(Calendar.MONTH) + 1;
			int z = localTime.get(Calendar.DAY_OF_MONTH);
			return x + "-" + y + "-" + z;
		}

		/**
		 * 给定的日期加一个月 格式为：2009-08-01
		 */
		public static String addMonth(String strdate) {

			Date date = new Date(); // 构造一个日期型中间变量

			String dateresult = null; // 返回的日期字符串
			// 创建格式化格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 加减日期所用
			GregorianCalendar gc = new GregorianCalendar();

			try {
				date = df.parse(strdate); // 将字符串格式化为日期型
			} catch (ParseException e) {
				e.printStackTrace();
			}

			gc.setTime(date); // 得到gc格式的时间

			gc.add(2, 1); // 2表示月的加减，年代表1依次类推(周,天。。)
			// 把运算完的时间从新赋进对象
			gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
			// 在格式化回字符串时间
			dateresult = df.format(gc.getTime());

			return dateresult;
		}

		/**
		 * 判端date1是否在date2之前；当date1的时间早于date2是返回true date1，date2的格式为：2009-08-01
		 */
		public static boolean isDate10Before(String date1, String date2) {
			try {
				DateFormat df = DateFormat.getDateInstance();
				return df.parse(date1).before(df.parse(date2));
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * 给定的日期减一个月 格式为：2009-08-01
		 */
		public static String subMonth(String strdate) {

			Date date = new Date(); // 构造一个日期型中间变量

			String dateresult = null; // 返回的日期字符串
			// 创建格式化格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 加减日期所用
			GregorianCalendar gc = new GregorianCalendar();

			try {
				date = df.parse(strdate); // 将字符串格式化为日期型
			} catch (ParseException e) {
				e.printStackTrace();
			}

			gc.setTime(date); // 得到gc格式的时间

			gc.add(2, -1); // 2表示月的加减，年代表1依次类推(周,天。。)
			// 把运算完的时间从新赋进对象
			gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
			// 在格式化回字符串时间
			dateresult = df.format(gc.getTime());

			return dateresult;
		}

		/**
		 * 给定的日期减一天 格式为：2009-08-01
		 */
		public static String subDay(String strdate) {

			Date date = new Date(); // 构造一个日期型中间变量

			String dateresult = null; // 返回的日期字符串
			// 创建格式化格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 加减日期所用
			GregorianCalendar gc = new GregorianCalendar();

			try {
				date = df.parse(strdate); // 将字符串格式化为日期型
			} catch (ParseException e) {
				e.printStackTrace();
			}

			gc.setTime(date); // 得到gc格式的时间

			gc.add(5, -1); // 2表示月的加减，年代表1依次类推(３周....5天。。)
			// 把运算完的时间从新赋进对象
			gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
			// 在格式化回字符串时间
			dateresult = df.format(gc.getTime());

			return dateresult;
		}

		/**
		 * 给定的日期加一天 格式为：2009-08-01
		 */
		public static String addDay(String strdate) {

			Date date = new Date(); // 构造一个日期型中间变量

			String dateresult = null; // 返回的日期字符串
			// 创建格式化格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 加减日期所用
			GregorianCalendar gc = new GregorianCalendar();

			try {
				date = df.parse(strdate); // 将字符串格式化为日期型
			} catch (ParseException e) {
				e.printStackTrace();
			}

			gc.setTime(date); // 得到gc格式的时间

			gc.add(5, 1); // 2表示月的加减，年代表1依次类推(３周....5天。。)
			// 把运算完的时间从新赋进对象
			gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
			// 在格式化回字符串时间
			dateresult = df.format(gc.getTime());

			return dateresult;
		}

		/**
		 * 拆分给定字符串构造本月月初 格式为：2009-08-01
		 */
		public static String giveMonthFist(String strdate) {

			// 以“－”为分隔符拆分字符串
			String strArray[] = strdate.split("-");

			String tempyear = strArray[0]; // 得到字符串中的年

			String tempmonth = strArray[1]; // 得到字符串中的月

			// 拼接成月首字符串
			return tempyear + "-" + tempmonth + "-01";
		}

		/**
		 * 拆分给定字符串构造本月月末 格式为：2009-08-01
		 */
		public static String giveMonthLast(String strdate) {
			// 先得到下个月的同一天
			String addmonth = DateUtil.addMonth(strdate);

			// 得到下个月的月初
			String monthfirst = DateUtil.giveMonthFist(addmonth);

			// 下个月月初减一天为本月月末
			String subday = DateUtil.subDay(monthfirst);
			return subday;
		}

		/**
		 * 拆分给定字符串构造上个月月初 格式为：2009-08-01
		 */
		public static String giveBeforeMonthFirst(String strdate) {
			// 调用得到上个月的函数
			String beforemonth = DateUtil.subMonth(strdate);

			// 调用构造月初的函数
			return DateUtil.giveMonthFist(beforemonth);
		}

		/**
		 * 拆分给定字符串构造上个月月末 格式为：2009-08-01
		 */
		public static String giveBeforeMonthLast(String strdate) {
			// 先调用函数得到本月月初
			String monthfirst = DateUtil.giveMonthFist(strdate);

			// 调用当前日期减一天方法得到上个月月末
			return DateUtil.subDay(monthfirst);
		}

		/**
		 * 给定的日期得到年月 格式为：2009-08-01
		 */
		public static String giveyrmo(String yrmoday) {
			// 以“－”为分隔符拆分字符串
			String strArray[] = yrmoday.split("-");

			String tempyear = strArray[0]; // 得到字符串中的年

			String tempmonth = strArray[1]; // 得到字符串中的月

			// 拼接成月首字符串
			return tempyear + "-" + tempmonth; // 最后组合成yyyy-mm形式字符串

		}

		/**
		 * 两个日期做减法，返回相差天数
		 * 
		 * @throws ParseException
		 * @throws ParseException
		 */
		public static long datesub(Date date1, Date date2) throws ParseException {

			@SuppressWarnings("unused")
			long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime()
					- date2.getTime() : date2.getTime() - date1.getTime();

			// 日期相减得到相差的日期
			long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0 ? (date1
					.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
					: (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);

			return day + 1;
		}

		/**
		 * 根据给定的年月构造日期月首字符串
		 */
		public static String giveMonthFist(Integer yr, Integer mo) {

			// 拼接成月首字符串
			if (mo >= 10) {
				return yr + "-" + mo + "-01";
			} else {
				return yr + "-" + "0" + mo + "-01";
			}

		}

		/**
		 * 根据给定的年月构造年月字符串
		 */
		public static String giveYrMo(Integer yr, Integer mo) {

			// 拼接成月首字符串
			if (mo >= 10) {
				return yr + "-" + mo;
			} else {
				return yr + "-" + "0" + mo;
			}

		}

		/**
		 * 给定年月字串返回一个整型月份 格式为：2009-08-01
		 */
		public static Integer retrunmo(String yrmoday) {
			// 以“－”为分隔符拆分字符串
			String strArray[] = yrmoday.split("-");

			String tempmonth = strArray[1]; // 得到字符串中的月

			return new Integer(tempmonth);
		}

		/**
		 * 给定年月字串返回一个整型年份 格式为：2009-08-01
		 */
		public static Integer retrunyr(String yrmoday) {
			// 以“－”为分隔符拆分字符串
			String strArray[] = yrmoday.split("-");

			String tempmonth = strArray[0]; // 得到字符串中的月

			return new Integer(tempmonth);
		}

		/**
		 * 给定的两个日期作比较，返回bool的类型 格式为：2009-08-01
		 * 
		 * @throws ParseException
		 */
		public static boolean boolcompara(String startdate, String enddate)
				throws ParseException {

			if (DateFormat.getDateInstance().parse(startdate)
					.compareTo(DateFormat.getDateInstance().parse(startdate)) >= 0) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * 
		 * @判断时间date1是否在时间date2之前 时间格式 2008-08-08 16:16:34
		 * @param date1
		 * @param date2
		 * @return
		 */
		public static boolean isDateBefore(String date1, String date2) {
			try {
				DateFormat df = DateFormat.getDateTimeInstance();
				return df.parse(date1).before(df.parse(date2));
			} catch (ParseException e) {
				System.out.print("[SYS] " + e.getMessage());
				return false;
			}
		}

		// 判断当前时间是否在时间date2之前
		// 时间格式 2005-4-21 16:16:34
		public static boolean isDateBefore(String date2) {
			try {
				Date date1 = new Date();
				DateFormat df = DateFormat.getDateTimeInstance();
				return date1.before(df.parse(date2));
			} catch (ParseException e) {
				System.out.print("[SYS] " + e.getMessage());
				return false;
			}
		}

		/**
		 * 
		 * 当前时间增加间隔小时后的时间 时间格式 2008-08-08 16:16:34
		 */
		public static String addHours(String startDate, int intHour) {
			try {
				DateFormat df = DateFormat.getDateTimeInstance();
				Date date = df.parse(startDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				long longMills = cal.getTimeInMillis() + intHour * 60 * 60 * 1000;
				cal.setTimeInMillis(longMills);

				// 返回日期
				return df.format(cal.getTime());
			} catch (Exception Exp) {
				return null;
			}
		}

		/**
		 * 
		 * 当前时间减去间隔小时后的时间 时间格式 2008-08-08 16:16:34
		 */
		public static String delHours(String startDate, int intHour) {
			try {
				DateFormat df = DateFormat.getDateTimeInstance();
				Date date = df.parse(startDate);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				long longMills = cal.getTimeInMillis() - intHour * 60 * 60 * 1000;
				cal.setTimeInMillis(longMills);

				// 返回日期
				return df.format(cal.getTime());
			} catch (Exception Exp) {
				return null;
			}
		}

		/**
		 * 得到当前日期 日期格式 2008-08-08
		 */
		public static String getCurrentDate() {
			try {
				@SuppressWarnings("unused")
				long longCalendar = 0;
				// 获得当前日期
				Calendar cldCurrent = Calendar.getInstance();
				// 获得年月日
				String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
				String strMonth = String
						.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
				String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
				// 整理格式
				if (strMonth.length() < 2) {
					strMonth = "0" + strMonth;
				}
				if (strDate.length() < 2) {
					strDate = "0" + strDate;
				}
				// 组合结果
				longCalendar = Long.parseLong(strYear + strMonth + strDate);
				// 系统默认月份加一
				longCalendar += 100L;
				// 创建上初始化上下文环境并返回
				return String.valueOf(strYear + "-" + strMonth + "-" + strDate);
			} catch (Exception Exp) {
				return "2008-08-08";
			}
		}

		/**
		 * 将给定的日期加一个月 参数类型“2009－03”
		 */
		public static String getThisMonthLast(String strdate) {
			String thisStrDate = strdate + "-01";
			// 先得到下个月的同一天
			String addmonth = DateUtil.addMonth(thisStrDate);

			// 得到下个月的月初
			String monthfirst = DateUtil.giveMonthFist(addmonth);

			// 下个月月初减一天为本月月末
			String subday = DateUtil.subDay(monthfirst);
			return subday;
		}

		/**
		 * 获得系统中使用长整形表示的日期 返回参数：long:表示的日期的8位长整形值 如：20090723
		 */
		public static long getLongCalendar() {
			try {
				long longCalendar = 0;

				// 获得当前日期
				Calendar cldCurrent = Calendar.getInstance();

				// 获得年月日
				String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
				String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH));
				String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));

				// 整理格式
				if (strMonth.length() < 2) {
					strMonth = "0" + strMonth;
				}
				if (strDate.length() < 2) {
					strDate = "0" + strDate;
				}

				// 组合结果
				longCalendar = Long.parseLong(strYear + strMonth + strDate);

				// 系统默认月份加一
				longCalendar += 100L;

				// 创建上初始化上下文环境并返回
				return longCalendar;
			} catch (Exception Exp) {
				return 0;
			}
		}

		/**
		 * 返回字符串行日期
		 * 
		 * @param canlendar
		 *            20090801或20090802080808
		 * @return 2009/08/01或2009/08/01 08:08:08
		 */
		public static String toString(long canlendar) {
			try {
				StringBuffer sbCalendar = new StringBuffer();

				sbCalendar.insert(0, canlendar);

				// 整理格式
				if (sbCalendar.length() == 8) {
					sbCalendar.insert(6, "/");
					sbCalendar.insert(4, "/");
				} else if (sbCalendar.length() == 14) {
					sbCalendar.insert(12, ":");
					sbCalendar.insert(10, ":");
					sbCalendar.insert(8, " ");
					sbCalendar.insert(6, "/");
					sbCalendar.insert(4, "/");
				} else {
					// 错误处理
					return null;
				}
				// 返回格式化好的整形日期的字符串格式
				return sbCalendar.toString();
			} catch (Exception Exp) {
				// 错误处理
				return null;
			}
		}

		/**
		 * 长整形时间类组合Calender，封装Calender的数字接口和方法 返回 20090802080808 包含日期和时间
		 * 
		 */
		public static long getLongTime() {
			try {
				long longCalendar = 0;

				// 获得当前日期
				Calendar cldCurrent = Calendar.getInstance();

				// 获得年月日
				String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
				String strMonth = String
						.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
				String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
				String strHour = String.valueOf(cldCurrent.get(Calendar.HOUR));
				String strAM_PM = String.valueOf(cldCurrent.get(Calendar.AM_PM));
				String strMinute = String.valueOf(cldCurrent.get(Calendar.MINUTE));
				String strSecond = String.valueOf(cldCurrent.get(Calendar.SECOND));

				// 把时间转换为24小时制
				// strAM_PM=="1",表示当前时间是下午，所以strHour需要加12
				if (strAM_PM.equals("1")) {
					strHour = String.valueOf(Long.parseLong(strHour) + 12);
				}

				// 整理格式
				if (strMonth.length() < 2) {
					strMonth = "0" + strMonth;
				}
				if (strDate.length() < 2) {
					strDate = "0" + strDate;
				}
				if (strHour.length() < 2) {
					strHour = "0" + strHour;
				}
				if (strMinute.length() < 2) {
					strMinute = "0" + strMinute;
				}
				if (strSecond.length() < 2) {
					strSecond = "0" + strSecond;
				}
				// 组合结果
				longCalendar = Long.parseLong(strYear + strMonth + strDate
						+ strHour + strMinute + strSecond);

				// 创建上初始化上下文环境并返回
				return longCalendar;
			} catch (Exception Exp) {
				return 0;
			}
		}

		/**
		 * 由长整型时间变为字符 通过长整数变为时间格式,可以自动适应8位或16位 输入："20090808"或"20090808080808"
		 * 返回："2009年08月08日"或"2009年08月08日 08:08:08"
		 */
		public static String getDateStringByLongDatetime(long longCalendar) {
			try {
				String StrCalendar = String.valueOf(longCalendar);
				String StrCalendarResult = "";
				// 判断为日期型
				if (StrCalendar.length() == 8) {
					StrCalendarResult = StrCalendar.substring(0, 4) + "年"
							+ StrCalendar.substring(4, 6) + "月"
							+ StrCalendar.substring(6, 8) + "日";
					return StrCalendarResult;
				}
				// 判断为日期及时间型
				if (StrCalendar.length() == 14) {
					StrCalendarResult = StrCalendar.substring(0, 4) + "年"
							+ StrCalendar.substring(4, 6) + "月"
							+ StrCalendar.substring(6, 8) + "日";
					StrCalendarResult = StrCalendarResult + " "
							+ StrCalendar.substring(8, 10) + ":"
							+ StrCalendar.substring(10, 12) + ":"
							+ StrCalendar.substring(12, 14);
					return StrCalendarResult;
				}
				// 否则返回空字符
				return "";
			} catch (Exception e) {
				// 错误处理
				e.printStackTrace();
				return "";
			}
		}

		/**
		 * 由长整型时间变为字符 通过长整数变为时间格式,可以自动适应8位或16位 输入："20090808"或"20090808080808"
		 * 返回："2009/08/08"或"2009/08/08 08:08:08"
		 */
		public static String getDateStringByLongDatetimeForPage(long longCalendar) {
			try {
				String StrCalendar = String.valueOf(longCalendar);
				String StrCalendarResult = "";
				// 判断为日期型
				if (StrCalendar.length() == 8) {
					StrCalendarResult = StrCalendar.substring(0, 4) + "/"
							+ StrCalendar.substring(4, 6) + "/"
							+ StrCalendar.substring(6, 8);
					return StrCalendarResult;
				}
				// 判断为日期及时间型
				if (StrCalendar.length() == 14) {
					StrCalendarResult = StrCalendar.substring(0, 4) + "/"
							+ StrCalendar.substring(4, 6) + "/"
							+ StrCalendar.substring(6, 8);
					StrCalendarResult = StrCalendarResult + " "
							+ StrCalendar.substring(8, 10) + ":"
							+ StrCalendar.substring(10, 12);
					return StrCalendarResult;
				}
				// 否则返回空字符
				return "";
			} catch (Exception e) {
				// 错误处理
				e.printStackTrace();
				return "";
			}
		}

		/**
		 * 得到系统当前时间 返回参数：String:系统当前时间 格式：yyyy/mm/dd
		 */
		public static String getCurrentDateTime() {
			return getDateStringByLongDatetimeForPage(getLongCalendar());
		}

		/**
		 * 得到系统当前日期显示， 返回格式：yyyy/mm/dd
		 */
		public static String getCurrentDateView() {
			// 获得当前日期
			Calendar cldCurrent = Calendar.getInstance();
			// 获得年月日
			String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
			String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
			String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}
			// 得出当天日期的字符串
			String StrCurrentCalendar = strYear + "/" + strMonth + "/" + strDate;
			return StrCurrentCalendar;
		}

		/**
		 * 得到给定时间显示，格式：yyyy/mm/dd 参数格式："20090808"或"20090808080808"
		 */
		public static String getCurrentDateView(long longCalendar) {
			if (longCalendar == 0) {
				return "";
			}
			String strDateView = String.valueOf(longCalendar);
			// 获得年月日
			String strYear = strDateView.substring(0, 4);
			String strMonth = strDateView.substring(4, 6);
			String strDate = strDateView.substring(6, 8);
			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}
			// 得出当天日期的字符串
			String StrCurrentCalendar = strYear + "/" + strMonth + "/" + strDate;
			return StrCurrentCalendar;
		}

		/**
		 * 由长整型时间变为字符 通过长整数变为时间格式,输入参数为6位的时间 如"123143" 返回格式：12点31分43秒
		 */
		public static String getTimeStringByLongTime(long longCalendar) {
			try {
				String StrCalendar = String.valueOf(longCalendar);
				String StrCalendarResult = "";

				// 判断为时间型
				if (StrCalendar.length() == 6) {
					StrCalendarResult = StrCalendar.substring(0, 2) + "点"
							+ StrCalendar.substring(2, 4) + "分"
							+ StrCalendar.substring(4, 6) + "秒";
					return StrCalendarResult;
				}
				// 否则返回空字符
				return "";
			} catch (Exception e) {
				// 错误处理
				e.printStackTrace();
				return "";
			}
		}

		/**
		 * 由长整型时间变为字符 通过长整数变为时间格式,输入参数为6位的时间 如"123143" 返回格式：12:31:43
		 */
		public static String getTimeStringByLongTimeForPage(long longCalendar) {
			try {
				String StrCalendar = String.valueOf(longCalendar);
				String StrCalendarResult = "";

				// 判断为时间型
				if (StrCalendar.length() == 6) {
					StrCalendarResult = StrCalendar.substring(0, 2) + ":"
							+ StrCalendar.substring(2, 4) + ":"
							+ StrCalendar.substring(4, 6);
					return StrCalendarResult;
				}
				// 否则返回空字符
				return "";
			} catch (Exception e) {
				// 错误处理
				e.printStackTrace();
				return "";
			}
		}

		/**
		 * 给指定的Calendar，返回常用的8位时间
		 */
		public static long getLongCalendar(Calendar yourCalendar) {
			try {
				long longCalendar = 0;

				// 获得年月日
				String strYear = String.valueOf(yourCalendar.get(Calendar.YEAR));
				String strMonth = String.valueOf(yourCalendar.get(Calendar.MONTH));
				String strDate = String.valueOf(yourCalendar.get(Calendar.DATE));

				// 整理格式
				if (strMonth.length() < 2) {
					strMonth = "0" + strMonth;
				}
				if (strDate.length() < 2) {
					strDate = "0" + strDate;
				}

				// 组合结果
				longCalendar = Long.parseLong(strYear + strMonth + strDate);

				// 系统默认月份加一
				longCalendar += 100L;

				// 创建上初始化上下文环境并返回
				return longCalendar;
			} catch (Exception Exp) {
				return 0;
			}
		}

		/**
		 * 给指定的Calendar，返回常用的１４位时间
		 */
		public static long getLongTime(Calendar yourCalendar) {
			try {
				long longCalendar = 0;
				// 获得年月日
				String strYear = String.valueOf(yourCalendar.get(Calendar.YEAR));
				String strMonth = String
						.valueOf(yourCalendar.get(Calendar.MONTH) + 1);
				String strDate = String.valueOf(yourCalendar.get(Calendar.DATE));
				String strHour = String.valueOf(yourCalendar.get(Calendar.HOUR));
				String strAM_PM = String.valueOf(yourCalendar.get(Calendar.AM_PM));
				String strMinute = String
						.valueOf(yourCalendar.get(Calendar.MINUTE));
				String strSecond = String
						.valueOf(yourCalendar.get(Calendar.SECOND));

				// 把时间转换为24小时制
				// strAM_PM=="1",表示当前时间是下午，所以strHour需要加12
				if (strAM_PM.equals("1")) {
					strHour = String.valueOf(Long.parseLong(strHour) + 12);
				}

				// 整理格式
				if (strMonth.length() < 2) {
					strMonth = "0" + strMonth;
				}
				if (strDate.length() < 2) {
					strDate = "0" + strDate;
				}
				if (strHour.length() < 2) {
					strHour = "0" + strHour;
				}
				if (strMinute.length() < 2) {
					strMinute = "0" + strMinute;
				}
				if (strSecond.length() < 2) {
					strSecond = "0" + strSecond;
				}
				// 组合结果
				longCalendar = Long.parseLong(strYear + strMonth + strDate
						+ strHour + strMinute + strSecond);

				// 创建上初始化上下文环境并返回
				return longCalendar;
			} catch (Exception Exp) {
				return 0;
			}
		}

		/**
		 * 将长整型日期转换为日历,自适应８位或１４位
		 */
		public static Calendar getCalendar(long longCalendar) {
			long longNF = 0;
			long longYF = 0;
			long longRZ = 0;
			long longXS = 0;
			long longFZ = 0;
			long longM = 0;
			GregorianCalendar gc = null;
			// 判断是８位还是１４位
			if (String.valueOf(longCalendar).length() < 14) {
				longNF = Long.parseLong(String.valueOf(longCalendar)
						.substring(0, 4));
				longYF = Long.parseLong(String.valueOf(longCalendar)
						.substring(4, 6)) - 1;
				longRZ = Long.parseLong(String.valueOf(longCalendar).substring(6));
				gc = new GregorianCalendar((int) longNF, (int) longYF, (int) longRZ);
			} else {
				longNF = Long.parseLong(String.valueOf(longCalendar)
						.substring(0, 4));
				longYF = Long.parseLong(String.valueOf(longCalendar)
						.substring(4, 6)) - 1;
				longRZ = Long.parseLong(String.valueOf(longCalendar)
						.substring(6, 8));
				longXS = Long.parseLong(String.valueOf(longCalendar).substring(8,
						10));
				longFZ = Long.parseLong(String.valueOf(longCalendar).substring(10,
						12));
				longM = Long.parseLong(String.valueOf(longCalendar).substring(12));

				gc = new GregorianCalendar((int) longNF, (int) longYF,
						(int) longRZ, (int) longXS, (int) longFZ, (int) longM);

			}
			return gc;
		}

		/**
		 * 当前时间增加间隔分钟后的时间
		 */
		public static long addMinutes(long longCalendar, int intMin) {
			try {
				//
				long longDate = 0;
				Calendar cal = getCalendar(longCalendar);
				long longMills = cal.getTimeInMillis() + intMin * 60 * 1000;
				cal.setTimeInMillis(longMills);

				if (String.valueOf(longCalendar).length() < 14)
					longDate = getLongCalendar(cal);
				else
					longDate = getLongTime(cal);

				// 返回日期
				return longDate;
			} catch (Exception Exp) {
				return -1;
			}
		}

		/**
		 * 当前时间增加间隔小时后的时间
		 */
		public static long addHours(long longCalendar, int intHour) {
			try {
				//
				long longDate = 0;
				Calendar cal = getCalendar(longCalendar);
				long longMills = cal.getTimeInMillis() + intHour * 60 * 60 * 1000;
				cal.setTimeInMillis(longMills);

				if (String.valueOf(longCalendar).length() < 14)
					longDate = getLongCalendar(cal);
				else
					longDate = getLongTime(cal);

				// 返回日期
				return longDate;
			} catch (Exception Exp) {
				return -1;
			}
		}

		/**
		 * 当前时间增加间隔天后的时间
		 */
		public static long addDays(long longCalendar, int intDay) {
			try {
				//
				long longDate = 0;
				Calendar cal = getCalendar(longCalendar);
				long longMills = cal.getTimeInMillis() + intDay * 24 * 60 * 60
						* 1000;
				cal.setTimeInMillis(longMills);

				if (String.valueOf(longCalendar).length() < 14)
					longDate = getLongCalendar(cal);
				else
					longDate = getLongTime(cal);

				// 返回日期
				return longDate;
			} catch (Exception Exp) {
				return -1;
			}
		}

		/**
		 * 当前时间增加间隔星期后的时间
		 */
		public static long addWeeks(long longCalendar, int intWeek) {
			try {
				//
				long longDate = 0;
				Calendar cal = getCalendar(longCalendar);
				long longMills = cal.getTimeInMillis() + intWeek * 7 * 24 * 60 * 60
						* 1000;
				cal.setTimeInMillis(longMills);

				if (String.valueOf(longCalendar).length() < 14)
					longDate = getLongCalendar(cal);
				else
					longDate = getLongTime(cal);

				// 返回日期
				return longDate;
			} catch (Exception Exp) {
				return -1;
			}
		}

		/**
		 * 当前日期增加间隔月份后的时间
		 */
		public static long addMonths(long longCalendar, int intMonth) {
			try {
				long longNF = 0;
				long longYF = 0;
				long longRZ = 0;
				long longDate = 0;
				long longNIAN = 0;
				String strYF = "";
				String strRZ = "";
				longNF = Long.parseLong(String.valueOf(longCalendar)
						.substring(0, 4));
				longYF = Long.parseLong(String.valueOf(longCalendar)
						.substring(4, 6));
				longRZ = Long.parseLong(String.valueOf(longCalendar)
						.substring(6, 8));
				longYF = longYF + intMonth;

				if (longYF > 12) {
					longNIAN = (long) Math.floor(longYF / 12);
					longYF = longYF % 12;

					if (longYF == 0) {
						longYF = 12;
					}

					longNF = longNF + longNIAN;
				}

				// 处理特殊日
				if (longRZ >= 28)
					longRZ = getNormalDay(longNF, longYF, longRZ);

				if (longYF < 10)
					strYF = "0" + String.valueOf(longYF);
				else
					strYF = String.valueOf(longYF);

				if (longRZ < 10)
					strRZ = "0" + String.valueOf(longRZ);
				else
					strRZ = String.valueOf(longRZ);

				// 判断是８位还是１４位
				if (String.valueOf(longCalendar).length() < 14) {
					longDate = Long.parseLong(String.valueOf(longNF) + strYF
							+ strRZ);
				} else {
					longDate = Long
							.parseLong(String.valueOf(longNF) + strYF + strRZ
									+ String.valueOf(longCalendar).substring(8, 14));
				}
				// 返回日期
				return longDate;
			} catch (Exception Exp) {
				return -1;
			}
		}

		/**
		 * 返回正常日－处理３０／３１／２８ 输入参数：long calendar 当前时间, int intWeek 间隔星期 返回值：
		 * long:处理后的时间
		 */
		public static long getNormalDay(long longNF, long longYF, long longRZ) {
			try {
				// 只有日为２８／２９／３０／３１才运行此函数
				// 处理２月份
				if (longYF == 2) {
					if ((longNF % 4) == 0) {
						if (longRZ > 28)
							longRZ = 29;
					} else
						longRZ = 28;
				}
				if (longRZ == 31) {
					if (longYF == 4 || longYF == 6 || longYF == 9 || longYF == 11)
						longRZ = 30;
				}
				return longRZ;

			} catch (Exception Exp) {
				return -1;
			}
		}

		/**
		 * 获得系统中使用长整形表示的日期加星期
		 */
		public static String getStringCalendarAndWeek() {
			try {
				String strCalendar = "";

				// 获得当前日期
				Calendar cldCurrent = Calendar.getInstance();

				// 获得年月日
				String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
				String strMonth = String
						.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
				String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));

				// 整理格式
				if (strMonth.length() < 2) {
					strMonth = "0" + strMonth;
				}
				if (strDate.length() < 2) {
					strDate = "0" + strDate;
				}
				// 组合结果
				strCalendar = strYear + "年" + strMonth + "月" + strDate + "日";

				int intWeek = cldCurrent.get(Calendar.DAY_OF_WEEK);
				String strWeek = "";
				switch (intWeek) {
				case 1:
					strWeek = "星期日";
					break;
				case 2:
					strWeek = "星期一";
					break;
				case 3:
					strWeek = "星期二";
					break;
				case 4:
					strWeek = "星期三";
					break;
				case 5:
					strWeek = "星期四";
					break;
				case 6:
					strWeek = "星期五";
					break;
				case 7:
					strWeek = "星期六";
					break;
				}

				strCalendar = strCalendar + " " + strWeek + " ";

				// 创建上初始化上下文环境并返回
				return strCalendar;
			} catch (Exception Exp) {
				return "";
			}
		}

		public static String Text2HtmlToPageContent(String text) {
			// 若原字符串为空，则返回空对象
			if (text == null) {
				return "";
			}
			// 保存原字符串，方法结束时此字符串被转换成HTML格式的字符串
			String strSource = text;
			// 转换字符串的临时空间
			StringBuffer sbTarget = new StringBuffer();
			// 纯文本中要转换的字符或字符串
			char[] charArraySource = { '<', '>', '&', '"', '\n' };
			// 纯文本中要转换的字符或字符串对应的HTML表达方式
			String[] strArrayTarget = { "&lt;", "&gt;", "&amp;", "&quot;", "<br>" };

			// 记录处理过特殊字符的位置
			int intStart = 0;

			// 依次检查每一个源字符串的字符或字符串
			for (int i = 0; i < strSource.length(); i++) {
				// 字符串中包含要转换的字符或字符串，则进行相应转换
				for (int j = 0; j < charArraySource.length; j++) {
					// 当前检查的字符是要转换的特殊字符，则进行处理
					if (strSource.charAt(i) == charArraySource[j]) {
						sbTarget.append(strSource.substring(intStart, i));
						sbTarget.append(strArrayTarget[j]);
						intStart = i + 1;
						continue;
					}
				}
			}
			// 将所有处理位置之后的字符串放入目标字符串中
			sbTarget.append(strSource.substring(intStart));

			// 转换完成，返回转换结果
			return sbTarget.toString();
		}

		public static String getDateStringByLongDate(long longCalendar) {
			try {
				String StrCalendar = String.valueOf(longCalendar);
				String StrCalendarResult = "";

				StrCalendarResult = StrCalendar.substring(0, 4) + "年"
						+ StrCalendar.substring(4, 6) + "月"
						+ StrCalendar.substring(6, 8) + "日";
				return StrCalendarResult;
			} catch (Exception e) {
				// 错误处理
				e.printStackTrace();
				return "";
			}
		}

		/**
		 * 根据月份数取得当前季度
		 * @param strYue   例: 1
		 * @return  1 
		 */
		public static String getJi(String strYue) {
			int intYue = Integer.decode(strYue).intValue();
			if (intYue >= 1 && intYue <= 3) {
				return "1";
			} else if (intYue >= 4 && intYue <= 6) {
				return "2";
			} else if (intYue >= 7 && intYue <= 9) {
				return "3";
			} else {
				return "4";
			}
		}

		public static java.util.Date parse(String dateString, String dateFormat) {
			return parse(dateString, dateFormat, java.util.Date.class);
		}

		@SuppressWarnings("unchecked")
		public static <T extends java.util.Date> T parse(String dateString,
				String dateFormat, Class<T> targetResultType) {
			if (StringUtils.isEmpty(dateString))
				return null;
			DateFormat df = new SimpleDateFormat(dateFormat);
			try {
				long time = df.parse(dateString).getTime();
				java.util.Date t = targetResultType.getConstructor(long.class)
						.newInstance(time);
				return (T) t;
			} catch (ParseException e) {
				String errorInfo = "cannot use dateformat:" + dateFormat
						+ " parse datestring:" + dateString;
				throw new IllegalArgumentException(errorInfo, e);
			} catch (Exception e) {
				throw new IllegalArgumentException("error targetResultType:"
						+ targetResultType.getName(), e);
			}
		}

		public static String format(java.util.Date date, String dateFormat) {
			if (date == null)
				return null;
			return new SimpleDateFormat(dateFormat).format(date);
		}

		/**
		* 方法描述 :获取运算 后的日期 格式为“yyyy-MM-dd”
		*  @param strdate 要变更的日期
		*  @param year  加减几年
		*  @param month 加减几月
		*  @param day  加减几天
		*  @return
		* 创建人 :  Wang Pengfei
		* 创建时间: 2015-1-7 下午05:21:27
		*/
		public static String getChangeDate(String strdate,int year,int month,int day){
			Date date = new Date(); // 构造一个日期型中间变量

			String dateresult = null; // 返回的日期字符串
			// 创建格式化格式
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// 加减日期所用
			GregorianCalendar gc = new GregorianCalendar();

			try {
				date = df.parse(strdate); // 将字符串格式化为日期型
			} catch (ParseException e) {
				e.printStackTrace();
			}
			gc.setTime(date); // 得到gc格式的时间
			gc.add(1, year); // 年
			gc.add(2, month); // 月
			gc.add(5, day);//天
			// 把运算完的时间从新赋进对象
			gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
			// 在格式化回字符串时间
			dateresult = df.format(gc.getTime());

			return dateresult;
		}
	 
	 
}