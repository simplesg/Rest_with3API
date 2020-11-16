package main.dto.issue.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Visibility {
    @JsonProperty("type")
    public String type;
    @JsonProperty("value")
    public String value;
}
