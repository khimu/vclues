package com.vclues.core.service;

import java.util.List;
import java.util.Map;

import com.vclues.core.data.Announcement;
import com.vclues.core.data.Game;
import com.vclues.core.entity.Cast;
import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Scene;
import com.vclues.core.entity.Script;
import com.vclues.core.entity.Story;

public interface IStoryService {
	
	public List<Story> getAllStories();

	public Story getStory(Long storyId);
	
	public Story saveStory(Story story);
	
	public void deleteStory(Long storyId);

	public List<Script> getAllScriptsBySceneId(Long sceneId);

	public void deleteScript(Long scriptId);

	public List<Scene> getAllSceneByStoryId(Long storyId);

	public void deleteScene(Long sceneId);
	
	public List<Hint> getAllHintBySceneId(Long sceneId);
	
	public void deleteHint(Long hintId);
	
	public void deleteCast(Long castId);
	
	public List<Cast> getAllCastByStoryId(Long storyId);

	public Cast saveCast(Cast cast);
	
	public Hint saveHint(Hint hint);
	
	public Scene saveScene(Scene scene);

	public Script saveScript(Script script);
	
	public Cast getCast(Long castId);
	
	public Hint getHint(Long hintId);
	
	public Script getScript(Long scriptId);
	
	public Scene getScene(Long sceneId);
	
}
