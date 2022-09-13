package edu.sio.messagerie;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.sio.messagerie.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {

}
