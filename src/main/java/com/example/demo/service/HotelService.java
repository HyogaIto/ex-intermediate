package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Hotel;
import com.example.demo.repository.HotelRepository;

/**
 * ホテル情報を操作するサービス.
 * 
 * @author hyoga.ito
 *
 */
@Service
public class HotelService {
	
	/**	リポジトリの参照情報を注入 */
	@Autowired
	private HotelRepository hotelRepository;
	

	
	/**
	 * ホテル情報の全件検索を行う.
	 * 
	 * @return　全ホテルリスト
	 */
	public List<Hotel> showAllList(){
		List<Hotel> hotelList=hotelRepository.findAll();
		return hotelList;
	}
	
	/**
	 * 金額以下のホテル一覧を検索する.
	 * 
	 * @param price 金額
	 * @return　金額以下のホテル一覧
	 */
	public List<Hotel> showUnderPriceHotelList(Integer price){
		List<Hotel> hotelList=hotelRepository.findByPrice(price);
		return hotelList;
	}
	
	
	
}
