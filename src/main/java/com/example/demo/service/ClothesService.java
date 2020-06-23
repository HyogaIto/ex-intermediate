package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Clothes;
import com.example.demo.repository.ClothesRepository;

/**
 * 衣類情報を操作するサービス.
 * 
 * @author hyoga.ito
 *
 */
@Service
public class ClothesService {
	
	@Autowired
	private ClothesRepository clothesRepository;
	
	/**
	 * 重複なしの色を検索する.
	 * 
	 * @return 重複なしの色が入った衣類オブジェクト
	 */
	public List<Clothes> colorList(){
		List<Clothes> clothesList=clothesRepository.findNotDuplicationColor();
		return clothesList;
	}
	
	/**
	 * 色と性別に一致する衣類を検索する.
	 * 
	 * @param color 色
	 * @param gender　性別　0=男,1=女
	 * @return　検索結果の衣類
	 */
	public List<Clothes> searchByColorAndGender(String color,Integer gender) {
		List<Clothes> clothesList = clothesRepository.findByColorAndGender(gender, color);
		return clothesList;
	}
	
	
	
}
