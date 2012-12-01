package android.project.CityZen;

import android.util.Base64;

enum Category
{
	INCIVILTA,
	BARRIERA
};

public class Report  {
	private static String _description;
	private static Category _category;
	private static String _base64image;
	private static Report _report = null;
	
	private Report()
	{}
	public static Report getReport()
	{
		if(_report != null)
			return _report;
		else
			return new Report();
	}
	public static boolean setDescription(String s)
	{
		if(s.length() > 300)
			return false;
		_description = s;
		return true;
	}
	public static String getDescription()
	{ 
		return _description; 
	}
	public static void setCategory(Category c)
	{ 
		_category = c;
	}
	public static Category getCategory()
	{
		return _category;
	}
	public static void setImage(byte[] image)
	{
		_base64image = Base64.encodeToString(image, 0);
	}
	public static String getImage()
	{
		return _base64image;
	}
	
	
	
}