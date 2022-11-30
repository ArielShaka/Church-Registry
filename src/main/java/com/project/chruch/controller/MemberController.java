package com.project.chruch.controller;

import com.project.chruch.model.Member;
import com.project.chruch.model.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/members")
@CrossOrigin
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/add")
    ResponseEntity<?> addMember(@RequestBody Member newMember) {
        try {
            Member addingMember = memberService.addingMember(newMember);
            return ResponseEntity.accepted().body(addingMember);

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: This product was not found.");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Member> updateStatusTodo(@PathVariable Long id, @RequestBody Member newMember){
        Member updatedMember = memberService.updateAMember(id, newMember); ;
        return ResponseEntity.accepted().body(updatedMember);
    }

    @GetMapping
    ResponseEntity<?> listAllMembers () {
        try {
            List<Member> getAllMembers = memberService.getAllMembers();
            return ResponseEntity.ok(getAllMembers);

        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: No members");
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getMemberById (@PathVariable Long id){
        try {
            Member member = memberService.getMemberById(id);
            return ResponseEntity.ok(member);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: No member");
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMemberById(@PathVariable Long id) {
        try {
            memberService.deleteMemberById(id);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: This product was not found.");
        }
    }








}
