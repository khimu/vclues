package com.vclues.core.data;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@XmlRootElement
@Document(collection = "guess")
public class Guess extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String answer;

	@Indexed(unique = false)
	@Field(value = "user_id")
	private Long userId;

	@DBRef
	@Indexed(unique = false)
	@Field(value = "player")
	private Player player;

	@DBRef
	@Indexed(unique = false)
	@Field(value = "game")
	private Game game;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
