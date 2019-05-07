# Java Web Consultant
## Pre-requisites
1. Java
  
 Have or install java 1.8>=
 You can check if you have it install with the command 
 
    `java -version`
   
2. 
 
 Download or clone the project
 
## How does it work? How can i use this project?

  On the root directory you will find a file named "urls.txt"
  if you open this file you will notice there are 30 urls, some being API's and others are just urls
  if you want to know if a site is up and running, just put the url, and if you are testing any API follow this structure
  
    `url;GET;property:value`
    `www.example.com;GET;Content-type:application/json`

  save the file and build the project, depending on your IDE this may vary, if you have IntelliJ you can open the project
  and since im including a folder made by this IDE you shouldn't have problems building it.
  
 
 So once you finished adding the urls you want to consume, run the project, and you will have to wait a little, depending on how many urls you added to the "urls.txt" and how good or bad your connection and the server are.
 
 After it finishes, you will see the url consulted plus the result of making a request to the given url or API.
