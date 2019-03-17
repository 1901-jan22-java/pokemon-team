package com.pokemon.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pokemon.models.Trainer;
import com.pokemon.services.TrainerService;

@Component
public class TrainerValidator implements Validator {

	 @Autowired
	 private TrainerService trainerService;
	
	 @Override
    public boolean supports(Class<?> aClass) {
        return Trainer.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Trainer trainer = (Trainer) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (trainer.getUsername().length() < 6 || trainer.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (trainerService.findByUsername(trainer.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (trainer.getPassword().length() < 8 || trainer.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!trainer.getPasswordConfirm().equals(trainer.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
