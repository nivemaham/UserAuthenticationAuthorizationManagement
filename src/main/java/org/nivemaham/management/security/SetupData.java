//package org.radarcns.management.security;
//
//import java.util.Arrays;
//import java.util.HashSet;
//
//import javax.annotation.PostConstruct;
//
//import org.radarcns.management.security.domain.Organization;
//import org.radarcns.management.security.domain.Privilege;
//import org.radarcns.management.security.domain.User;
//import org.radarcns.management.security.repository.OrganizationRepository;
//import org.radarcns.management.security.repository.PrivilegeRepository;
//import org.radarcns.management.security.repository.UserRepository;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//@Component
////@ComponentScan
//public class SetupData {
//
//    private final org.slf4j.Logger log = LoggerFactory.getLogger(SetupData.class);
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
//    @Autowired
//    private OrganizationRepository organizationRepository;
//
//    @PostConstruct
//    public void init() {
//        initOrganizations();
//        initPrivileges();
//        initUsers();
//    }
//
//    private void initUsers() {
//        log.info("Init USERS------------------");
//
//        final Privilege privilege1 = privilegeRepository.findByName("FOO_READ_PRIVILEGE");
//        final Privilege privilege2 = privilegeRepository.findByName("FOO_WRITE_PRIVILEGE");
//        //
//        final User user1 = new User();
//        user1.setUsername("john");
//        user1.setPassword("123");
//        user1.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1)));
//        user1.setOrganization(organizationRepository.findByName("FirstOrg"));
//        userRepository.save(user1);
//        //
//        final User user2 = new User();
//        user2.setUsername("tom");
//        user2.setPassword("111");
//        user2.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1, privilege2)));
//        user2.setOrganization(organizationRepository.findByName("SecondOrg"));
//        userRepository.save(user2);
//    }
//
//    private void initOrganizations() {
//        final Organization org1 = new Organization("FirstOrg");
//        organizationRepository.save(org1);
//        //
//        final Organization org2 = new Organization("SecondOrg");
//        organizationRepository.save(org2);
//    }
//
//    private void initPrivileges() {
//        final Privilege privilege1 = new Privilege("FOO_READ_PRIVILEGE");
//        privilegeRepository.save(privilege1);
//        //
//        final Privilege privilege2 = new Privilege("FOO_WRITE_PRIVILEGE");
//        privilegeRepository.save(privilege2);
//    }
//}
