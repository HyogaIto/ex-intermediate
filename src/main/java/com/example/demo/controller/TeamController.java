package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Team;
import com.example.demo.service.TeamService;

/**
 * 画面表示を行うコントローラ.
 * 
 * @author hyoga.ito
 *
 */
@Controller
@RequestMapping("/ex01")
public class TeamController {

	/**	サービスの参照情報を注入 */
	@Autowired
	TeamService teamService;


	/**
	 * チーム一覧を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return　チーム一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Team> teamList = teamService.showList();
		model.addAttribute("teamList", teamList);
		return "ex-01-team-list";
	}

	/**
	 * チーム詳細情報を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @param id　チームID
	 * @return　チーム詳細情報画面
	 */
	@RequestMapping("/team-details")
	public String teamDetails(Model model, Integer id) {
		Team team = teamService.showTeam(id);

		model.addAttribute("team", team);

		return "ex-01-team-details";
	}

}
