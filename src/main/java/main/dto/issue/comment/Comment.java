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
public class Comment {

    @JsonProperty("body")
    public String body;
    @JsonProperty("visibility")
    public Visibility visibility;
}
