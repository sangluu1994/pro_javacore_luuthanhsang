/**
 * Copyright(C) 2017 Luvina Software Company
 * 
 * CD.java, 26/09/2017 SangLT
 */
package testjavacore_bai2;

/**
 * Class creates CD objects in table `CDs`
 * 
 * @author SangLT
 *
 */
public class CD {
	// Declare properties
	private String artist;
	private String title;

	/**
	 * Constructor
	 * 
	 * @param artist
	 * @param title
	 * 
	 */
	public CD(String artist, String title) {
		this.artist = artist;
		this.title = title;
	}

	/**
	 * Getter: get artist property
	 * 
	 * @return artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Getter: get title property
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter: set artist property
	 * 
	 * @param artist
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Setter: set title property
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
