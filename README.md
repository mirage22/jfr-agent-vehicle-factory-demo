# jfr-agent-vehicle-factory-demo

Following demo has be created for demonstration purposes for 
presentation : JFR - Under the hood. It has been used to present 
upcoming JMC Agent Plugin in Java Mission Control/Java Flight Recorder 
release 8.1

### requirement: 
- JDK 11
- Current build of JMC/JFR and JMC Agent: https://github.com/openjdk/jmc
- JMC build script "**build.sh**" located in the root of the JMC project
link: https://mirage22.github.io/blog/2021/how-to-build-easily-jmc-jfr/
  
### scenario: 
We build simple vehicle factories. Those factories go through the following 
states: PREPARE parts, PRODUCING vehicles and DELIVERING vehicle to the customers.
This scenarios is simple. 

### how to run
```bash 
$ mvn clean install
$ java -jar ./target/jfr-agent-vehicle-factory-demo-1.0-SNAPSHOT.jar
```

It's required to attach the agent, which can be found in agent directory. 

Enjoy JMC Agent Demo!
