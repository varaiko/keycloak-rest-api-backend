package com.keycloak.keycloak_integration.repository;

import com.keycloak.keycloak_integration.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {

}
