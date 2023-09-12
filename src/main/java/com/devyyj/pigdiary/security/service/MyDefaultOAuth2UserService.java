package com.devyyj.pigdiary.security.service;

import com.devyyj.pigdiary.common.entity.CommonCode;
import com.devyyj.pigdiary.common.repository.CommonCodeRepository;
import com.devyyj.pigdiary.user.entity.MyUser;
import com.devyyj.pigdiary.user.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MyDefaultOAuth2UserService extends DefaultOAuth2UserService {

    private final MyUserRepository userRepository;
    private final CommonCodeRepository codeRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String socialId = oAuth2User.getName();

        // 회원 아니면 회원 가입
        if (userRepository.findBySocialIdAndDeleted(socialId, false).isEmpty()) {
            String nickName = makeNickName();
            MyUser user = userRepository.save(MyUser.builder()
                    .nickName(nickName)
                    .socialId(socialId)
                    .socialType(userRequest.getClientRegistration().getRegistrationId())
                    .build());
        }

        return oAuth2User;
    }

    private String makeNickName() {
        String nickName = "돼지";
        Optional<CommonCode> adjective = codeRepository.findRandomByCategory("형용사");
        Optional<CommonCode> country = codeRepository.findRandomByCategory("나라");
        if (adjective.isPresent() && country.isPresent()) {
            nickName = adjective.get().getName() + " " + country.get().getName() + " " + nickName;
        }
        return nickName;
    }
}
