# Spring 대개 오류 원인

### 404 원인

* APP이 애초에 실행이 안되는 경우
  * web.xml 을 처음에 읽어들이면서 실행되어야 하는데 그 이후 beans.xml 이나 mybatis-config 등등 물리고 물려서 실행되다가 하나라도 안 되면 실행 안되면서 404 에러가 난다.
    * 보통 이 경우는 mapper 파일 경로/내부 보는게 좋다(거기서 오류났을 확률이 높다, 우리가 추가한 파일이니까)
  * mybatis-config에 package name이 올바르지 않다
  * mapping file 위치가 잘못되었거나 빼먹었다.
  * sql 오류가 났다 (BadSQLException)
  * mapper/XXX.xml 파일에 오류가 났다.
    * parameter**MAP** 으로 되어있다.
      * 안 쓰는건데 아직까지 남아있어서 오류가 안 난다.
    * resultType 형식이 잘못되었다.
    * mybatis-config.xml  에서는 typealias 를 하지 않았는데 클래스 이름으로만 썼다.

* @Controller 없다
* servlet-context 에 component-scan 에 경로 오류났다
* requestMapping  매칭 실패
* 포워딩되는  경로에 jsp file 이 없는 경우

### 500 원인

* 컴파일까지는 잘 되었으나 실행 중 던져지는 RuntimeException이 여기에 해당한다.

* NullpointException

* BadSQLGrammerException

* NumberFormantException 등등등.... 

* 서버 내부 로직에서 오류가 나서 예외를 던질 경우

* Controller 에서 Parameter로 int age 등으로 받았는데, FE에서 'aaa' 등으로 넣었고 그래서 이런 데에다가 Integer.parseInt('aaa') 하니까 오류가 나는 경우

  * 단 Controller에서 Parameter DTO로 읽으면, 클래스가 생성되면서 default 값이 들어가니까 이때는 오류가 안날수도 있당.

* ```html
  <c:forEach items="${todolist}" var="todo" >
  	<tr>
      	<td>${todo.writedate}</td> <!-- writeDate인데 writeDate라고 쓰는 경우-->
      </tr>
  </c:forEach>
  ```

  * PropertynotfoundException 인데, getWritedate <- 이런거 없어서 오류가 나는거당. DTO멤버함수에 이게 없다.

  * Todo DTO 에는 이렇게 되어있다.

  * ```java
    private String writeDate;
    public String getWriteDate() {
    		return writeDate;
    	}
    //그러니까 없다고 뜨는겨!
    ```

    

### 405 원인

* url mapping 하는 메소드가 get만 있는데 post로 보냈다.

* 혹은 반대의 경우 등등..

### 기타 오류 원인

* LoginForm.do -> Login.do -> .. .로 가야 하는데 중간 로직을 빼먹고 이상한 데로 보내는 경우 
  * 때에 따라서 NullPointerException이 나거나 애초에 오류가 난 채로 수행되어 가진다

* User 등 dto file에 기본 Constructor를 쓰지 않았을 경우
  * default constructor어쩌구저쩌구 하면서 오류가 난다. **단 버전에 따라서 오류가 안 날수도있다!**
* login.jsp 등 dto 와 연결되는 form안에 name parameter를 매칭시켜 주지 않아서 잘못 썼을 경우(예를 들어 User DTO 에는 userId인데 form에는 userid 등등인 경우)
  * 제대로 쳐도 로그인이 안 된다. 파라미터 이름이랑 잘 맞춰야 한다!
  * 또한 반드시 input요소는 name 속성을 가지고 있어야 한다.
* @Autowired를 하지 않은 경우
  * DI 가 되지 않았기 때문에 NullPointerException 이 떨어진다.
  * 다 준비 해 놓고 짝꿍 해달라고 이야기 하지 않은 것
* 스프링이 개~~~~길게 막 설명하면서(나는 이 빈을 찾기를 기대했는데 빈을 찾을수가없다 어쩌구저쩌구) 하는 경우(디펜던시 에러)
  * DI 주입은 해주려고 했는데 해당하는 클래스가 없다라고 얘기하는것

