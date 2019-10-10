package model.numbersapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Number {

    private String text;
    private int year;
    private boolean found;
}
