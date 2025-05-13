
![로고](https://github.com/user-attachments/assets/1c1cc665-6405-47d0-b4e5-7c76d23740d2)
#### 테스형
> 테스형, 자기사유 테스트 플랫폼

#### 서비스 접속
[테스형 - 자기사유 테스트 플랫폼](https://tessbro.site)

<a href="https://chivalrous-saffron-326.notion.site/1e10ba93975b80cb80e4d09bd9a4e437?pvs=4"><img src="https://img.shields.io/badge/Notion 링크 보기-E6E6E6?style=for-the-badge&logo=notion&logoColor=black" /></a>
<a href="https://www.notion.so/1f20ba93975b80f0a5b6d7f1d53e80e0?pvs=21"><img src="https://img.shields.io/badge/테스형 산출물-E6E6E6?style=for-the-badge&logo=notion&logoColor=black" /></a><br>
<a href="https://velog.io/@seuo/series/%ED%85%8C%EC%8A%BD" target="_blank" rel="noopener noreferrer">
  <img src="https://img.shields.io/badge/[시리즈 | 테스형 : velog]-20C997?style=for-the-badge&logo=velog&logoColor=black&labelColor=20C997&color=20C997" />
</a>
<br>

**트러블 슈팅**
<!-- Github Action 과정 중 AWS EC2 연결 오류 모음 -->
<a href="https://velog.io/@seuo/Github-Action-%EA%B3%BC%EC%A0%95-%EC%A4%91-AWS-EC2-%EC%97%B0%EA%B2%B0-%EC%98%A4%EB%A5%98-%EB%AA%A8%EC%9D%8C" target="_blank" rel="noopener noreferrer">
  <img src="https://img.shields.io/badge/Github Action 중 EC2 오류 모음-20C997?style=for-the-badge&logo=velog&logoColor=black&labelColor=20C997&color=ffffff" />
</a>
<br>

<!-- AWS EC2 서버 멈춤 및 CPU 상승 이슈 -->
<a href="https://velog.io/@seuo/AWS-EC2-%EC%84%9C%EB%B2%84-%EB%A9%88%EC%B6%A4-%EB%B0%8F-CPU-%EC%83%81%EC%8A%B9-%EC%9D%B4%EC%8A%88" target="_blank" rel="noopener noreferrer">
  <img src="https://img.shields.io/badge/EC2 서버 멈춤 & CPU 상승 이슈-20C997?style=for-the-badge&logo=velog&logoColor=black&labelColor=20C997&color=ffffff" />
</a>
<br>

<!-- Redis 관련 이슈 모음 -->
<a href="https://velog.io/@seuo/Redis-%EA%B4%80%EB%A0%A8-%EC%9D%B4%EC%8A%88-%EB%AA%A8%EC%9D%8C" target="_blank" rel="noopener noreferrer">
  <img src="https://img.shields.io/badge/Redis 관련 이슈 모음-20C997?style=for-the-badge&logo=velog&logoColor=black&labelColor=20C997&color=ffffff" />
</a>

<aside>


<hr>

</aside>

# 개요
![image](https://github.com/user-attachments/assets/41bdcc23-b59a-4a87-87c9-2768c0315349)
![image](https://github.com/user-attachments/assets/30797d94-2409-4f57-86d7-a570ce656eae)
![image](https://github.com/user-attachments/assets/c9c4e7c9-bffe-42dc-9a5e-0941607e7254)
![image](https://github.com/user-attachments/assets/b0cf6fd0-c28f-4ef0-bb42-32a8ee988126)
![image](https://github.com/user-attachments/assets/b71f566c-3637-45e9-bc59-0483ce30d405)


# 주요기능

### 레디스를 이용한 테스트 세션 캐싱

> 테스트 진행 시 세션 데이터 레디스를 이용하여 캐싱
> 

![image](https://github.com/user-attachments/assets/449fb06e-cdb8-408b-ae15-fee7eb7efe68)


### 테스트 결과 추출 알고리즘 구현

> 한개의 선택지 당 2가지의 결과 유형(Style)에 매핑이 가능하므로 style1,2 구분 및 카운트
> 

```java
public int findMostSelectedStyle() {
        // 스타일의 등장 횟수를 기록할 맵
        Map<Integer, Integer> countMap = new HashMap<>();

        // 선택된 박스들에서 style1, style2를 각각 카운트       
        for (SelectedBox box : this.selectedBoxesMap.values()) {
            // style1 등장 횟수 +1
            countMap.put(box.getStyleId1(), countMap.getOrDefault(box.getStyleId1(), 0) + 1);
            // style2 등장 횟수 +1
            countMap.put(box.getStyleId2(), countMap.getOrDefault(box.getStyleId2(), 0) + 1);
        }

        // 가장 많이 등장한 styleId 찾기
        return countMap.entrySet().stream()
                .max(Map.Entry.comparingByValue()) // 값 기준으로 최대값 찾기
                .map(Map.Entry::getKey) // 그 최대값을 가진 key 반환
                .orElse(0); // 아무것도 없으면 기본값 1
    }
```

### 테스트 유형 매핑 프로세스 설계

> 각 선택지는 최대 2가지 결과 유형에 매핑, 균형있고 뻔하거나 편향되지 않는 테스트 진행 가능> 

![image](https://github.com/user-attachments/assets/174da13a-eedb-4949-82c2-fed06ffd7a76)


---

# 기술

## 스택

![image](https://github.com/user-attachments/assets/35e5c321-195f-47b3-8e65-b3e9501d78fa)


## 아키텍쳐

### Https 배포

> Route53을 이용한 Cerificate Manager 인증서(SSL/TLS) 발급 및 도메인 적용 Https 배포

![image](https://github.com/user-attachments/assets/32070664-c598-40e7-a203-4f3655753709)


### 시스템

> AWS EC2 프론트엔드와 백엔드를 Docker 컨테이너로 분리 배포하고, Nginx를 통해 통합 관리


![image](https://github.com/user-attachments/assets/c67149b9-80d0-4ff6-8eb6-4d0d714916e7)


### API 서버

> JPA 기반 Entity-Mapper-Repository 설계를 활용하여 도메인 중심 데이터 처리 구현
Spring Boot 기반 모듈화 된 설계로 확장성과 유지 보수성을 강화한 웹 서비스 제작


![image](https://github.com/user-attachments/assets/fda30aac-fc22-4d5f-ac9d-f96243690657)


### Spring Security

> 프론트엔드와 백엔드 분리 운영에 따른 CORS 정책 설정 및 도메인 허용 처리


![image](https://github.com/user-attachments/assets/8c1c7225-3b19-47f4-95df-c5f0af2e3a9f)


## DevOps

> Git Flow 전략(Main, Develop 브랜치 기반)을 따르며 체계적인 버전 관리 및 협업 환경 유지
> 

![image](https://github.com/user-attachments/assets/06ccb262-13cc-41af-800d-0177949b11b9)


**`FE`**

![image](https://github.com/user-attachments/assets/2924c882-8cda-4789-94ca-56ec2a7015f3)


**`BE`**

> GitHub Action 기반 CI/CD 파이프라인 구축으로 코드 Push 시 Docker 이미지 빌드 및 EC2 배포 자동화
> 

![image](https://github.com/user-attachments/assets/a2ca55a7-4817-4f6b-bd6f-99ff87e7bc6e)


---

## 테스트 및 검증 도구

### Junit 단위 테스트

> API 서버 제작 과정 시 Junit을 이용한 API 단위 테스트
> 

![image](https://github.com/user-attachments/assets/f2e37169-6189-4fb1-9717-e3de8befd67f)


### Postman

> API 서버 제작 후, FE 유사 환경 설정 후 요청/응답 확인
> 

![image](https://github.com/user-attachments/assets/b126cfde-242a-4894-8f49-7990e9ae5ac2)


---
