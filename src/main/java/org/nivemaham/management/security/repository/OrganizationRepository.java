package org.nivemaham.management.security.repository;

import org.nivemaham.management.security.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    public Organization findByName(String name);

}
