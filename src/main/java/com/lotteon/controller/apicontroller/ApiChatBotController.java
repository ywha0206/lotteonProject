package com.lotteon.controller.apicontroller;

import com.lotteon.dto.requestDto.ChatReqDto;
import com.lotteon.dto.responseDto.ChatRespDto;
//import com.lotteon.service.ChatBotService;
import com.lotteon.entity.product.Product;
import com.lotteon.service.ChatBotService;
import com.lotteon.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chatbot")
@RequiredArgsConstructor
public class ApiChatBotController {

    private final RestTemplate template;
    private final ProductService productService;
    private final ChatBotService chatBotService;

    @GetMapping("/chat")
    public String chat(@RequestParam(name = "prompt")String prompt){
        String regex = "(화장품|가전|의류|식품|도서|여성의류|남성의류|브랜드의류|악기|취미용품|스포츠브랜드|로션|스마트폰|상의|캐쥬얼브랜드|유아의류|선크림|라면|자켓|코트|스킨|원피스|삼성|아이폰)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(prompt);
        if (prompt.contains("상품")) {
            String result = chatBotService.findBestProduct(prompt);
            return result;
        }
        if(matcher.find()){
            String result = chatBotService.findBestCategory(prompt);
            return result;
        }

        if(prompt.contains("주문취소")){
            if(prompt.contains("쳇봇주문취소")){
                String result = chatBotService.cancleOrder(prompt);
                return result;
            }
            if(prompt.contains("주문취소조회")){
                String result = chatBotService.possibleOrder(prompt);
                return result;
            }

            return "주문 취소 방법을 알려드리겠습니다. 주문 취소는 판매자가 배송을 보내기 전 상태에 가능합니다. \n 마이페이지의 주문내역이나 메인페이지에서 확인 가능합니다. \n 취소가능한 주문번호를 조회하시려면 주문취소조회 를 입력해주세요. \n 주문취소를 진행하시려면 \n 쳇봇주문취소 : 주문번호를 입력해주세요.";
        }
        if(prompt.contains("배송조회")){
            if(prompt.contains("쳇봇배송조회")){
                String result = chatBotService.selectDeliDate(prompt);
                return result;
            }
            return "배송예정일 조회 방법을 알려드리겠습니다. 배송조회는 판매자가 배송을 보낸 후 상태에 가능합니다. \n 마이페이지의 주문내역이나 메인페이지에서 확인 가능합니다. \n 배송조회가 가능한 주문번호를 조회하시려면 배송예정일조회 를 입력해주세요. \n 배송조회를 진행하시려면 \n 쳇봇배송조회 : 주문번호를 입력해주세요.";
        }

        if(prompt.contains("배송예정일조회")){
            String result = chatBotService.possibleDeli(prompt);
            return result;
        }



        ChatReqDto request = new ChatReqDto("gpt-3.5-turbo", prompt);
        ChatRespDto chatGPTResponse =  template.postForObject("https://api.openai.com/v1/chat/completions", request, ChatRespDto.class);
        return chatGPTResponse.getChoices().get(0).getMessage().getContent();
    }
}