package com.vclues.core.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Configuration
//@ConfigurationProperties("storage")
@Component("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    //private String location = "upload-dir";
    
    @Value("${asset.storage.location}")
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
