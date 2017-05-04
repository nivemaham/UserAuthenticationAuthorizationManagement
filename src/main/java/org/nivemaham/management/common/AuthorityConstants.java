package org.nivemaham.management.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class AuthorityConstants {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String READ_DATA        = "ROLE_READ_DATA";
    public static final String MANAGE_STUDY     = "ROLE_MANAGE_STUDY";
    public static final String SEND_DATA        = "SEND_DATA";

    public static final Set<String> ORGANISATION_AUTHORITIES = new HashSet<>(Arrays.asList(
        MANAGE_STUDY
    ));

    public static boolean isOrganisationAuthority(String name) {
        return ORGANISATION_AUTHORITIES.contains(name);
    }

}
