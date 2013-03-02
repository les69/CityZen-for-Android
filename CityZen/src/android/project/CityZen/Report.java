package android.project.CityZen;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

enum Category
{
	INCIVILTA,
	BARRIERA
};

public class Report  {
	private static String country;
	private static String city;
	private static String address;
	private static String _description;
	private static String _category;
	private static String _base64image;
	private static Report _report = null;
	private static Bitmap _bitmapImg = null;
	private static Bitmap _scaledImg = null;
	private static ByteArrayOutputStream _imgStream;
	
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
	public static void setCategory(String c)
	{ 
		_category = c;
	}
	public static String getCategory()
	{
		return _category;
	}
	public static void setImage(byte[] image)
	{
		_base64image = Base64.encodeToString(image, 0);
	}
	public static String getBase64Image()
	{
		return _base64image = Base64.encodeToString(_imgStream.toByteArray(), 0);
	}
	public static void setImageView(Bitmap img)
	{
		_bitmapImg = img;
		if(img == null)
			return;
		_scaledImg=Bitmap.createScaledBitmap(img, 640, 480, true);	
		_imgStream = new ByteArrayOutputStream();
		_scaledImg.compress(Bitmap.CompressFormat.JPEG, 90, _imgStream);
		
		
	}
	public static Bitmap getImageView()
	{
		return _bitmapImg;
	}
	/**
	 * @return the country
	 */
	public static String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public static void setCountry(String country) {
		Report.country = country;
	}

	/**
	 * @return the city
	 */
	public static String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public static void setCity(String city) {
		Report.city = city;
	}

	/**
	 * @return the address
	 */
	public static String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public static void setAddress(String address) {
		Report.address = address;
	}

	/**
	 * @return the _description
	 */
	public static String get_description() {
		return _description;
	}

	/**
	 * @param _description the _description to set
	 */
	public static void set_description(String _description) {
		Report._description = _description;
	}


}
	
	
	
