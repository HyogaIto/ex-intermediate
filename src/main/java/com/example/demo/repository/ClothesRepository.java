package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Clothes;

/**
 * 衣類情報を操作するリポジトリ.
 * 
 * @author hyoga.ito
 *
 */
@Repository
public class ClothesRepository {
	
	/**	衣類オブジェクトにSQLの結果を入れるローマッパー */
	private final RowMapper<Clothes> CLOTHES_ROW_MAPPER=(rs,i)->{
		Clothes clothes=new Clothes();
		clothes.setId(rs.getInt("id"));
		clothes.setCategory(rs.getString("category"));
		clothes.setGenre(rs.getString("genre"));
		clothes.setGender(rs.getInt("gender"));
		clothes.setColor(rs.getString("color"));
		clothes.setPrice(rs.getInt("price"));
		clothes.setSize(rs.getString("size"));
		return clothes;
	};
	
	/**	色検索用ローマッパー */
	private final RowMapper<Clothes> CLOTHES_COLOR_ROW_MAPPER=(rs,i)->{
		Clothes clothes=new Clothes();
		clothes.setColor(rs.getString("color"));
		return clothes;
	};
	
	/**	SQL実行用変数 */
	@Autowired
	NamedParameterJdbcTemplate template;
	
	/**	テーブル名 */
	private final String TABLE_NAME="clothes";
	
	/**
	 * 用いられている色を重複なくすべて検索する.
	 * 
	 * @return 重複なしの色が入った衣類オブジェクト
	 */
	public List<Clothes> findNotDuplicationColor(){
		String sql="select distinct color from "+TABLE_NAME;
		List<Clothes> clothesColorList= template.query(sql, CLOTHES_COLOR_ROW_MAPPER);
		return clothesColorList;
	}
	
	
	/**
	 * 色と性別にあった服を検索する.
	 * 
	 * @param gender 性別　0=men,1=women
	 * @param color　色
	 * @return　検索結果の服一覧
	 */
	public List<Clothes> findByColorAndGender(Integer gender,String color) {
		String sql="select id,category,genre,gender,color,price,size "
				+ "from "+TABLE_NAME+" where gender=:gender and color=:color";
		SqlParameterSource param=new MapSqlParameterSource("gender",gender).addValue("color", color);
		List<Clothes> clothesList= template.query(sql, param,CLOTHES_ROW_MAPPER);
		return clothesList;
	}
}
