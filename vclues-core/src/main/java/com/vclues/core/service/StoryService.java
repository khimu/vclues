package com.vclues.core.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.ssm.api.InvalidateMultiCache;
import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughMultiCache;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.entity.Cast;
import com.vclues.core.entity.Hint;
import com.vclues.core.entity.Scene;
import com.vclues.core.entity.Script;
import com.vclues.core.entity.Story;
import com.vclues.core.mongo.repository.AnnouncementRepository;
import com.vclues.core.mongo.repository.GameRepository;
import com.vclues.core.repository.CastRepository;
import com.vclues.core.repository.HintRepository;
import com.vclues.core.repository.SceneRepository;
import com.vclues.core.repository.ScriptRepository;
import com.vclues.core.repository.StoryRepository;
import com.vclues.core.repository.UserRepository;

@Service
public class StoryService implements IStoryService {
	
	private final Logger logger = LoggerFactory.getLogger(StoryService.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StoryRepository storyRepository;
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private AnnouncementRepository announcementRepository;
    
    @Autowired
    private CastRepository castRepository;
    
    @Autowired
    private HintRepository hintRepository;
    
    @Autowired
    private SceneRepository sceneRepository;
    
    @Autowired
    private ScriptRepository scriptRepository;
    
    public void deleteStory(Long storyId) {
    	storyRepository.deleteStory(storyId);
    }
    /*
    * Somehow not getting a new object from DB and causing stale state exeception on 
    * second edit request
    */
    @InvalidateMultiCache(namespace = "stories")
    public Story saveStory(@ParameterValueKeyProvider Story story) {
    	return saveSingleCacheStory(story);
    }
    
    @InvalidateSingleCache(namespace = "story")
    public Story saveSingleCacheStory(@ParameterValueKeyProvider Story story) {
    	return storyRepository.save(story);
    }
    
    @ReadThroughMultiCache(namespace = "stories")
    public List<Story> getAllStories() {
    	return storyRepository.findAll();
    }
    
    /*
     * Somehow not getting a new object from DB and causing stale state exeception on 
     * second edit request
     * 
     * (non-Javadoc)
     * @see com.vclues.core.service.IStoryService#getStory(java.lang.Long)
     */
    @ReadThroughSingleCache(namespace = "story")
    public Story getStory(@ParameterValueKeyProvider Long storyId) {
    	return storyRepository.findOne(storyId);
    }
	
	public List<Script> getAllScriptsBySceneId(Long sceneId) {
		return scriptRepository.getAllScriptsBySceneId(sceneId);
	}
	
	public void deleteScript(Long scriptId) {
		scriptRepository.deleteScript(scriptId);
	}

	public List<Scene> getAllSceneByStoryId(Long storyId) {
		return sceneRepository.getAllSceneByStoryId(storyId);
	}
	
	public void deleteScene(Long sceneId) {
		sceneRepository.deleteScene(sceneId);
	}
	
	public List<Hint> getAllHintBySceneId(Long sceneId) {
		List<Hint> hints = new ArrayList<Hint>();
		hints.add(hintRepository.getAllHintBySceneId(sceneId));
		return hints;
	}
	
	public void deleteHint(Long hintId) {
		hintRepository.deleteHint(hintId);
	}
	
	public void deleteCast(Long castId) {
		castRepository.deleteCast(castId);
	}
	
	public List<Cast> getAllCastByStoryId(Long storyId) {
		return castRepository.getAllCastByStoryId(storyId);
	}
	
	public Cast saveCast(Cast cast) {
		Story story = cast.getStory();
		cast.setStory(story);
		return castRepository.save(cast);
	}
	
	public Hint saveHint(Hint hint) {
		Scene scene = sceneRepository.findOne(hint.getScene().getId());
		//Integer position = sceneRepository.countByStoryId(scene.getStory().getId());
		hint.setScene(scene);
		hint.setPosition(scene.getPosition());
		return hintRepository.save(hint);
	}
	
	public Scene saveScene(Scene scene) {
		Story story = scene.getStory();
		if(scene.getPosition() == 0) {
			Integer position = sceneRepository.countByStoryId(scene.getStory().getId());
			scene.setPosition(position + 1);
		}
		scene.setStory(story);
		return sceneRepository.save(scene);
	}

	public Script saveScript(Script script) {
		Scene scene = sceneRepository.findOne(script.getScene().getId());
		script.setScene(script.getScene());
		script.setCast(script.getCast());
		//Integer position = sceneRepository.countByStoryId(scene.getStory().getId());
		script.setPosition(scene.getPosition());
		return scriptRepository.save(script);
	}
	
	public Cast getCast(Long castId) {
		//return castRepository.getOne(castId);
		return castRepository.findOne(castId);
	}
	
	public Hint getHint(Long hintId) {
		return hintRepository.findOne(hintId);
	}
	
	public Script getScript(Long scriptId) {
		return scriptRepository.findOne(scriptId);
	}
	
	public Scene getScene(Long sceneId) {
		return sceneRepository.findOne(sceneId);
	}

}
