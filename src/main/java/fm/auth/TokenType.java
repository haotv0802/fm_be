package fm.auth;

public enum TokenType
{
    ACCESS("ACCESS")
  , REFRESH("REFRESH")
  ;

  private final String value;

  TokenType(String value)
  {
    this.value = value;
  }

  public String value()
  {
    return value;
  }
}

