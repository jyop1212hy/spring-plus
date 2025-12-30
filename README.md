# 🚀 Spring Plus (Expert)

Spring Boot 기반 REST API 서버에 JWT 인증/인가 + Docker + AWS 배포까지 직접 구현한 프로젝트입니다.
기초 골격부터 배포 환경까지 백엔드 실무 흐름을 그대로 따라가며 구성했습니다.

⸻

## 📌 프로젝트 개요
•	Spring Boot 기반 REST API 서버
•	JWT 기반 인증 / 인가 구조 구현
•	Docker 이미지로 패키징 후 AWS EC2 배포
•	RDS(MySQL) 연동

⸻

## ⚙️ 기술 스택
•	Java 17
•	Spring Boot 3.3.3
•	Spring Data JPA
•	Spring Security
•	JWT (jjwt)
•	MySQL / H2
•	Docker
•	AWS EC2 / RDS

⸻

## 📁 프로젝트 구조

src/main/java
└─ org.example.expert
├─ config
│  ├─ JwtUtil                # JWT 생성 / 검증 / Claims 추출
│  ├─ JwtFilter              # JWT 인증 필터
│  └─ GlobalExceptionHandler # 전역 예외 처리
├─ domain
│  ├─ todo                   # Todo 도메인
│  ├─ user                   # User 도메인
│  └─ common                 # 공통 DTO / Exception
└─ ExpertApplication


⸻

## 🔐 인증 / 인가 구조

✅ JWT Util
•	토큰 생성
•	Bearer Prefix 제거
•	토큰 검증
•	Claims 추출

✅ JWT Filter
•	요청 헤더에서 JWT 추출
•	토큰 검증 후 인증 정보 설정
•	Spring Security SecurityContext 연동

Spring Security의 세부 인가 정책(@PreAuthorize 등) 은 다음 단계에서 확장 예정

⸻

🩺 Health Check API
•	서버 Live 상태 확인용 API
•	인증 없이 누구나 접근 가능

GET /health


⸻

## 🐳 Docker & 배포

1️⃣ JAR 파일 빌드

./gradlew clean bootJar -x test

2️⃣ Docker 이미지 생성

docker build --platform linux/amd64 -t expert .

3️⃣ Docker 실행

docker run -p 8080:8080 --name simple-aws expert


⸻

## ☁️ AWS 인프라 구성

아키텍처 개요

[ Client ]
↓
[ EC2 (Spring Boot + Docker) ]
↓
[ RDS (MySQL) ]

- [AWS_RDS_데이터베이스_생성.pdf](image/AWS_RDS_데이터베이스_생성.pdf)
- [EC2_인바운드_규칙.pdf](image/EC2_인바운드%20규칙.pdf)
- [EC2_인스턴스_시작.pdf](image/EC2_인스턴스%20시작.pdf)

⸻

## 🔧 환경 설정 (application.yml 예시)

spring:
datasource:
url: jdbc:mysql://{RDS_ENDPOINT}:3306/{DB_NAME}
username: admin
password: {PASSWORD}
driver-class-name: com.mysql.cj.jdbc.Driver

jwt:
secret:
key: {BASE64_SECRET_KEY}