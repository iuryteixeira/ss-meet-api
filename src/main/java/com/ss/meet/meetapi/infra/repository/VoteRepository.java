package com.ss.meet.meetapi.infra.repository;

import java.util.List;

import com.ss.meet.meetapi.domain.vote.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByUserId(String userId);

    List<Vote> findBySessionId(Long id);
}
