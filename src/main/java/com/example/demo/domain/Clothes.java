package com.example.demo.domain;

/**
 * 衣類情報が入るドメイン.
 * 
 * @author hyoga.ito
 *
 */
public class Clothes {
	/**	衣類ID */
	private Integer id;
	/**	衣類の種類 */
	private String category;
	/**	衣類の種類 */
	private String genre;
	/**	性別　0=男　1=女 */
	private Integer gender;
	/**	色　→　赤、青、黄、白 */
	private String color;
	/**	値段 */
	private Integer price;
	/**	大きさ　S,M,L */
	private String size;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Clothes [id=" + id + ", category=" + category + ", genre=" + genre + ", gender=" + gender + ", color="
				+ color + ", price=" + price + ", size=" + size + "]";
	}

}
