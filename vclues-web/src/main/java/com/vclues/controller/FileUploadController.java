package com.vclues.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vclues.core.storage.StorageFileNotFoundException;
import com.vclues.core.storage.StorageService;


@Controller("/admin")
public class FileUploadController {

	@javax.annotation.Resource(name="storageService")
    private StorageService storageService;

    @GetMapping("/assets")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService
                .loadAll()
                .map(path ->
                        MvcUriComponentsBuilder
                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
                                .build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    /*
    @PostMapping("/assets")
    public String handleFileUpload(@RequestParam Map<String,String> requestParams, @RequestParam("file") MultipartFile file,
    					RedirectAttributes redirectAttributes, final Model model) {
    	 
    		      
    	// @RequestParam("domain") String domain, @RequestParam("id") String id,
    	String domain = requestParams.get("domain");
    	String id = requestParams.get("id");
    	
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }
    */
    
    //@RequestMapping(value = "/assets", method = RequestMethod.POST)
    @PostMapping("/assets")
    public String handleFileUpload(final @RequestParam("file") MultipartFile file, final RedirectAttributes redirectAttributes, final Model model) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
