package main.dto.issue.issueUpdate;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Labels {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "add",defaultValue = "")
    private String add;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(value = "remove",defaultValue = "")
    private String remove;

}