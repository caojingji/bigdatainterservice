package com.founder.interservice.repository;

import com.founder.interservice.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track,String> {
}
