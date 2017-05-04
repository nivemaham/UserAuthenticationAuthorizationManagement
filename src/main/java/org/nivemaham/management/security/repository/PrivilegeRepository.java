package org.nivemaham.management.security.repository;

import org.nivemaham.management.security.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    public Privilege findByName(String name);

}
