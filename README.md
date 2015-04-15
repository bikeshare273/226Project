

Starting point of Application(UI)
localhost:8080/movieapp/index.html

=====================================

To Run Application

1)Import git project into spring sts. Follow below section from tutorial
http://eclipsesource.com/blogs/tutorials/egit-tutorial/
-installing egit in eclipse
-egit configuration
-cloning repositories

2)Rightclick pom.xml and then select maven clean and maven install from run as

3)Project-> Clean

4)Right click com.movieproject.movieapp.Application, Run as java application or springboot application

==================================

Information for ui-

1)Angularjs file path
\226Project\src\main\webapp\movieapp\js\movie_controller.js

2)Application css path
\226Project\src\main\webapp\movieapp\css\movieapp.css

3)Add new html file to below folder level
\226Project\src\main\webapp\movieapp\

4)Add Images to below folder
\226Project\src\main\webapp\movieapp\img\

5)In case, to add new js/css component
	5.1)add it to bower.json
	example-
	"fontawesome": "~4.1.0"
	"angular-resource": "1.4.0-build.3962+sha.fe9cd9d"
	
	5.2)run bower install
	
	5.3)Above command will automatically download repository(js) to below path
	\226Project\src\main\webapp\bower_components\
	
	5.4)then include js file into index.html
	\226Project\src\main\webapp\movieapp\index.html
	example-
	<link rel="stylesheet" href="/movieapp/bower_components/fontawesome/css/font-awesome.css">
	<script type="text/javascript" src="/movieapp/bower_components/angular-route/angular-route.js"></script>



