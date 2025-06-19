package com.tst.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Log4j2 // log.info 등 로그 사용을 위한 어노테이션
@RestController // 컨트롤러 + @ResponseBody 조합 (JSON 반환)
@RequestMapping("/login") // 해당 클래스 내 모든 URL 앞에 /login prefix
public class AuthController {

    // application.properties에서 클라이언트 ID/SECRET 값 주입
    @Value("${naver.client-id}")
    private String naverClientId;

    @Value("${naver.client-secret}")
    private String naverClientSecret;

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.client-secret}")
    private String kakaoClientSecret;

    // 외부 API 요청을 보낼 때 사용하는 스프링 유틸 클래스
    private final RestTemplate restTemplate = new RestTemplate();

    // JSON → Java 객체 (또는 그 반대) 변환을 위한 Jackson 유틸
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 카카오 로그인 리다이렉트 URI
    private final String REDIRECT_URL = "http://127.0.0.1:8080/login/kakao/callback";

    // 1️⃣ 네이버 로그인 콜백 처리
    @GetMapping("/naver/callback")
    public ResponseEntity<?> naverCallback(@RequestParam String code, @RequestParam String state) {
        try {
            // (1) 네이버 토큰 요청 URL (POST 방식으로 요청할 준비)
            String tokenUrl = "https://nid.naver.com/oauth2.0/token";

            // (2) HTTP 헤더에 Content-Type: application/x-www-form-urlencoded 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // (3) 토큰 요청에 필요한 파라미터 세팅
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", naverClientId);
            params.add("client_secret", naverClientSecret);
            params.add("code", code);
            params.add("state", state);

            // (4) 파라미터와 헤더를 함께 HttpEntity에 담음 (POST 요청 바디 준비)
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            // (5) POST 방식으로 토큰 요청 → 응답을 JSON 문자열로 받음
            ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenUrl, request, String.class);

            // (6) 받은 JSON에서 access_token만 추출
            String accessToken = parseAccessToken(tokenResponse.getBody());

            // (7) 사용자 정보 요청 시 Authorization 헤더에 Bearer 토큰 세팅
            HttpHeaders profileHeaders = new HttpHeaders();
            profileHeaders.setBearerAuth(accessToken);
            HttpEntity<String> profileEntity = new HttpEntity<>(profileHeaders);

            // (8) 네이버 사용자 정보 요청 (GET 방식, 토큰 포함된 헤더 전달)
            String profileUrl = "https://openapi.naver.com/v1/nid/me";
            ResponseEntity<String> profileResponse = restTemplate.exchange(profileUrl, HttpMethod.GET, profileEntity, String.class);

            // (9) 사용자 정보 JSON 파싱
            JsonNode json = objectMapper.readTree(profileResponse.getBody());
            JsonNode res = json.get("response");

            String id = res.get("id").asText();
            String name = res.get("name").asText();
            String email = res.get("email").asText();
            String nickname = res.has("nickname") ? res.get("nickname").asText() : "";

            // (10) 사용자 정보 로그 출력
            log.info("▶ 네이버 로그인 정보 - id: {}, name: {}, nickname: {}, email: {}", id, name, nickname, email);

            // (11) 로그인 성공 응답 반환
            return ResponseEntity.ok("네이버 로그인 성공");
        } catch (Exception e) {
            // (12) 예외 발생 시 로그 출력 및 500 에러 반환
            log.error("네이버 로그인 처리 중 에러", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("네이버 로그인 실패");
        }
    }


    // 2️⃣ 카카오 로그인 콜백 처리
    @GetMapping("/kakao/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam String code, @RequestParam String state) {
        try {
            // (1) 카카오 토큰 요청 URL (POST 방식)
            String tokenUrl = "https://kauth.kakao.com/oauth/token";

            // (2) POST 요청에 필요한 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            // (3) 파라미터 구성 (application/x-www-form-urlencoded 형식)
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("grant_type", "authorization_code");
            params.add("client_id", kakaoClientId);
            params.add("client_secret", kakaoClientSecret);
            params.add("redirect_uri", REDIRECT_URL);
            params.add("code", code);

            // (4) 요청 객체 조립
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

            // (5) POST 요청 후 토큰 응답 받기
            ResponseEntity<String> tokenResponse = restTemplate.postForEntity(tokenUrl, request, String.class);

            // (6) JSON에서 access_token 추출
            String accessToken = parseAccessToken(tokenResponse.getBody());

            // (7) 사용자 정보 요청 헤더 설정
            HttpHeaders profileHeaders = new HttpHeaders();
            profileHeaders.setBearerAuth(accessToken);
            HttpEntity<String> profileEntity = new HttpEntity<>(profileHeaders);

            // (8) 카카오 사용자 정보 요청
            String profileUrl = "https://kapi.kakao.com/v2/user/me";
            ResponseEntity<String> profileResponse = restTemplate.exchange(profileUrl, HttpMethod.GET, profileEntity, String.class);

            // (9) 사용자 정보 파싱
            JsonNode json = objectMapper.readTree(profileResponse.getBody());
            String id = json.get("id").asText();
            String email = json.path("kakao_account").path("email").asText(""); // 이메일 없을 수도 있어서 path()
            String nickname = json.path("properties").path("nickname").asText("");

            // (10) 사용자 정보 로그 출력
            log.info("▶ 카카오 로그인 정보 - id: {}, nickname: {}, email: {}", id, nickname, email);

            return ResponseEntity.ok("카카오 로그인 성공");
        } catch (Exception e) {
            log.error("카카오 로그인 처리 중 에러", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("카카오 로그인 실패");
        }
    }

    // 공통 로직: JSON 문자열에서 access_token 추출
    private String parseAccessToken(String responseBody) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(responseBody); // JSON 파싱
        return jsonNode.get("access_token").asText(); // access_token 값 추출
    }
}
