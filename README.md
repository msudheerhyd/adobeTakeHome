# adobeTakeHome

# Title

This is a containerized CRUD application for the resource fiction
Deployed on AWS EC2 

root url = http://3.145.168.228/

## Entity 
	 Fiction 
	  -title
	  -author

## Roles 
	-ROLE_USER
	-ROLE_ADMIN

## APIs

	 Method     	 	ReturnType        	AUTHORITY	URL
	POST /add    		String 			ALL		http://3.145.168.228/users/add
	POST /authenticate	token 			ALL 		http://3.145.168.228/users/authenticate

	 Method     	 ReturnType        	AUTHORITY	URL					RateLimited
	GET /all    	List of Fictions	  ALL		http://3.145.168.228/fictions/all	Yes
	GET /{id} 	Fiction			ROLE_USER	http://3.145.168.228/fictions/1		No
	POST /	  	Fiction			ROLE_USER	http://3.145.168.228/fictions		Yes
	PUT /{id}  	Fiction			ROLE_USER	http://3.145.168.228/fictions/1		No
	DELETE /{id} 	String			ROLE_ADMIN	http://3.145.168.228/fictions/1		No
	
Need to create a user with role as ROLE_USER or ROLE_ADMIN first

Authenticate the user with username and password and get the token

Attach the token as the bearer in auth to access the authorized above requests

RateLimiting is 5 requests per 30 seconds

Attached the postman collection consisting of the requests
