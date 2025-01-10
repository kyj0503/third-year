package com.webproject.kangneng_back.service;

import com.webproject.kangneng_back.dto.*;
import com.webproject.kangneng_back.entity.UserEntity;
import com.webproject.kangneng_back.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }

        else {

            return null;
        }
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        Optional<UserEntity> existData = userRepository.findByUsername(username);

        if (existData.isEmpty()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");
            userEntity.setNewUser(true);  // 신규 사용자 표시

            userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");
            userDTO.setNewUser(true);  // DTO에도 신규 사용자 표시

            return new CustomOAuth2User(userDTO);
        } else {
            UserEntity user = existData.get();
            user.setEmail(oAuth2Response.getEmail());
            user.setName(oAuth2Response.getName());

            userRepository.save(user);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(user.getRole());
            userDTO.setNewUser(user.isNewUser());  // 기존 사용자 상태 유지

            return new CustomOAuth2User(userDTO);
        }
    }
}