I don't like people reserving all the Tennis courts, so I automated reserving all the Tennis courts 
==========================


 Screenshots
 ====================
 
  ## Main Dashboard
 ![Screen Shot 2020-08-25 at 2 02 09 PM](https://user-images.githubusercontent.com/35381714/91214285-dba04d00-e6e0-11ea-8e5c-d91db82113dd.png)
 
  ## Reservation List By Date
 ![Screen Shot 2020-08-25 at 2 02 58 PM](https://user-images.githubusercontent.com/35381714/91214302-dfcc6a80-e6e0-11ea-9dcc-1b9ca4f9e921.png)
 
 
 Technologies used
 =============
 * Java Spring + Maven
 * Selenium Driver
 * Java Mail
 * ReactJS
 * React Bootstrap
 * OpenWeatherMap API
 * PostgreSQL
 * Heroku (project is currently connected to a remote database)
 

## This project follows an organized development pipeline involving 2 main branches


| Branch        | Merge to      | Purpose                     |
| ------------- | ------------- |---------------------------- |
| **Master**    | *N/A*         | production ready            |
| **DEV**       | Master        | all current working features|
| ATC-x         | DEV           | new features                |
| ATCB-x        | ATC-x branch  | bug fixes                   |
| ATD-x         | *N/A*         | documentation               |
| DEP-x         | *N/A*         | deployment related          |


# Master

* Only fully tested code ready for deployment is on the master branch.

# DEV

* Once a feature is completed and working, it is merged into the **DEV** branch.

# ATC branches 

* These branches are created according to codes of *issues* in this repository
 once a task is complete, the **ATC** branch is merged into the **DEV** branch.
 
 # ATCB branches 

* These branches are created according to codes of *issues* in this repository
 once a bug is fixed, the **ATCB** branch is merged into its corresponding **ATC** branch.
 
 # ATD branches 

* These branches are created according to codes of *issues* in this repository. They are related to any form of documentation.
 
 # DEP branches 

* These branches are created according to codes of *issues* in this repository.
 They are related to any form of deployment
 
 
 
 
 

 
