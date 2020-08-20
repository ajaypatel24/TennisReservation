I don't like people reserving all the Tennis courts, so I automated reserving all the Tennis courts 
==========================

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
