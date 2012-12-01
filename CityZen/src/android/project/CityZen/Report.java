package android.project.CityZen;

import android.content.Context;
import android.location.*;
import android.util.Base64;

enum Category
{
	INCIVILTA,
	BARRIERA
};
public class Report {
	private Context _context;
	private LocationManager _location;
	private String _description;
	private Category _category;
	private String _base64image;
	 
	
	
	Report(){}
	Report(Context c)
	{
		_context = c;
	}
	public boolean setDescription(String s)
	{
		if(s.length() > 300)
			return false;
		_description = s;
		return true;
	}
	public String getDescription()
	{ 
		return _description; 
	}
	public void setCategory(Category c)
	{ 
		_category = c;
	}
	public Category getCategory()
	{
		return _category;
	}
	public void setImage(byte[] image)
	{
		_base64image = Base64.encodeToString(image, 0);
	}
	public String getImage()
	{
		return _base64image;
	}
	
	
	
}
