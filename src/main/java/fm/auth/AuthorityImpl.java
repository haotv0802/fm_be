package fm.auth;

import org.springframework.security.core.GrantedAuthority;

public class AuthorityImpl implements GrantedAuthority {
  final String authority;

  public AuthorityImpl(String authority) {
    this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return authority;
  }

  @Override
  public String toString() {
    return authority;
  }
}