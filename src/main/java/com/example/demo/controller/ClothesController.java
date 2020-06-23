package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Clothes;
import com.example.demo.service.ClothesService;

/**
 * 衣類検索画面を表示するコントローラ.
 * 
 * @author hyoga.ito
 *
 */
@Controller
@RequestMapping("/ex03")
public class ClothesController {
	/**	サービスの参照を注入 */
	@Autowired
	private ClothesService clothesService;
	
	/**
	 * 衣類検索画面を表示する.
	 * 
	 * 色を表示先に渡すため、色一覧を取得する
	 * 
	 * @param model　リクエストスコープ
	 * @return　衣類検索画面
	 */
	@RequestMapping("")
	public String index(Model model){
		List<Clothes> ClothesColorList=clothesService.colorList();
		model.addAttribute("ClothesColorList", ClothesColorList);
		return "ex-03-clothes-search";
	}
	
	/**
	 * 衣類検索結果画面を衣類検索画面に表示する.
	 * 
	 * @param gender 性別　0=男,1=女
	 * @param color　色
	 * @param model　リクエストスコープ
	 * @return　衣類検索画面
	 */
	@RequestMapping("search")
	public String search(Integer gender,String color,Model model) {
		List<Clothes> clothesList=clothesService.searchByColorAndGender(color, gender);
		if(clothesList.size()==0) {
			model.addAttribute("noResult", "検索結果に合うものはありません。");
		}else {
			model.addAttribute("clothesList", clothesList);
			
		}
		
		return index(model);
	}
	
}
