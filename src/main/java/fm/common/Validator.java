package fm.common;

/**
 * Created by haho on  10/05/2017.
 * Interface for all validators
 */
public interface Validator<T> {
  /**
   * @return the default exception code for any validator
   */
  String defaultFaultCode();

  /**
   * The default, without custom fault and context, validation method
   * Any impl should rise a {@link RuntimeException} if it finds the target invalid
   *
   * @param target - the target of the the validation
   */
  default void validate(T target) {
    validate(target, defaultFaultCode(), (Object) null);
  }

  /**
   * Validation method without the call context
   * Any impl should rise a {@link RuntimeException} if it finds the target invalid
   *
   * @param target    - the target of the the validation
   * @param faultCode - custom fault code
   */
  default void validate(T target, String faultCode) {
    validate(target, faultCode, (Object) null);
  }

  /**
   * Validation method with all options.
   * Any impl should rise a {@link RuntimeException} if it finds the target invalid
   *
   * @param target    - the target of the the validation
   * @param faultCode - custom fault code
   * @param args      - custom context
   */
  void validate(T target, String faultCode, Object... args);
}