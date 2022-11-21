package com.sha.microserviceusermanagement.controller;

import com.sha.microserviceusermanagement.model.Role;
import com.sha.microserviceusermanagement.model.User;
import com.sha.microserviceusermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment environment;

    @Value("${spring.application.name}")
    private String serviceId;

    @GetMapping("/service/port")
    public String getPort(){
        return "Service port number: " + this.environment.getProperty("local.server.port");
    }

    @GetMapping("/service/services")
    public ResponseEntity<?> getService(){
        return new ResponseEntity<>(this.discoveryClient.getServices(), HttpStatus.OK);
    }

    @GetMapping("/service/instances")
    public ResponseEntity<?> getInstances(){
        return new ResponseEntity<>(discoveryClient.getInstances(serviceId), HttpStatus.OK);
    }

    public ResponseEntity<?> saveUser(@RequestBody User user){
        if(this.userService.findByUsername(user.getUsername()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.save(user),HttpStatus.CREATED);
    }

    @GetMapping("/service/login")
    public ResponseEntity<?> getUser(Principal principal){
        //Principal principal1 = request.getPrinciple(); --istekten işlem yapan ilgili kullanıcıyı aldık
        if(principal == null || principal.getName() == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.ok(this.userService.findByUsername(principal.getName()));
    }

    @PostMapping("/service/names")
    public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList){
        return ResponseEntity.ok(this.userService.findUsers(idList));
    }

    @GetMapping("/service/test")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Bu bir testtir. This is a test.!");
    }
}
