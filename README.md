# beadandoRepo
webshop app

funkciók:<br>
-felhasználó regisztráció / bejelentkezés<br>
-autentikálás aktivitás esetén (minden oldal betöltésekor) JSESSIONID süti alapján, paraméterezett idejű inaktivitást követően kiléptetés<br>
-termékek listázása, szűrőlehetőségek listázása, termékek listázása szűrési feltételekkel<br>
-kosárhoz adás / kosár megtekintése / elem törlése a kosárból<br>
-user menu - a navbaron elhelyezett felhasználó indikátorra kattintva a felhasználó korábbi vásárlásai kilistázhatóak<br>

Mivel egyes ajax hívások JSONArray formájában küldenek request paramétereket, ezért futtatás előtt szükséges a tomcat konfigurációs file kibővítése (%TOMCAT_HOME%/conf/server.xml):<br>
Connector port="8081" protocol="HTTP/1.1" URIEncoding="UTF-8" relaxedQueryChars='[]'<br>
               connectionTimeout="20000"<br>
               redirectPort="8443" /><br>
               
Az adatok kiolvasása JSON fileokból történik, amelyeket alapértelmezetten a "%TOMCAT_HOME%/bin/" mappában hoz létre a webshop app<br>
