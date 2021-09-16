## WS, WAS

- webServer: 정적인 결과(웹페이지 파일)를 리턴해주는 서버
    - apache
    - nginx
        - 멀티프로세스 싱글쓰레드(이벤트 루프 방식으로 IO 처리)
        - 동적인 처리가 필요 없을 때는 static 페이지 파일을 nginx에 두고 바로바로 처리할 수 있게 되는거지

- webApplicationServer: 동적인 처리/결과를 리턴해주는 서버(정적인 결과를 리턴해줄 수 있음)
    - [tomcat | https://tomcat.apache.org/tomcat-8.0-doc/config/executor.html]
        - servlet: 동적 처리를 담당하는 역할, 인터페이스(구현체로는 스프링 MVC)
        - tomcat
            - servlet의 컨테이너(생명주기 관여) 역할을 함
            - 요청이 왔을 때 쓰레드 할당(+처리할 서블릿에 요청 전달) 및 쓰레드풀 관리
            - 스프링 MVC에는 DispatcherServlet이 있고, 모든 요청 처리하는 서블릿임
                - 나머지 빈은 순수 자바로 구현할 수 있게 되는거지 : 물론 Spring 관련이 코드에 묻을 수 있지만