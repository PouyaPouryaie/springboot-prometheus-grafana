# springboot-microservices
Sample Project show how use micrometer in springBoot and use it into prometheus

---
## Steps:
1) run services on docker <br> 
   - prometheus: <br>
     notice: set Path of prometheus.yml in docker
        - ```docker run -d -p 9090:9090 --name prometheus -v [yourAddress]/prometheus.yml:/etc/prometheus/prometheus.yml bitnami/prometheus``` 
   - grafana:
        - ```docker run -d --name grafana -p 3000:3000 grafana/grafana```
    
2) set this dependency:
   ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
   <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <scope>runtime</scope>
    </dependency>
   ```
3) config application.properties <br>
    enable some endpoint in actuator: ```management.endpoints.web.exposure.include=health,info,metrics,prometheus```
   
4) run App
5) first check app added into prometheus:
    - ``http://localhost:9090`` 
    - go into targets in status tap and see your app endpoint
6) go to grafana and follow this command:
    - ``http://localhost:3000``
    - go configuration, in dataSource tab, add datasource and select prometheus
    - set prometheus host url and save
    - go to create, add a new panel
    - select prometheus in datasource
    - add name of app Metric in grafana: ``rate(sample_visit_counter_total[10m])``