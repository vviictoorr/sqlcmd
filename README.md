SQLCMD
======

Project Name: "SQLCMD" - the educational project "Database command line client".
Accomplishments: Program can connect to database via command line and execute sql queries.
All history queries are saved for reusing without typing again. Ant build is done.

How to use:

1)Ant Build

SQLCMD
------build
-----------jar
--------------SqlCmd.jar

2)CMD

java -cp E:\Java\IdeaProject\sqlcmd\target\classes;lib/* ua.com.juja.study.sqlcmd.SqlCmd
-u juja_core -p juja -d org.postgresql.Driver -url jdbc:postgresql://localhost:5432/juja

select * from participations;