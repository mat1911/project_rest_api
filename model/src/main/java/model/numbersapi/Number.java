package model.numbersapi;

public class Number {

    private String text;
    private int year;
    private boolean found;

    public Number() {
    }

    public Number(String text, int year, boolean found) {
        this.text = text;
        this.year = year;
        this.found = found;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Number number = (Number) o;

        if (year != number.year) return false;
        if (found != number.found) return false;
        return text != null ? text.equals(number.text) : number.text == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (found ? 1 : 0);
        return result;
    }
}
