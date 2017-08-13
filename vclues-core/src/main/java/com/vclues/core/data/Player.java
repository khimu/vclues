package com.vclues.core.data;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Script;

@XmlRootElement
@Document(collection = "player")
public class Player extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	@Indexed(unique = false)
	@Field(value = "cast_id")
	private Long castId;

	@Indexed(unique = false)
	@Field(value = "user_id")
	private Long userId;

	@DBRef
	@Indexed(unique = false)
	@Field(value = "game")
	private Game game;
	
	@DBRef
	@Indexed(unique = false)
	@Field(value = "guess")
	private Guess guess;

	@Field(value = "cast_name")
	private String castName;
	
	@Indexed(unique = false)
	@Field(value = "script_id")
	private Long scriptId;
	
	@Indexed(unique = false)
	@Field(value = "hint_id")
	private Long hintId;

	@Transient
	private Script script;
	
	@Transient 
	private Hint hint;

	@Transient
	private List<Script> scripts;

	@Transient
	private List<Hint> hints;

	@Transient
	private List<Announcement> announcements;

	/*
	 * done with game
	 */
	@Field(value = "done")
	private boolean done;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Script getScript() {
		return script;
	}

	public void setScript(Script script) {
		this.script = script;
	}

	public List<Script> getScripts() {
		return scripts;
	}

	public void setScripts(List<Script> scripts) {
		this.scripts = scripts;
	}

	public List<Hint> getHints() {
		return hints;
	}

	public void setHints(List<Hint> hints) {
		this.hints = hints;
	}

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}

	public Guess getGuess() {
		return guess;
	}

	public void setGuess(Guess guess) {
		this.guess = guess;
	}

	public Long getScriptId() {
		return scriptId;
	}

	public void setScriptId(Long scriptId) {
		this.scriptId = scriptId;
	}

	public Long getHintId() {
		return hintId;
	}

	public void setHintId(Long hintId) {
		this.hintId = hintId;
	}

	public Hint getHint() {
		return hint;
	}

	public void setHint(Hint hint) {
		this.hint = hint;
	}
}
