package model.systranapi.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputWords {

    private List<String> words;

    public InputWords(Collection<String> words){ this.words = new ArrayList<>(words); }

}
