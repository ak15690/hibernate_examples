package com.blacbuck.dealbreaker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Table2Repository extends JpaRepository<Table2, Long> {

    List<Table2> findAllByName(String name);


}
