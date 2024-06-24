package com.agence.shop.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.agence.shop.dto.UserUpdateDto;
import com.agence.shop.entities.User;
import com.agence.shop.repositories.UserRepository;
import com.agence.shop.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDto> {
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDto dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		var uriVarAtributes = (Map<String, String>) httpRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVarAtributes.get("id"));
		
		Optional<User> optionalUser = userRepo.findByEmail(dto.getEmail());
		if (!optionalUser.isPresent()) {
			User user = optionalUser.get();
			if (userId != user.getId())
				list.add(new FieldMessage("e-mail", "This e-mail address is already being used"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getFieldMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}