package org.nivemaham.management.common;

import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

public interface AuthenticatedUser extends Principal, IdentifiableUser {

    UUID getUuid();

    String getName();

    String getPassword();

    Collection<String> getAuthorityNames();

//    Map<UUID, Collection<String>> getOrganisationAuthorities();

}
