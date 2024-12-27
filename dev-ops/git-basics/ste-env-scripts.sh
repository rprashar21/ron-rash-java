move from downloads to my current db Server
scp ~/Downloads/referencedataoffences-liquibase-7.0.23-PSS-SNAPSHOT.jar STECCM04DCTDB01.cpp.nonlive:~
scp ~/Downloads/referencedataoffences-service-7.0.23-PSS-SNAPSHOT.war STECCM04ACTAP01.cpp.nonlive:~
scp ~/Downloads/referencedata-service-7.0.97-REFDATA2-20211118.120010-2.war STECCM04ACTAP01.cpp.nonlive:~
sudo mv ~/referencedataoffences-service-7.0.23-PSS-SNAPSHOT.war .
sudo mv ~/referencedata-service-7.0.97-REFDATA2-20211118.120010-2.war .
run liquibase on my db Server
java -jar ~/referencedataoffences-liquibase-7.0.23-PSS-SNAPSHOT.jar --driver=org.postgresql.Driver --url=“jdbc:postgresql://localhost:5432/referencedataoffences” --username=referencedataoffences --password=referencedataoffences update
sudo mv ~/referencedataoffences-liquibase-7.0.23-PSS-SNAPSHOT.jar /tmp
ssh SITCCM01DCTDB02.cpp.nonlive -L 5436:localhost:5432
ssh -L 8080:localhost:8080 STECCM04ACTAP01.cpp.nonlive
ssh -L 8080:localhost:8080 STECCM17ACTAP01.cpp.nonlive
ssh -L 5430:localhost:5432 STECCM14DCTDB01.cpp.nonlive
ssh -L 8080:localhost:8080 STECCM14ACTAP01.cpp.nonlive

sudo mv referencedataoffences-service-7.0.24-PSS-SNAPSHOT.war
scp ~/Downloads/referencedataoffences-liquibase-7.0.23-PSS-SNAPSHOT.jar STECCM04DCTDB01.cpp.nonlive:~
scp STECCM17ACTAP01.cpp.nonlive:/opt/wildfly/standalone/configuration/standalone.xml .
cd /opt/wildfly/standalone/log
java -jar ~/referencedataoffences-liquibase-7.0.23-PSS-SNAPSHOT.jar --driver=org.postgresql.Driver --url=“jdbc:postgresql://localhost:5432/referencedataoffences” --username=referencedataoffences --password=referencedataoffences update
