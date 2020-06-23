package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Hotel;

/**
 * ホテル情報を操作するリポジトリ.
 * 
 * @author hyoga.ito
 *
 */
@Repository
public class HotelRepository {
	
	/**	SQL結果をHotelオブジェクトに入れるローマッパー */
	private final RowMapper<Hotel> HOTEL_ROW_MAPPER =(rs,i)->{
		Hotel hotel=new Hotel();
		hotel.setId(rs.getInt("id"));
		hotel.setAreaName(rs.getString("area_name"));
		hotel.setHotelName(rs.getString("hotel_name"));
		hotel.setAddress(rs.getString("address"));
		hotel.setNearestStation(rs.getString("nearest_station"));
		hotel.setPrice(rs.getInt("price"));
		hotel.setParking(rs.getString("parking"));
		return hotel;
	};
	
	/**	SQL操作用変数 */
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**	テーブル名 */
	private final String TABLE_NAME="hotels";
	
	/**
	 * 全件検索を行う.
	 * 
	 * @return 全件ホテル一覧
	 */
	public List<Hotel> findAll(){
		  String sql="select id,area_name,hotel_name,address,"
		  		+ "nearest_station,price,parking from "
				  +TABLE_NAME+" order by price;";
		  List<Hotel> hotelList= template.query(sql, HOTEL_ROW_MAPPER);
		  return hotelList;
		  
	}
	
	/**
	 * 金額以下のホテル一覧を検索する
	 * 
	 * @param price　金額
	 * @return　金額以下のホテル一覧
	 */
	public List<Hotel> findByPrice(Integer price){
		  String sql="select id,area_name,hotel_name,address,"
			  		+ "nearest_station,price,parking from "
					  +TABLE_NAME+" where price<=:price"
					  		+ " order by price;";
		  SqlParameterSource param =new MapSqlParameterSource("price",price);
			  List<Hotel> hotelList= template.query(sql,param, HOTEL_ROW_MAPPER);
			  return hotelList;
	}
	
}
