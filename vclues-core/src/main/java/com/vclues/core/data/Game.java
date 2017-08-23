package com.vclues.core.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.vclues.core.entity.Hint;

/*
 * Created for each member only if paid
 */
@XmlRootElement
@Document(collection = "game")
public class Game extends Base {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	/*
	 * The story chosen
	 */
	@Indexed(unique = false)
	@Field(value = "story_id")
	private Long storyId;

	/*
	 * Game creator
	 */
	@Indexed(unique = false)
	@Field(value = "user_id")
	private Long userId;

	/*
	 * players of game
	 */
	@Transient
	private List<Player> players;
	
	/*
	 * Cast assignment
	 */
	@DBRef
	@Indexed
	private List<GameCast> gameCast;

	/*
	 * Game name
	 */
	@Field(value = "name")
	private String name;

	/*
	 * number of players
	 */
	@Field(value = "count")
	private Integer count;

	/*
	 * Game start day
	 */
	@Field(value = "started")
	private Date started;

	/*
	 * How frequent to send scripts and hints per scene
	 */
	@Field(value = "frequency")
	private Integer frequency;

	@Field(value = "last_shown")
	private Date lastShown;

	@Field(value = "current_scene")
	private Long sceneId;

	@Transient
	private Hint hint;

	@Transient
	private List<Hint> hints;

	@Field(value = "emails")
	private List<String> emails;

	@Transient
	private String invites;

	@Transient
	private List<Announcement> announcements;

	/*
	 * Is the game done
	 */
	@Field(value = "done")
	private Boolean done = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getStoryId() {
		return storyId;
	}

	public void setStoryId(Long storyId) {
		this.storyId = storyId;
	}

	public List<Player> getPlayers() {
		if (players == null) {
			return new ArrayList<Player>();
		}
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public List<GameCast> getGameCast() {
		if(gameCast == null) {
			gameCast = new ArrayList<GameCast>();
		}
		
		return gameCast;
	}

	public void setGameCast(List<GameCast> gameCast) {
		this.gameCast = gameCast;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getLastShown() {
		return lastShown;
	}

	public void setLastShown(Date lastShown) {
		this.lastShown = lastShown;
	}

	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	public Hint getHint() {
		return hint;
	}

	public void setHint(Hint hint) {
		this.hint = hint;
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

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public String getInvites() {
		return invites;
	}

	public void setInvites(String invites) {
		this.invites = invites;
	}

}
