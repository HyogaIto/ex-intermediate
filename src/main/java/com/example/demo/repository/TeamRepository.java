package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Team;

/**
 * チーム情報を操作するリポジトリ.
 * 
 * @author hyoga.ito
 *
 */
@Repository
public class TeamRepository {
	/**　SQLの内容をドメインオブジェクトに入れるローマッパー */
	private final RowMapper<Team> TEAM_ROW_MAPPER = (rs, i) -> {
		Team team = new Team();
		team.setId(rs.getInt("id"));
		team.setLeagueName(rs.getString("league_name"));
		team.setTeamName(rs.getString("team_name"));
		team.setHeadquarters(rs.getString("headquarters"));
		team.setInauguration(rs.getString("inauguration"));
		team.setHistory(rs.getString("history"));
		return team;
	};
	/** テーブル名 */
	private final String TABLE_NAME = "teams";
	
	/** SQL実行用変数 */
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * チーム情報を全件検索メソッド.
	 * 
	 * @return チーム情報一覧
	 */
	public List<Team> findAll(){
		String sql="select id,league_name,team_name,headquarters,"
				+ "inauguration,history from "+TABLE_NAME+" order by inauguration;";
		List<Team> teamList=template.query(sql, TEAM_ROW_MAPPER);
		return teamList;
	}
	
	
	/**
	 * IDからチーム情報を1件検索するメソッド.
	 * 
	 * @param id チームID
	 * @return　チーム情報
	 */
	public Team findById(Integer id){
		String sql="select id,league_name,team_name,headquarters,"
				+ "inauguration,history from "+TABLE_NAME+" where id=:id;";
		SqlParameterSource param=new MapSqlParameterSource("id", id);
		Team team = template.queryForObject(sql, param, TEAM_ROW_MAPPER);
		return team;
	}

}
