package de.frontierpsychiatrist.example.oauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.frontierpsychiatrist.example.oauth.domain.Credentials;

/**
 * @author Moritz Schulze
 */
@Repository("credentialsRepository")
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Credentials findByName(String name);

}
