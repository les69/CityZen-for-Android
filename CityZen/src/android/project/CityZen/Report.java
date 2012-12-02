package android.project.CityZen;

import android.graphics.Bitmap;
import android.util.Base64;

enum Category {
	INCIVILTA, BARRIERA
};

public class Report {
	private static String country;
	private static String city;
	private static String address;
	private static String _description;
	private static Category _category;
	private static String _base64image;
	
	private static Report _report = null;
	private static Bitmap _bitmapImg = null;

	private Report() {
	}

	public static Report getReport() {
		if (_report != null)
			return _report;
		else
			return new Report();
	}

	public static boolean setDescription(String s) {
		if (s.length() > 300)
			return false;
		_description = s;
		return true;
	}

	public static String getDescription() {
		return _description;
	}

	public void setCategory(Category c) {
		_category = c;
	}

	public Category getCategory() {
		return _category;
	}

	public static void setImage(byte[] image) {
		_base64image = Base64.encodeToString(image, 0);
	}

	public static String getImage() {
		return _base64image;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		Report.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		Report.city = city;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Report.address = address;
	}

	/**
	 * @return the _description
	 */
	public String get_description() {
		return _description;
	}

	/**
	 * @param _description the _description to set
	 */
	public static void set_description(String _description) {
		Report._description = _description;
	}

	/**
	 * @return the _category
	 */
	public static Category get_category() {
		return _category;
	}

	/**
	 * @param _category the _category to set
	 */
	public static void set_category(Category _category) {
		Report._category = _category;
	}

	/**
	 * @return the _base64image
	 */
	public String get_base64image() {
		return _base64image;
	}

	/**
	 * @param _base64image the _base64image to set
	 */
	public static void set_base64image(String _base64image) {
		Report._base64image = _base64image;
	}

	public void setImageView(Bitmap img) {
		_bitmapImg = img;
	}

	public Bitmap getImageView() {
		return _bitmapImg;
	}

}