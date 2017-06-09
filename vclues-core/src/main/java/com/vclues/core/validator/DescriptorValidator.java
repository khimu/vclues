package com.vclues.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vclues.core.data.Descriptor;
import com.vclues.core.mongo.repository.AnnouncementRepository;

@Component
public class DescriptorValidator implements Validator {
	
	@Autowired
	private AnnouncementRepository descriptorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return Descriptor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Descriptor descriptor = (Descriptor) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact", "NotEmpty");
        
        
        if (descriptor.getName().length() < 2 || descriptor.getName().length() > 32) {
            errors.rejectValue("name", "Size.userForm.name");
        }
        
        if (descriptor.getContact().length() < 2 || descriptor.getContact().length() > 32) {
            errors.rejectValue("contact", "Size.userForm.contact");
        } 
        
        if(descriptor.getDescriptorKey() == null || "".equals(descriptor.getDescriptorKey())) {
        	throw new RuntimeException("There is an issue with the setting of descriptor");
        }
        
        if (descriptor.getDescriptorKey() != null && !"".equals(descriptor.getDescriptorKey()) && descriptorRepository.exists(descriptor.getDescriptorKey()) == true) {
            errors.rejectValue("name", "Duplicate.userForm.name");
            errors.rejectValue("contact", "Duplicate.userForm.contact");
        }
        
    }
}