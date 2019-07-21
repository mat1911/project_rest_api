package model.countriesapi.json;

import java.util.List;

public class CountryCodes {

    private List<String> codes;

    public CountryCodes() {
    }

    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryCodes that = (CountryCodes) o;

        return codes != null ? codes.equals(that.codes) : that.codes == null;
    }

    @Override
    public int hashCode() {
        return codes != null ? codes.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "CountryCodes{" +
                "codes=" + codes +
                '}';
    }
}
