# :mask:코로나 확진자의 동선을 피해가자:mask:
![image1](https://github.com/user-attachments/assets/7ab9d6c6-c518-483b-87b9-cdace1d5adc8)

# 프로젝트 소개
* 전 세계적으로 코로나19 바이러스가 2020년 내내 확산되고 있는 가운데 우리나라도 최근 신규 확진 환자 600명대를 도달하면서 수도권의 사회적 거리두기 단계가 2.5단계로 격상됨에도 불구하고 확진 환자와의 접촉 가능성을 높이면서 코로나19 바이러스의 확진 상승세를 가속화되고있다.  
* 이에 따라 확진 환자의 방문 지역의 100m 내 근접 시 알림을 받을 수 있는 어플리케이션을 제작해 본 프로젝트의 목적인 거리두기에 경각심을 가지면서 코로나 19 바이러스의 확산 방지에 도움을 줄 수 있다.
<br>

# 프로젝트 구조도
![image](https://github.com/user-attachments/assets/f56b2444-a933-4a57-95fc-80ce4251f2bb)

1. 파이어 베이스에 코로나 19 바이러스의 확진 환자의 정보를 저장한다.  
2. 파이어 베이스의 데이터는 Json 파일 형식으로 전달된다.  
3. 전달 받은 데이터와 Thread를 통해 10초마다 업데이트 되는 현재 위치를 비교해 코로나 19 바이러스의 확진 환자가 방문한 지역에 100m 근접 시 알림을 울린다.  
<br>

# 개발 기간
<strong>2020.08.31 ~ 2020.12.11</strong>
<br>

# 기술 스택
<div>
  <img src="https://img.shields.io/badge/androidstudio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white">
  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white">
  <img src="https://img.shields.io/badge/firebase-DD2C00?style=for-the-badge&logo=firebase&logoColor=white">
</div>
<br>

# APIs
* Google Maps API
  *  Gooole Maps API는 Google 지도 서버 액세스, 데이터 다운로드, 지도 표시, 지도 동작에 대한 응답을 자동으로 처리한다.  
  *  또한 API 호출을 사용하여 기본 지도에 아이콘, 다각형, 오버레이를 추가하고 특정 지도 영역의 사용자 뷰를 변경할 수도 있다.  
  *  이 객체는 지도 위치에 관한 추가 정보를 제공하며 사용자는 이 객체를 통해 지도와 상호작용할 수 있다.
* News API
  * News API 는 웹 전체에서 라이브 기사를 검색하기 위한 간단한 HTTP REST API 이다.
  * 라이브 헤드라인을 표시하는 뉴스 간판 및 기타 애플리케이션의 데이터 소스로 유용하다.
  * 50개 이상의 국가에 걸쳐 7개의 카테고리의 헤드라인과 100개 이상의 상위 간행물 및 블로그에서 실시간으로 헤드라인을 추적한다.
<br>

# 프로젝트 결과
* 스플래시 화면  
  ![image](https://github.com/user-attachments/assets/eb91877c-31c2-4fa8-a64c-fe66d332480a)  

  스플래시 화면이 2초간 화면에 출력된다.
<br>

* 홈 화면
  > 홈 화면 1  
  ![image](https://github.com/user-attachments/assets/3202b126-7419-4221-8cc5-77decb8663ac)  

  각종 코로나 19 바이러스 확진 환자 정보가 CardView 및 HorizontalBarChar로 표시된다.  

  > 홈 화면 2  
  ![image](https://github.com/user-attachments/assets/04ab1566-69f7-4dac-809a-e56a82367a7c)
<br>

* 권한 요청 및 알람
  > 권한 요청  
  ![image](https://github.com/user-attachments/assets/d6ad06f8-3d6f-46f8-98ae-197710ef62c7)

  > 알람  
  ![image](https://github.com/user-attachments/assets/62507b59-c8e1-4169-8bc0-4780b5837a28)  
<br>

* 지도 화면  
  ![image](https://github.com/user-attachments/assets/b6dadfca-d3d1-4208-ba6c-98436bd06d81)

  MapView와 GoogleMapsAPI를 이용해 화면에 확진 환자 방문 지역 표시
<br>

* 뉴스 화면  
  ![image](https://github.com/user-attachments/assets/400cf637-5d3f-451b-a1ba-242d1bebfb38)

  NewsAPI와 CoorinatorLayout, RecyclerView를 통해 코로나 관련 뉴스 정렬
<br>

# Reference
[1] Android 개발자 / Android Developers - https://developer.android.com/docs?hl=ko

[2] Google Maps API - Google Cloud Console - https://developers.google.com/maps/documentation/

[3] NewsAPI - https://newsapi.org/docs

[4] Retrofit - https://square.github.io/retrofit/
