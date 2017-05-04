//package org.radarcns.management.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import java.io.Serializable;
//import java.time.ZonedDateTime;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Set;
//import java.util.UUID;
//import java.util.stream.Collectors;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.PrePersist;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import org.hibernate.annotations.BatchSize;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Parameter;
//import org.hibernate.validator.constraints.Email;
//import org.radarcns.management.common.AuthenticatedUser;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
///**
// * A user.
// */
//@Entity
//@Table(name = "radar_user")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//public class User extends AbstractAuditingEntity implements AuthenticatedUser, UserDetails,
//    Serializable {
//
//    private static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
//    @GenericGenerator(
//        name = "user_seq_gen",
//        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//        parameters = {
//            @Parameter(name = "sequence_name", value = "user_seq"),
//            @Parameter(name = "initial_value", value = "1000"),
//            @Parameter(name = "increment_size", value = "50")
//        }
//    )
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private UUID uuid;
//
//    @NotNull
//    @Pattern(regexp = LOGIN_REGEX)
//    @Size(min = 1, max = 50)
//    @Column(length = 50, unique = true, nullable = false)
//    private String login;
//
//    @JsonIgnore
//    @NotNull
//    @Size(min = 60, max = 60)
//    @Column(name = "password_hash",length = 60)
//    private String password;
//
//    @Size(max = 50)
//    @Column(name = "first_name", length = 50)
//    private String firstName;
//
//    @Size(max = 50)
//    @Column(name = "last_name", length = 50)
//    private String lastName;
//
//    @Email
//    @NotNull
//    @Size(min=3, max = 100)
//    @Column(length = 100, unique = true)
//    private String email;
//
//    @Column(name="telephone")
//    private String telephone;
//
//    @Column(name="institute")
//    private String institute;
//
//    @Column(name="department")
//    private String department;
//
//    @Column(name="job_title")
//    private String jobTitle;
//
//    @Column(name="specialism")
//    private String specialism;
//
//    @Size(min = 2, max = 5)
//    @Column(name = "lang_key", length = 5)
//    private String langKey;
//
//    @Size(max = 20)
//    @Column(name = "activation_key", length = 20)
//    @JsonIgnore
//    private String activationKey;
//
//    @Column(name = "activation_key_date", nullable = true)
//    private ZonedDateTime activationKeyDate = null;
//
//    @Size(max = 20)
//    @Column(name = "reset_key", length = 20)
//    private String resetKey;
//
//    @Column(name = "reset_date", nullable = true)
//    private ZonedDateTime resetDate = null;
//
//    @Column(name="deleted")
//    private boolean deleted = false;
//
//    @Column(name="email_verified")
//    private boolean emailVerified = false;
//
//    @Column(name="admin_verified")
//    private boolean adminVerified = false;
//
//    @Column(name="failed_login_attempts")
//    private int failedLoginAttempts = 0;
//
//    @Column(name="account_locked")
//    private boolean accountLocked = false;
//
//    @Column(name = "account_lock_date", nullable = true)
//    private ZonedDateTime accountLockDate = null;
//
//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//        name = "role_users",
//        joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
//        inverseJoinColumns = {@JoinColumn(name = "roles_id", referencedColumnName = "id")})
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    @BatchSize(size = 20)
//    private Set<Role> roles = new HashSet<>();
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public UUID getUuid() {
//        return uuid;
//    }
//
//    public UUID getUserUuid() { return getUuid(); }
//
//    /**
//     * UUID is generated by the DBMS and cannot be overridden.
//     * @param uuid This will be passed by syntax is satisfies for Hibernate and used mappers.
//     */
//    public void setUuid(UUID uuid) {
//        // pass
//    }
//
//    @PrePersist
//    public void generateUuid() {
//        if (this.uuid == null) {
//            this.uuid = UUID.randomUUID();
//        }
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    // Lowercase the login before saving it in database
//    public void setLogin(String login) {
//        this.login = login.toLowerCase(Locale.ENGLISH);
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return getLogin();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !isAccountLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEmailVerified() && isAdminVerified();
//    }
//
//    @Override
//    public Collection<String> getAuthorityNames() {
//        return getAuthorities().stream()
//            .map(GrantedAuthority::getAuthority)
//            .collect(Collectors.toSet());
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        // Lowercase e-mail address before saving
//        this.email = email.toLowerCase(Locale.ENGLISH);
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public String getInstitute() {
//        return institute;
//    }
//
//    public void setInstitute(String institute) {
//        this.institute = institute;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//
//    public String getJobTitle() {
//        return jobTitle;
//    }
//
//    public void setJobTitle(String jobTitle) {
//        this.jobTitle = jobTitle;
//    }
//
//    public String getSpecialism() {
//        return specialism;
//    }
//
//    public void setSpecialism(String specialism) {
//        this.specialism = specialism;
//    }
//
//    public boolean isActivated() {
//        return emailVerified && adminVerified;
//    }
//
//    public String getActivationKey() {
//        return activationKey;
//    }
//
//    public void setActivationKey(String activationKey) {
//        this.activationKey = activationKey;
//    }
//
//    public String getResetKey() {
//        return resetKey;
//    }
//
//    public void setResetKey(String resetKey) {
//        this.resetKey = resetKey;
//    }
//
//    public ZonedDateTime getResetDate() {
//       return resetDate;
//    }
//
//    public void setResetDate(ZonedDateTime resetDate) {
//       this.resetDate = resetDate;
//    }
//
//    public String getLangKey() {
//        return langKey;
//    }
//
//    public void setLangKey(String langKey) {
//        this.langKey = langKey;
//    }
//
//    public ZonedDateTime getActivationKeyDate() {
//        return activationKeyDate;
//    }
//
//    public void setActivationKeyDate(ZonedDateTime activationKeyDate) {
//        this.activationKeyDate = activationKeyDate;
//    }
//
//    public boolean isDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        this.deleted = deleted;
//    }
//
//    public boolean isEmailVerified() {
//        return emailVerified;
//    }
//
//    public void setEmailVerified(boolean emailVerified) {
//        this.emailVerified = emailVerified;
//    }
//
//    public boolean isAdminVerified() {
//        return adminVerified;
//    }
//
//    public void setAdminVerified(boolean adminVerified) {
//        this.adminVerified = adminVerified;
//    }
//
//    public int getFailedLoginAttempts() {
//        return failedLoginAttempts;
//    }
//
//    public void resetFailedLoginAttempts() {
//        this.failedLoginAttempts = 0;
//    }
//
//    public void increaseFailedLoginAttempts() {
//        this.failedLoginAttempts++;
//    }
//
//    public boolean isAccountLocked() {
//        return accountLocked;
//    }
//
//    public void setAccountLocked(boolean accountLocked) {
//        this.accountLocked = accountLocked;
//    }
//
//    public ZonedDateTime getAccountLockDate() {
//        return accountLockDate;
//    }
//
//    public void setAccountLockDate(ZonedDateTime accountLockDate) {
//        this.accountLockDate = accountLockDate;
//    }
//
//    public Set<GrantedAuthority> getAuthorities() {
//        return roles.stream()
//            .map(Role::getAuthority)
//            .map(Authority::getName)
//            .map(SimpleGrantedAuthority::new)
//            .collect(Collectors.toSet());
//    }
//
//
//
//    public Set<Role> getRoles() { return roles; }
//
//    public void setRoles(Set<Role> roles) { this.roles = roles; }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        User user = (User) o;
//
//        if (!login.equals(user.login)) {
//            return false;
//        }
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        return login.hashCode();
//    }
//
//    @Override
//    public String getName() {
//        return login;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//            "login='" + login + '\'' +
//            ", firstName='" + firstName + '\'' +
//            ", lastName='" + lastName + '\'' +
//            ", email='" + email + '\'' +
//            ", activated='" + isActivated() + '\'' +
//            ", langKey='" + langKey + '\'' +
//            ", activationKey='" + activationKey + '\'' +
//            "}";
//    }
//
//}