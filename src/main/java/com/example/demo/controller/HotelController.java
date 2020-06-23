package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Hotel;
import com.example.demo.service.HotelService;

/**
 * ホテル検索を行う画面を表示するコントローラ.
 * 
 * @author hyoga.ito
 *
 */
@Controller
@RequestMapping("/ex02")
public class HotelController {

	/** サービスの参照を注入 */
	@Autowired
	private HotelService hotelService;

	/**
	 * ホテル検索画面を表示する.
	 * 
	 * @return ホテル検索画面
	 */
	@RequestMapping("")
	public String index() {
		return "ex-02-hotel-search";
	}

	/**
	 * 入力された金額以下のホテル一覧を検索し、表示する.
	 * 
	 * @param price 金額
	 * @param model リクエストスコープ
	 * @return ホテル検索画面
	 */
	@RequestMapping("/search")
	public String search(Integer price, Model model) {
		List<Hotel> hotelList = new ArrayList<>();

		if (price == null) {
			hotelList = hotelService.showAllList();
			model.addAttribute("hotelList", hotelList);
		} else if (price < 0) {
			model.addAttribute("error", "不正な数字が入力されました!");
		} else {
			hotelList = hotelService.showUnderPriceHotelList(price);
			model.addAttribute("hotelList", hotelList);

		}

		if (hotelList.size() == 0) {
			model.addAttribute("notFindHotel", "ホテルが見つかりませんでした");
		}

		return index();

	}

}
