# [프로젝트] React - Spring Boot 상품 관리 API 구현
## 프로젝트 설명 😎
게임 재고 정보가 저장된 데이터베이스와 주문 관리 데이터베이스를 사용한 게임 판매 웹페이지로  React로 만들어진 Front End가 정해져있는 상황에서 spring boot를 이용한 재고 관리 api와 주문 관리 api 개발을 했습니다.

[클론 프로젝트](https://github.com/y005/react-springboot-rest-api) 의 응용 프로젝트 입니다.

### 기존 기능
- [x] 구매 가능한 게임 품목 확인 기능
- [x] 게임 품목 관리 기능
- [x] 게임 주문 웹페이지

### 추가 기능

- [x] 재고 물량 확인
- [x] 유효한 구매 물량에 대한 유효성 검사
- [x] 게임 장르별 탐색 기능
- [x] 장바구니 초기화 버튼
- [X] 웹페이지 UI 스타일 변경
- [X] 조회 api 페이징 지원

## REST API 사용 명세서
- products API 
  - 조회
  
  | name  | type | description          |
  |-----|----------------------|---|
  | page  | int | default is 0         |
  | size  | int | default is 5         |
  | genre | str | default is all genre |
  | name  | str | optional             |
  | id     | str | pathvariable |

  ```
  GET http://localhost:8080/api/v1/products
  ```
  ```
  [
  {
    "id": 50,
    "name": "The Legend of Zelda",
    "genre": "Adventure",
    "quantity": 2,
    "price": 75000,
    "img": "http://image.auction.co.kr/itemimage/15/1f/28/151f28b126.jpg"
  },
  {
    "id": 51,
    "name": "OverWatch",
    "genre": "FPS",
    "quantity": 8,
    "price": 25000,
    "img": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRyal5mLSCi24Lhz-JC0lZPES6G2NIzt5sZVA&usqp=CAU"
  },
  {
    "id": 52,
    "name": "Tetris",
    "genre": "Puzzle",
    "quantity": 2,
    "price": 15000,
    "img": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvxI36xsG8s85ZdmEAU5_duSH2DerSNNrRnw&usqp=CAU"
  }
  ]
  ```
  
  ```
  GET http://localhost:8080/api/v1/products?genre=Adventure
  ```
  ```
  [
  {
    "id": 50,
    "name": "The Legend of Zelda",
    "genre": "Adventure",
    "quantity": 2,
    "price": 75000,
    "img": "http://image.auction.co.kr/itemimage/15/1f/28/151f28b126.jpg"
  }
  ]
  ```
  ```
  GET http://localhost:8080/api/v1/products/50
  ```
  ```
  [
  {
    "id": 50,
    "name": "The Legend of Zelda",
    "genre": "Adventure",
    "quantity": 2,
    "price": 75000,
    "img": "http://image.auction.co.kr/itemimage/15/1f/28/151f28b126.jpg"
  }
  ]
  ```
  - 생성
  ```
  POST http://localhost:8080/api/v1/products
  Content-Type: application/json
    
  {
    "name": "39",
    "genre": "Adventure",
    "quantity": 10,
    "price": 75000,
    "img": ""
  }
  ```
  ```
  successfully create product
  ```
  - 수정
  ```
  PUT http://localhost:8080/api/v1/products
  Content-Type: application/json

  {
   "id": 53
   "name": "39",
   "genre": "Adventure",
   "quantity": 100,
   "price": 75000,
   "img": ""
  }
  ```
  ```
  successfully update product
  ```
  
  - 삭제
  ```
  DELETE http://localhost:8080/api/v1/products/55
  ```
  ```
  successfully delete product
  ```

- orders API
  - 생성
  ```
  POST http://localhost:8080/api/v1/orders
  Content-Type: application/json
    
  {
    "email": "y005@naver.com",
    "address": "서울시",
    "postcode": "06708",
    "total_price": 750000,
    "orderItems": [
    {
      "id": 50,
      "genre": "adventure",
      "quantity": 10,
      "price": 75000
     }
    ]
  }
  ```
  ```
  successfully create order
  ```

## 게임 SHOP 웹페이지 설명 
- 가게에서 구매 가능한 상품 정보와 수량을 확인할 수 있습니다.
![](https://velog.velcdn.com/images/y005/post/4f17097d-bc17-4dfe-b807-756b80d2e800/image.png)
- 사용자는 특정 게임 장르의 상품 조회가 가능합니다.
![](https://velog.velcdn.com/images/y005/post/07710802-35c0-40d8-a28d-ed2180328dd9/image.png)
- 사용자의 배송 정보를 다 입력한 후 결제하기를 누르면 결제가 정상적으로 등록됩니다.
  ![](https://velog.velcdn.com/images/y005/post/201094fe-f2eb-4459-9325-ce37719f8fe8/image.png)
  
  ![](https://velog.velcdn.com/images/y005/post/8358fbc2-87c8-4624-802e-2bbe3d3859ec/image.png)
  
  ![](https://velog.velcdn.com/images/y005/post/95f1ae65-1f67-404c-aa07-c5dd57348170/image.png)
- 재고 이상의 물품을 구매한 경우 결제가 이뤄지지 않습니다.
- 장바구니 초기화가 가능합니다.
