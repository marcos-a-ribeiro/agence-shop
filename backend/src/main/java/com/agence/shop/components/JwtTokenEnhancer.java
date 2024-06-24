package com.agence.shop.components;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.agence.shop.entities.User;
import com.agence.shop.repositories.UserRepository;
import com.agence.shop.services.exceptions.EntityNotFoundException;
import com.agence.shop.services.exceptions.ResourceNotFoundException;

@Component
public class JwtTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user;
		try {
			user = (User) userRepository.findByEmail(authentication.getName()).orElseThrow();
			
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("User does not exists");
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", user.getId());
		map.put("userName", user.getName());
		map.put("userEmail", user.getEmail());

		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);
		
		return accessToken;
	}

}
