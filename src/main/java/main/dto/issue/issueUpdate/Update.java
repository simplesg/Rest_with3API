package main.dto.issue.issueUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.dto.issue.Component;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Update {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("summary")
    public List<Summary> summary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("description")
    public List<Description> description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("labels")
    public List<Labels> labels;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("components")
    public List<Component> components;

}
