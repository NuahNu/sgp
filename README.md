# 게임 컨셉: High Concept 및 핵심 메카닉
모바일게임 Event Horizon Space Shooting과 PC게임 FTL: Faster Than Light를 적절히 섞을 게임.\
플레이어는 전투 시작 전 무기를 개조할 수 있다.\
전투를 시작하면 플레이어는 자신의 비행기를 조종해 적을 격파한다.\
https://play.google.com/store/apps/details?id=com.ZipasGames.Frontier&pli=1
https://store.steampowered.com/app/212680/FTL_Faster_Than_Light/

# 개발 범위
3개 이상의 비행기\
3개 이상의 무기종류(탄, 미사일, 레이저 등)\
2가지 이상의 [AI패턴](#ai패턴)([배회](#배회), [간격 벌리기](#간격-벌리기), [요격](#요격) 등)

# 예상 게임 실행 흐름
1. 어플 실행시 Touch to Play
2. 사용할 무기를 선택 및 개조\
![FTL_무기UI](https://user-images.githubusercontent.com/104414203/229501925-e582c354-d8e0-44d4-9bb2-cc35be1052cc.png)
3. 적과 전투\
![image](https://user-images.githubusercontent.com/104414203/229502390-a05f712f-49d4-4bc9-ac37-fc98bd7a44e1.png)
4. 2, 3을 반복.
 
# 개발 일정
1주 : 임시 리소스 수집.\
2주 : 기본 게임 오브젝트 class 작성\
3주 : 프레임워크 중 전투 부분 부터 작성시작\
4주 : 하위 오브젝트(무기) 구현 및 테스트\
5주 : 하위 오브젝트(비행기) 구현 및 테스트\
6주 : 적 AI 구현\
7주 : 리소스 수정 및 UI 수정\
8주 : 무기 선택 등 메뉴 구현\
9주 : 게임답도록 수정.

# 발표 동영상링크




# AI패턴
## 배회
유저와 상관없이 위치함.
## 간격 벌리기
일정 거리를 유지하며 공격.
## 요격
직선으로 접근해 공격하고 도망을 반복.