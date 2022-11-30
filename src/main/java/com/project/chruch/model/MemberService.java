package com.project.chruch.model;

import com.project.chruch.repo.JpaMemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    JpaMemberRepo repo;

    public Member addingMember (Member newMember) {
        return repo.save(newMember);
    }

    public List<Member> getAllMembers () {
        return repo.findAll();
    }

    public Member getMemberById (Long id) {
        if (repo.findMemberById(id) == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        return repo.findMemberById(id);
    }



    public Member updateAMember (Long id, Member updatedMember) {
        Member oldMember = repo.findMemberById(id);
        if(oldMember == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        updatedMember.setId(oldMember.getId());
        updatedMember.setName(oldMember.getName());
        updatedMember.setLastName(oldMember.getLastName());

        return repo.save(updatedMember);
    }

    public void deleteMemberById(Long id) {
        if ((getMemberById(id)) == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        repo.deleteById(id);
    }




}
