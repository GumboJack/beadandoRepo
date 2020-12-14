# beadandoRepo
webshop app

funkciók:
-felhasználó regisztráció / bejelentkezés
-autentikálás aktivitás esetén (minden oldal betöltésekor) JSESSIONID süti alapján, paraméterezett idejű inaktivitást követően kiléptetés
-termékek listázása, szűrőlehetőségek listázása, termékek listázása szűrési feltételekkel
-kosárhoz adás / kosár megtekintése / elem törlése a kosárból
-user menu - a navbaron elhelyezett felhasználó indikátorra kattintva a felhasználó korábbi vásárlásai kilistázhatóak

Mivel egyes ajax hívások JSONArray formájában küldenek request paramétereket, ezért futtatás előtt szükséges a tomcat konfigurációs file kibővítése (%TOMCAT_HOME%/conf/server.xml):
Connector port="8081" protocol="HTTP/1.1" URIEncoding="UTF-8" relaxedQueryChars='[]'
               connectionTimeout="20000"
               redirectPort="8443" />
               
Az adatok kiolvasása JSON fileokból történik, amelyeket alapértelmezetten a "%TOMCAT_HOME%/bin/" mappában hoz létre a webshop app
