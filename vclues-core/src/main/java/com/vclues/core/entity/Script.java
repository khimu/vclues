package com.vclues.core.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "script")
public class Script extends AbstractEntity implements Order {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String video;

	private String audio;

	private String image;

	@Type( type = "text" )
	private String text;

	@Type( type = "text" )
	private String secret;

	private String sound;

	private Integer position;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cast_id")
	private Cast cast;

	@ManyToOne(optional = false)
	@JoinColumn(name = "scene_id")
	private Scene scene;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Cast getCast() {
		return cast;
	}

	public void setCast(Cast cast) {
		this.cast = cast;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getAudio() {
		return audio;
	}

	public void setAudio(String audio) {
		this.audio = audio;
	}

}
