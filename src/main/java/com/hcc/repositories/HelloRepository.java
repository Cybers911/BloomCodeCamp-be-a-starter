package com.hcc.repositories;

import com.hcc.entities.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

// this is an example repository feel free to delete this once you have created your own.

public interface HelloRepository extends JpaRepository<Hello, Long> {

    Hello findByMessage(String message); // example method to find hello by message

}
