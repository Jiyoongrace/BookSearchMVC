# BookSearchMVC
## 1. Layered Architecture

- Layered Architecture는 소프트웨어 개발에서 일반적으로 널리 사용되는 아키텍처를 지칭하는 용어.
- 프로그램을 개발할 때 몇 개의 계층(Layer)를 이용할 지에 따라서 그 형태가 달라지는 데 일반적으로 많이 사용하는 것은 4계층이다. (4-Tier Architecture)
- 객체지향적 개념으로 보자면 관심사의 분리(Seperation of Concern)와 동일한 개념이라고 생각하면 된다.
- Layered Architecture는 소프트웨어 시스템을 여러 개의 논리적인 계층으로 나누어 구조화하는 방법론.
- 각 계층은 특정한 역할을 수행하며, 시스템을 유연하고 유지보수가 용이하도록 만든다.
- 각 계층은 서로 분리되어 있어, 한 계층의 변경이 다른 계층에 미치는 영향을 최소화한다.
- 이렇게 각 계층은 자신의 역할에 집중하며, 시스템을 구조화하여 유지보수와 확장을 용이하게 한다.
- 변경이 필요한 경우 한 계층만 수정하면 되므로 시스템의 유연성이 향상된다.

<img src="https://github.com/Jiyoongrace/BookSearchMVC/assets/88182667/0c6f9d75-570a-4eac-8a27-c49ea729181f.png" width="420" height="280"/>

- Presentation Layer : 사용자 인터페이스(UI), HTML, CSS, JavaScript
    - 화면을 만들어주는 Class ⇒ View 라고 한다.
- Business Layer : 비즈니스 로직을 처리하고, 시스템의 핵심 기능을 구현, 주문을 처리하거나 계산을 수행하는 등의 작업 (데이터를 이용해서 로직을 수행, 단, DB 처리 제외)
    - Service(실제 로직을 담당하는 객체), (Domain) Model이라고 하는 데이터 객체가 포함된다. (VO)
- Persistence Layer : 데이터의 영구적인 저장과 관리 담당, 데이터베이스와의 상호작용을 처리하고, 데이터를 저장하거나 불러오는 등의 작업, CRUD
    - 데이터베이스를 핸들링하는 객체(DAO - Data Access Object)
- Database Layer : 실제 데이터를 저장하고 관리, 데이터베이스 관리 시스템(DBMS)
    - MySQL, Oracle

<br>

## 2. MVC Pattern

- MVC는 소프트웨어 시스템을 3가지 타입의 component로 분할해서 개발하는 소프트웨어 개발 패턴.
- Model(M) - Service (Model), Domain Model(VO)
- View(V) - 사용자 interface 담당
- Controller(C) - 시스템 흐름제어(View와 Service 간의 Bridge)

<br>

## 3. 도서 검색 프로그램을 MVC 형태로 구현

- View : Class 하나 이용해서 화면 처리를 해야 한다. FXML로 처리한다.
