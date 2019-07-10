package model.systranapi;

public class Output {
    private String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Output output1 = (Output) o;

        return output != null ? output.equals(output1.output) : output1.output == null;
    }

    @Override
    public int hashCode() {
        return output != null ? output.hashCode() : 0;
    }
}
