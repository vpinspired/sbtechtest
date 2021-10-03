# sbtechtest (also at: sbtechtest/familytree/README.txt)

# familytree
1              2
|______________|
        |
 _______|_____
|            |                       3             4
5            6             7         |_____________|
             |_____________|               |
                   |                       8                  9
       ____________|________               |________________|
       |         |          |                   |
       10        11         12                 13
                            |__________________|
                                     |
                                     14                 15
                                     |__________________|
                                             |
                                             16

The Diagram above represents the test data

A Family member has the following attributes:
* Name - This is for user readability only

* Gender - This is used to check a Male and Female are added as parents

* Parent Family ID - Used to identify sibling and related members
    It is a string in the format of <Mother ID>-<FatherID>
    Where there is no Parent data the String is empty
    
* Family ID -This is a used to identify family units
    It is a string in the format of <ID>-<PartnerID>
    Where there is no couple the String id <ID>

Example:  Member 9  [id : 9, familyID : "8-9", parentFamilyID : ""]
          Member 9  [id : 11, familyID : "11", parentFamilyID : "5-7"]
          Member 12 [id : 12, familyID : "12-13", parentFamilyID : "6-7"]


Run Program via IDE (Intelij)
File > New from Sources > Locate folder > open
Or
File > Open >  Locate project

Uses Spring Boot, Tomcat is embedded
Click Play (run) on MainClass

Open Browser (Chrome)

Get all Family Tree >  http://localhost:8080/FamilyTree/
Given member > http://localhost:8080/FamilyTree/6
Given members children > http://localhost:8080/FamilyTree/6/children
Given members descendants > http://localhost:8080/FamilyTree/6/descendants
Given members ancestors > http://localhost:8080/FamilyTree/6/ancestors



TODO:
 Logging
 UpDate
 Delete
