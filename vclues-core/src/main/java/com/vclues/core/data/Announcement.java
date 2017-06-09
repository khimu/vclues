package com.vclues.core.data;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@XmlRootElement
@Document(collection = "announcement")
public class Announcement extends Base {

	private static final long serialVersionUID = 1L;

	@Field(value = "message")
	private String message;

	@Field
	private String name;

	@Indexed(unique = false)
	@Field(value = "user_id")
	private Long userId;

	@Indexed(unique = false)
	@Field(value = "cast_id")
	private Long castId;

	@Indexed(unique = false)
	@Field(value = "game")
	private Game game;

	@Field(value = "image")
	private String image;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCastId() {
		return castId;
	}

	public void setCastId(Long castId) {
		this.castId = castId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
