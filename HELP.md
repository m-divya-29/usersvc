Run Mysql in container   
docker run -d           --name mysql-container            -e MYSQL_ROOT_PASSWORD=root123       -e MYSQL_DATABASE=myd -p 3306:3306 -v mysql-data:/var/lib/mysql mysql:8.0


create database usersvc;
CREATE USER 'usersvc'@'%' IDENTIFIED BY 'password123';

GRANT ALL PRIVILEGES ON usersvc.* TO 'usersvc'@'%';

FLUSH PRIVILEGES;


CREATE USER 'usersvc'@'%' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON mysqllocal.* TO 'usersvc'@'%';
FLUSH PRIVILEGES;


----------
1. Signup
   POST http://localhost:8080/users/signup
   {
   "name" : "Divya",
   "email" : "Dm@gmail.com",
   "password" : "password"
   }
2. Login
   POST http://localhost:8080/users/login
   {
   "email" : "Dm@gmail.com",
   "password" : "password"
   }
3. Logout
   POST http://localhost:8080/users/logout
   {
   "token": "i7P5jW13ADufGDqA4LKwF1syTYJBHTnPUyhDN19AfuAiP2b0q1I3eVwPMGICOOgu9FexXdpeH3lAIYX1Nkbhqmo70cqTlkOk2EbtZKuhhX1XukDQCcpkYMopUgRfUQds"
   }