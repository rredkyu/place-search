# place-search



## 프로젝트 설명 

- 장소 검색 서비스를 위한 서버 프로그램 구현 
- API 테스트 방법: curl 기재



## API Test

- docker install 및 redis docker install  필요 

- ```
  $ docker pull redis
  $ sudo docker run -p 6379:6379 redis
  ```

  

- ```
  curl "http://localhost:8080?keyword={keyword}" 
  ```

- ```
  curl "http://localhost:8080/keyword/ranking" 
  ```

  

## 기술 스택 

- Spring Boot 2.7.11
- Java 11 버전
- Gradle 7.6.1
- Redis
  - Sorted Set을 활용한 실시간 순위정보를 나타내기 좋다고 생각
- lombok
- open feign
  - 외부 REST API 사용이 쉽고, 기타 configuration 설정이 용이
  - annotation 기반으로 사용하기 용이



## 상세 구현 설명 

- SOLID OCP 원칙을 준수해 kakao, naver 외의 추가사항이 왔을때 기능의 변경 최소화 
- 키워드 별로 검색된 횟수의 경우 Redis Sorted Set을 활용한 키워드 검색 순위 및 횟수 조회 
  - 동시성 이슈 역시 Sorted Set으로 해결될거라 생각 
  - 추후에 search-place 서버가 늘어나더라도 확장성 용이 

### 
