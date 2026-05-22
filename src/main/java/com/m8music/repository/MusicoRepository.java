package com.m8music.repository;

import com.m8music.model.Musico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicoRepository extends JpaRepository<Musico, Long> {
}
