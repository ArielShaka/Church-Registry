package com.project.chruch.repo;

import com.project.chruch.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemberRepo extends JpaRepository<Member, Long> {
    Member findMemberById(Long longId);
}
