package uz.pdp.task1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {


    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>("Hello from Check",HttpStatus.OK);
    }
}
