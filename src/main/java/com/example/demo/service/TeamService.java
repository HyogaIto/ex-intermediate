package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Team;
import com.example.demo.repository.TeamRepository;

/**
 * チーム情報を操作するリポジトリ.
 * 
 * @author hyoga.ito
 *
 */
@Service
public class TeamService {
	/** リポジトリへの参照情報を注入する */
	@Autowired
	private TeamRepository teamRepository;

	/**
	 * 全件検索を行うメソッドを呼ぶメソッド.
	 * 
	 * @return 全件チーム情報
	 */
	public List<Team> showList() {
		List<Team> teamList = teamRepository.findAll();
		return teamList;
	}

	/**
	 * チーム情報を1件取得するメソッドを呼ぶメソッド.
	 * 
	 * @param id　チームID
	 * @return　検索されたチーム情報
	 */
	public Team showTeam(Integer id) {
		Team team = teamRepository.findById(id);
		return team;
	}
}
