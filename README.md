# Bunnies

## 👨‍🏫 프로젝트 소개
영화 시사회를 예매하는 사이트 백엔드 서버 개발

## 프로젝트 계기
그동안 진행했던 프로젝트는 cache 기능을 사용하지 않고, DB에서 직접 가져오는 구조를 띄었습니다. 이 경우 사용자와 데이터가 많아졌을 때 DB에 많은 부하를 줄 수 있는데, 이를 보완하기 위해 부하를 줄여줄 수 있는 방법 중 하나인 cache를 공부하여 적용해보려고 합니다.


## ⏲️ 개발기간
- 2024.02.14(수) ~ 2024.02.22(목)

## 📚️ 개발환경

### ✔️ Language
<img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white">

### ✔️ Version Control
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### ✔️ IDE
<img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">

### ✔️ Framework
<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">

### ✔️ DB
<img src="https://img.shields.io/badge/supabase-3FCF8E?style=for-the-badge&logo=supabase&logoColor=white"> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">

## 와이어프레임
![GIGABOX1](https://github.com/doojoo9999/GigaBox/assets/57141923/3bad775b-f8ac-44f4-aaf8-67db7a45db2c)


## API 명세서
[API 명세서](https://www.notion.so/478a7c378abb4eff8d56b7de5e682482?v=5193f9bb9b34445a836ec9e43469f2c3)


## ERD
![GIGABOX (1)](https://github.com/doojoo9999/GigaBox/assets/57141923/57ba3332-3207-46e4-8c8c-184d481e375f)

## 📌프로젝트 요약

### 👀 Redis 읽기
- 기존 Supabase 사용 테스트 결과
![image](https://github.com/doojoo9999/GigaBox/assets/96476393/411f7379-7eaf-4b61-a84c-61da9e74e026)


- In-memory cache 사용 테스트 결과
![image](https://github.com/doojoo9999/GigaBox/assets/96476393/e5e31367-b284-4f2b-976c-db58efcb47b0)


- Redis 사용 테스트 결과
![image](https://github.com/doojoo9999/GigaBox/assets/96476393/86584faf-ffa4-4331-968a-4ecb458a1736)


> Supabase에서 Redis로 전환한 결과, 읽기 속도에서 성능 개선이 이루어졌습니다.
> 
> TPS 1.7 → 1459 / 85723% 성능 개선


### ✏️ Redis 쓰기
- 기존 Supabase 사용 테스트 결과
![image](https://github.com/doojoo9999/GigaBox/assets/96476393/bad29847-5604-4cc7-92c9-e1154d780703)


- Redis 사용 테스트 결과
![image](https://github.com/doojoo9999/GigaBox/assets/96476393/7eeef2db-f493-467b-b5ec-40dec90859b4)


> Supabase에서 Redis로 전환한 결과, 쓰기 속도에서도 성능 개선이 이루어졌습니다.
> 
> TPS 12.1 → 3567 / 29625% 성능 개선

## 패키지 구조

```
├─main
│  ├─kotlin
│  │  └─com
│  │      └─teamsparta
│  │          └─gigabox
│  │              ├─client
│  │              │  ├─config
│  │              │  └─oauth2
│  │              │      └─kakao
│  │              │          └─dto
│  │              ├─domain
│  │              │  ├─coupon
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─exception
│  │              │  │  └─dto
│  │              │  ├─member
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─movie_info
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─post
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─receipt
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─reservation
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─screen_info
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─theater
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  ├─theater_info
│  │              │  │  ├─controller
│  │              │  │  ├─dto
│  │              │  │  │  ├─request
│  │              │  │  │  └─response
│  │              │  │  ├─model
│  │              │  │  ├─repository
│  │              │  │  └─service
│  │              │  └─upload
│  │              │      ├─controller
│  │              │      ├─dto
│  │              │      │  └─request
│  │              │      ├─model
│  │              │      ├─repository
│  │              │      └─service
│  │              └─infra
│  │                  ├─aop
│  │                  ├─auditing
│  │                  ├─aws
│  │                  ├─cache
│  │                  ├─querydsl
│  │                  ├─security
│  │                  │  └─jwt
│  │                  ├─swagger
│  │                  └─utility
│  │                      ├─couponutility
│  │                      ├─mailutility
│  │                      └─scheduler
│  └─resources
└─test
    ├─kotlin
    │  └─com
    │      └─teamsparta
    │          └─gigabox
    │              ├─domain
    │              │  ├─coupon
    │              │  │  └─service
    │              │  └─movie_info
    │              │      ├─controller
    │              │      ├─repository
    │              │      └─service
    │              └─infra
    │                  └─cache
    └─resources
```

## 👨🏻‍💻 Built With

* [박병률] - 팀장
* [김성현] - 조원
* [최혜림] - 조원
* [황승현] - 조원
