package model.countriesapi;

public class Country {

    private String name;
    private String capital;
    private String region;
    private String subregion;

    public Country() {
    }

    public Country(String name, String capital, String region, String subregion) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        if (capital != null ? !capital.equals(country.capital) : country.capital != null) return false;
        if (region != null ? !region.equals(country.region) : country.region != null) return false;
        return subregion != null ? subregion.equals(country.subregion) : country.subregion == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (capital != null ? capital.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (subregion != null ? subregion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country" + System.lineSeparator() +
                "name: " + name + System.lineSeparator() +
                "capital: " + capital + System.lineSeparator() +
                "region: " + region + System.lineSeparator() +
                "subregion: " + subregion + System.lineSeparator();
    }
}
