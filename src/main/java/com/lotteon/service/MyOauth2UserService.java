package com.lotteon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotteon.config.MyUserDetails;
import com.lotteon.dto.requestDto.PostCustSignupDTO;
import com.lotteon.entity.member.Customer;
import com.lotteon.entity.member.Member;
import com.lotteon.repository.member.CustomerRepository;
import com.lotteon.repository.member.MemberRepository;
import com.lotteon.service.member.CustomerService;
import com.lotteon.service.member.MemberService;
import groovy.lang.Lazy;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
public class MyOauth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;
    private final CustomerRepository customerRepository;

    @Lazy
    private CustomerService customerService;


    public MyOauth2UserService(MemberRepository memberRepository, CustomerRepository customerRepository) {
        this.memberRepository = memberRepository;
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Access Token을 통해 사용자 정보를 가져옵니다.
        String accessToken = userRequest.getAccessToken().getTokenValue();
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // 소셜 로그인 유저 정보 로딩
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info(oAuth2User);

        // OAuth2User의 속성에서 필요한 정보를 추출합니다.
        Map<String, Object> attributes = oAuth2User.getAttributes();
        JsonNode rootNode = new ObjectMapper().convertValue(attributes, JsonNode.class);
        Member member = null;

        if (provider.equals("kakao")) {
            JsonNode account = rootNode.path("kakao_account");
            String custEmail = account.path("email").asText(null);
            String memUid = "KAKAO_SOCIAL_" + rootNode.path("id").asLong();

            if (custEmail != null) {
                Customer customer = customerRepository.findByCustEmail(custEmail);

                if (customer != null) {
                    Optional<Member> optMember = memberRepository.findByCustomer_id(customer.getId());
                    member = optMember.orElse(null);
                } else {
                    // 필드 값이 null일 경우 대비하여 기본값 설정
                    String custName = account.path("profile").path("nickname").asText("Unknown User");
                    String hp = Optional.ofNullable(account.path("phone_number").asText(null)).orElse("Unknown Phone");
                    String custHp = hp;
                    if(!hp.equals("Unknown Phone")){ custHp = "0"+hp.split(" ")[1]; }
                    Boolean custGender = "female".equals(account.path("gender").asText(null));
                    String birthyear = account.path("birthyear").asText("0000");
                    String birthday = account.path("birthday").asText("0000");
                    String custBirth = birthyear + "-" + birthday.substring(0, 2) + "-" + birthday.substring(2, 4);


                    PostCustSignupDTO dto = PostCustSignupDTO.builder()
                            .memId(memUid)
                            .memPwd(UUID.randomUUID()+"")
                            .custHp(custHp)
                            .custName(custName)
                            .custGender(custGender)
                            .custBirth(custBirth)
                            .custOptional(false)
                            .custEmail(custEmail)
                            .basicAddr(false)
                            .build();

                    member = customerService.insertCustomer(dto);
                }
            } else {
                log.warn("Customer email is null for Kakao account: {}", account);
            }
        }
        if (member == null) {
            throw new OAuth2AuthenticationException("User could not be created or retrieved");
        }

        return MyUserDetails.builder()
                .user(member)
                .accessToken(accessToken)
                .attributes(attributes)
                .build();
    }
}
