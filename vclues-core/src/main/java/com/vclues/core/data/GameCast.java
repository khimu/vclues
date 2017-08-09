package com.vclues.core.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.vclues.core.entity.Cast;

@XmlRootElement
@Document(collection = "game_cast")
public class GameCast extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/*
	 * user: username
	 * player: name
	 */
	@Indexed(unique = false)
	private String username;

	@DBRef
	@Indexed(unique = false)
	@Field(value = "game")
	private Game game;
	
	@Field(value = "cast_id")
	private Long castId;
	
	/*
	 * players of game
	 */
	@Transient
	private Cast cast;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCastId() {
		return castId;
	}

	public void setCastId(Long castId) {
		this.castId = castId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Cast getCast() {
		return cast;
	}

	public void setCast(Cast cast) {
		this.cast = cast;
	}

}
