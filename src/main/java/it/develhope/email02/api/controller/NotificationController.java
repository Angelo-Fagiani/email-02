package it.develhope.email02.api.controller;

import it.develhope.email02.api.controller.entities.NotificationDTO;
import it.develhope.email02.email.EmailService;
import it.develhope.email02.student.entities.Student;
import it.develhope.email02.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Controller
public class NotificationController {

    @Autowired
    StudentService studentService;

    @Autowired
    EmailService emailService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationDTO payload){
        try {
            Student studentToSendNotification = studentService.getStudentById(payload.getContactId());
            System.out.println("Getting the student: " +  studentToSendNotification);
            if (studentToSendNotification == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Did not find the student");
            emailService.sendTo(studentToSendNotification.getEmail(), payload.getTitle(), payload.getText());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            System.out.println("Error in notification controller " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).body(e.getMessage());
        }

    }
}
