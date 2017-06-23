package fm.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

public class UserDetailsImpl implements UserDetails {

  private final String username;

  private String password;

  int userId;

  String fullName;

  String lang;

  private final Set<GrantedAuthority> authorities;

  /**
   * Authorities comparator class
   */
  private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
    public int compare(GrantedAuthority g1, GrantedAuthority g2) {
      // Neither should ever be null as each entry is checked before adding it to the set.
      // If the authority is null, it is a custom authority and should precede others.
      if (g2.getAuthority() == null) {
        return -1;
      }

      if (g1.getAuthority() == null) {
        return 1;
      }

      return g1.getAuthority().compareTo(g2.getAuthority());
    }
  }

  /**
   * Account not expired
   */
  private final boolean accountNonExpired;

  /**
   * Account not locked
   */
  private final boolean accountNonLocked;

  /**
   * End date of locking
   */
  private Date blockedTo;

  /**
   * Credentials not expired
   */
  private final boolean credentialsNonExpired;

  /**
   * Whether user is enabled
   */
  private final boolean enabled;

  /**
   * Number of login failed atempts
   */
  int failedAtempts;

  /**
   * Extranet only flag
   */
  private boolean extranetOnly;

  /**
   * Not ot managing out of competence
   */
  private boolean notManagingOutOfCompetence;


  /**
   * Person schema
   */
  private String schema;

  /**
   * Person Category
   */
  String personCategory;

  /**
   * Management group partition number
   * <p>
   * When the feature is enabled in system settings
   * when g_personnel.p8 is null -> 0
   * when is superuser ->9
   * else g_personnel.p8 (1 - 8)
   * else
   * null
   */
  private Integer mgmtGroupPartNum;

  /**
   * Agency reference g_societe.refsoc
   */
  private String agencyRef;

  /**
   * Simple constructor which set all non essential properties to some default
   *
   * @param username
   * @param password
   * @param authorities
   */
  public UserDetailsImpl(int userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {

    this.userId = userId;
    this.username = username;
    this.password = password;
    this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
  }

//  /**
//   * Construct the <code>ImxUserDetails</code> with the details required by
//   * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
//   *
//   * @param username                   the username presented to the
//   *                                   <code>DaoAuthenticationProvider</code>
//   * @param password                   the password that should be presented to the
//   *                                   <code>DaoAuthenticationProvider</code>
//   * @param fullName                   User full name
//   * @param enabled                    set to <code>true</code> if the user is enabled
//   * @param accountNonExpired          set to <code>true</code> if the account has not
//   *                                   expired
//   * @param credentialsNonExpired      set to <code>true</code> if the credentials
//   *                                   have not expired
//   * @param accountNonLocked           set to <code>true</code> if the account is not
//   *                                   locked
//   * @param blockedTo                  set to end date of the locking period
//   * @param failedAtempts              set login failed attempts
//   * @param extranetOnly               set to <code>true</code> when underling record
//   *                                   has no flag extranet_only
//   * @param notManagingOutOfCompetence set to<code>true<code/> when user is not managing
//   *                                   case elements out of competence
//   * @param schema                     imx schema
//   * @param mgmtGroupPartNum           management group partition number
//   * @param authorities                the authorities that should be granted to the caller
//   *                                   if they presented the correct username and password and the user
//   *                                   is enabled. Not null.
//   * @throws IllegalArgumentException if a <code>null</code> value was passed
//   *                                  either as a parameter to username or password or as an element in the
//   *                                  <code>GrantedAuthority</code> collection
//   */
//  public UserDetailsImpl(String username
//      , String password
//      , String fullName
//      , String lang
//      , boolean enabled
//      , boolean accountNonExpired
//      , boolean credentialsNonExpired
//      , boolean accountNonLocked
//      , Date blockedTo
//      , int failedAtempts
//      , boolean extranetOnly
//      , boolean notManagingOutOfCompetence
//      , String schema
//      , Integer mgmtGroupPartNum
//      , String personCategory
//      , Collection<? extends GrantedAuthority> authorities) {
//
//    if (((username == null) || "".equals(username)) || (password == null)) {
//      throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
//    }
//
//    this.username = username;
//    this.password = password;
//    this.refPerson = refPerson;
//    this.refIndividual = refIndividual;
//    this.fullName = fullName;
//    this.lang = lang;
//    this.enabled = enabled;
//    this.accountNonExpired = accountNonExpired;
//    this.credentialsNonExpired = credentialsNonExpired;
//    this.accountNonLocked = accountNonLocked;
//    this.blockedTo = blockedTo;
//    this.failedAtempts = failedAtempts;
//    this.extranetOnly = extranetOnly;
//    this.notManagingOutOfCompetence = notManagingOutOfCompetence;
//    this.schema = schema;
//    this.mgmtGroupPartNum = mgmtGroupPartNum;
//    this.personCategory = personCategory;
////    this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
//    this.authorities = null;
//  }

  private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
    Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
    // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
    SortedSet<GrantedAuthority> sortedAuthorities =
        new TreeSet<GrantedAuthority>(new AuthorityComparator());

    for (GrantedAuthority grantedAuthority : authorities) {
      Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
      sortedAuthorities.add(grantedAuthority);
    }

    return sortedAuthorities;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public int getUserId() {
    return userId;
  }

  public String getFullName() {
    return fullName;
  }

  public String getLang() {
    return lang;
  }

  public Date getBlockedTo() {
    return blockedTo;
  }

  public int getFailedAtempts() {
    return failedAtempts;
  }

  public boolean isExtranetOnly() {
    return extranetOnly;
  }

  public boolean isNotManagingOutOfCompetence() {
    return notManagingOutOfCompetence;
  }

  public String getSchema() {
    return schema;
  }

  public String getPersonCategory() {
    return personCategory;
  }

  public Integer getMgmtGroupPartNum() {
    return mgmtGroupPartNum;
  }

  public String getAgencyRef() {
    return agencyRef;
  }

  public void setAgencyRef(String agencyRef) {
    this.agencyRef = agencyRef;
  }

  /**
   * Returns {@code true} if the supplied object is a {@code User} instance with the
   * same {@code username} value.
   * <p>
   * In other words, the objects are equal if they have the same username, representing the
   * same principal.
   */
  @Override
  public boolean equals(Object rhs) {
    if (rhs instanceof UserDetailsImpl) {
      return username.equals(((UserDetailsImpl) rhs).username);
    }
    return false;
  }

  /**
   * Returns the hashcode of the {@code username}.
   */
  @Override
  public int hashCode() {
    return username.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(super.toString()).append(": ");
    sb.append("Username: ").append(this.username).append("; ");
    sb.append("Password: [PROTECTED]; ");
//    sb.append("refPerson: ").append(this.refPerson).append("; ");
//    sb.append("refIndividual: ").append(this.refIndividual).append("; ");
//    sb.append("fullName: ").append(this.fullName).append("; ");
//    sb.append("Lang: ").append(this.lang).append("; ");
//    sb.append("Enabled: ").append(this.enabled).append("; ");
//    sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
//    sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
//    sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
//    sb.append("AccountblockedTo: ").append(this.blockedTo).append("; ");
//    sb.append("NumFailedLoginAttempts: ").append(this.failedAtempts).append("; ");
//    sb.append("ExtranetOnly: ").append(this.extranetOnly).append("; ");
//    sb.append("notManagingOutOfCompetence: ").append(this.notManagingOutOfCompetence).append("; ");
//    sb.append("schema: ").append(this.schema).append("; ");
//    sb.append("mgmtGroupPartNum: ").append(this.mgmtGroupPartNum).append("; ");
//    sb.append("agencyRef: ").append(this.agencyRef).append("; ");

//    if (!authorities.isEmpty()) {
//      sb.append("Granted Authorities: ");
//
//      boolean first = true;
//      for (GrantedAuthority auth : authorities) {
//        if (!first) {
//          sb.append(",");
//        }
//        first = false;
//
//        sb.append(auth);
//      }
//    } else {
//      sb.append("Not granted any authorities");
//    }

    return sb.toString();
  }

}

