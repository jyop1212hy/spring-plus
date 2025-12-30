## README

Spring Plus (Expert)

⸻

📌 프로젝트 개요
•	Spring Boot 기반 REST API 서버
•	JWT 기반 인증/인가를 적용한 프로젝트

⸻

⚙️ 기술 스택
•	Java 17
•	Spring Boot 3.3.3
•	Spring Data JPA
•	Spring Security
•	JWT
•	MySQL / H2
•	Docker

⸻

📁 프로젝트 구조

src/main/java
└─ org.example.expert
├─ config
│  ├─ JwtUtil
│  ├─ JwtFilter
│  └─ GlobalExceptionHandler
├─ domain
│  ├─ todo
│  ├─ user
│  └─ common
└─ ExpertApplication


⸻

🔐 인증 / 인가 구조
•	JWT Util 구현 완료
•	토큰 생성
•	토큰 분리(Bearer 제거)
•	토큰 검증
•	Claims 추출
•	Filter 기반 JWT 인증 처리
•	Security 전체 적용은 단계적으로 진행 중

⸻

🐳 Docker
•	JAR 빌드 후 Docker 이미지 생성
•	eclipse-temurin 기반 이미지 사용

./gradlew clean bootJar -x test
docker build -t expert .
docker run -p 8080:8080 expert

⸻

✅ 현재 진행 상태
•	프로젝트 기본 골격 구성 완료
•	JWT Util 구현 완료
•	JWT Filter 적용
•	Health Check API 구현
•	JAR 빌드 및 Docker 이미지 생성 완료
•	Spring Security 세부 인가 정책은 다음 단계에서 적용 예정