* @Autowired라고 까지도 했는데 그 해당 클래스(DAOImpl 등)에서 @Repository 를 하지 않은 경우
  * 첫 페이지부터 안 뜬다. 스프링 초기화 오류가 난다.(404) 스프링이 던지는 예외.
  * UnsatisfiedDependencyException :  ErrorCreating beans....어쩌구저쩌구 뜬다 NoSuchBeanDefinition 가능한 DAO Bean이 없다. 등의 오류가 난다.
* mapper/XXX.xml에서 column명을 없는 걸 줬을때
  * SQLSyntaxError가 난다.
* mapper/XXX.xml에서 콤마를 빼먹거나 SQL 구문 오류가 날 경우
  * SQLSyntaxErrorException. badSQLGrammerException . 이쯤 어디어디에서 오류가 났다 이런 식으로 나옴(**콤마 빼먹었다 이런거 안나옴!! 주의**)

### 400 원인

* bad Request
* 데이터가 넘어올 때 parameter에서 잘못된 데이터가 입력받아졌다
* 예를 들면 숫자를 입력해야되는곳에 abc등을 입력했다.

### 꿀팁

* 파일에  S자 안 뜨면 안 등록된거다(파일 모양)

* 무작정 돌려보지 말고 설정 파일 다 열어보고 돌리는데 다 기본적인것 확인해 보고 시작하기

* 오류가 날 만한거 주석처리 해 가면서 오류를 찾아내기

* 컨트롤러에 서비스 주입 되어있는지 안 되어있는지 생각하기(의외로 잘 까먹음)

  * 그리고 주입 2개 3개 될수도 있다는거 명심하기(1개만 아님)

* 멤버 변수 위에다가도 @Autowired할수도있는거 명심하기

  * 다만 이렇게 되어있으면 xml 에 주입할 때 p: 하면 안보인다. 그래서 setter에 다가 @Autowired를 하는것을 권장한다.

* mapper/XXX.xml 에 id이름이  DAOImpl에서 불러오는 이름이라는거 명심하십쇼

* DAOImpl 에는 final String NS = "com.ssafy....왈왈왈.XXXDAO"경로까지 써주고, sqlSession에서 부를 때 (NS+".{id}", {parameter}) 등으로 부르는거 잊지말자

* XXX.xml파일에서 (SQL 구문 쓸 때,) 콤마 꼭 써야 합니다! 엔터도 명심하고요

* select 가 되어서 console log 에 있으면 100프로 뿌리는 방식 문제임

  * 저장한 이름이랑 뿌리는 녀석 이름이 다르다
  * 가는 방식을 생각!(포워딩 으로 가야하는데 리다이렉션으로 가는 경우)

* DTO가 contoller method 의 parameter 안에 있을 때 Model 에 넣지 않아도 가진다고 한다.

  * 이 때 쓰려면 ${param.XXX} 식으로 쓴다루루 XXX <- dto 멤버변수

  * 이거 이용하면 로그인같은거 잘못했을대 아이디는 그대로있는거 이용할수있다

    ``` html
    <input type="text" name="userId" value="${param.userId}" />
    ```

    이런식으로 쓰면 그대로 남아있어서 사용자한테 편리함니다

* return "redirect:/login.jsp" 등으로 보냈는 경우, 말 그대로 리다이렉트 기 때문에 데이터를 가지고 가지 않는다는것 명심하자(세션 말구)

* 애노테이션 곧바로 붙이자! 설정 빼먹지 않게. 헷갈리니까..

### 에러 처리 방법

* ErrorAdvice 등의 클래스를 만들어서 거기다가 Exception 마다 핸들링하는 메소드 만들기

  * 별도로 .java 만들어서 클래스 이름 위에 **@Controlleradvice**  써주기

  * 안에 함수는 **@ExceptionHandler** 꼭 붙이기!

    ```java
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception){ //여기 매개변수에 오는 타입을 다른걸로 만들수있는거시당. 예를들면 NullPointerException.class이런거.
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", exception.getMessage());
        mav.setViewName("global_error");
        return mav;
    }
    ```

* 컨트롤러마다마다 위에 함수로 만들기!

* 만약에 외부 자바 클래스도 만들고, 내부에도 핸들링 메소드를 만들었다? 그런데 같은 오류가 났다?

  * 이럴 땐 내부 컨트롤러 안에있는 메소드로 실행된다.
  * 자바 정렬시킬때 수행 기준 내부클래스, 외부클래스로 만든거 그거랑 똑같다고 생각하면 될듯

  

