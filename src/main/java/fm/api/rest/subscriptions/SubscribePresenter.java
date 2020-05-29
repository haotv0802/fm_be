package fm.api.rest.subscriptions;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by HaoHo on 5/29/2020
 */
public class SubscribePresenter {
    @NotNull(message = "Email cannot be null")
    @Size(min = 1, max = 50, message = "Email must be between 1 and 50 characters")
    private String email;

    private List<String> types;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
