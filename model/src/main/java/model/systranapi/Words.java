package model.systranapi;

import java.util.List;

public class Words {
    private List<Output> outputs = null;

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Words words = (Words) o;

        return outputs != null ? outputs.equals(words.outputs) : words.outputs == null;
    }

    @Override
    public int hashCode() {
        return outputs != null ? outputs.hashCode() : 0;
    }
}
