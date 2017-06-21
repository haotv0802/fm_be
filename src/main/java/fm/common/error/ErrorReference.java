package fm.common.error;

/**
 * Created by haho on 10/05/2017.
 */
public class ErrorReference {
  private String incidentId;

  public ErrorReference() {

  }

  public ErrorReference(String incidentId) {
    this.incidentId = incidentId;
  }

  public String getIncidentId() {
    return incidentId;
  }

  public void setIncidentId(String incidentId) {
    this.incidentId = incidentId;
  }
}
