## README

Spring Plus (Expert)

📌 프로젝트 개요
•	Spring Boot 기반 REST API 서버
•	JWT 기반 인증 구조를 적용하기 위한 기본 골격 프로젝트
•	Docker를 이용한 배포 환경 구성

⸻

⚙️ 기술 스택
•	Java 17
•	Spring Boot 3.3.3
•	Spring Data JPA
•	Spring Security (필터 기반, 단계적 적용)
•	JWT (jjwt)
•	MySQL / H2
•	Docker
•	Gradle

⸻

📂 패키지 구조 (핵심만)

src/main/java
└─ org.example.expert
├─ config
│  ├─ JwtUtil
│  └─ JwtFilter
├─ domain
│  ├─ todo
│  └─ user
└─ common


⸻

🔐 인증 / 인가 구조 (현재 단계)
•	JWT 토큰 발급 및 검증 로직 구현
•	Authorization 헤더의 Bearer {token} 형식 사용
•	토큰 만료 시간 설정
•	Security는 추가 적용 예정 (골격 우선)

⸻

🩺 Health Check API

서버 정상 동작 여부를 확인하기 위한 API

요청

GET /health

응답 예시

{
"status": "UP"
}

	•	인증 없이 누구나 접근 가능

⸻

🐳 Docker 실행 방법

1️⃣ JAR 파일 생성

./gradlew clean bootJar -x test

2️⃣ Docker 이미지 빌드

docker build --platform linux/amd64 -t aws-test .

3️⃣ 컨테이너 실행

docker run -d -p 8080:8080 --name aws-test-container aws-test

⸻

🔑 환경 변수 설정 (application.yml)

jwt:
secret:
key: {BASE64_SECRET_KEY}


⸻

✅ 현재 진행 상태
•	프로젝트 기본 구조 구성
•	JWT Util 구현
•	Health Check API 구현
•	Docker 빌드 및 실행
•	Spring Security 단계적 적용 예정

⸻